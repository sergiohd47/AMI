import javafx.util.Pair;

import java.util.*;

/**
 * @author Sergio Hernandez Dominguez
 */
//IMPROVE DONDE SE REALIZAN CAMBIOS EN FUNCION DE SU CENTRALIDAD PERO AÑADIENDO UN NUMERO BETA PARA ACOTAR LA BUSQUEDA
public class BetaImprovement implements Improvement {
    private static final int NUMERO_SIMULACIONES=30; //NUMERO SIMULACIONES QUE HACE EL ALGORITMO (debido al teoria central del limite, se elige 30)
    private int promedioMaximo=Integer.MIN_VALUE;
    @Override
    public void improve(Solution solucion) {
        long inicioTiempoPre=System.currentTimeMillis();
        /*HashMap<Integer, HashSet<Integer>> mapaConjuntos=new HashMap<>();
        HashMap<Integer, ArrayList<HashSet<Integer>>> mapaPromedioConjSemilla=new HashMap<>();
        int mayorPromedio=Integer.MIN_VALUE;
        ArrayList<HashSet<Integer>> lista=new ArrayList<>();
        mapaPromedioConjSemilla.put(mayorPromedio,lista);
         */
        Grafo grafoND=solucion.getGrafo();
        ArrayList<Pair<Integer,Float>> listaClosenessCompleta=new ArrayList<>();
        for(Integer nodo: grafoND.nodos()){
            listaClosenessCompleta.add(new Pair<>(nodo, grafoND.closenessCentrality(nodo)));
        }
        ArrayList<Pair<Integer,Float>> listaClosenessSemilla=new ArrayList<>();
        for(Integer nodoSemilla: solucion.getConjuntoNodoSemilla()){
            listaClosenessSemilla.add(new Pair<>(nodoSemilla,grafoND.closenessCentrality(nodoSemilla)));
        }
        ArrayList<Pair<Integer, Float>> listaNodosEntradaSemilla = new ArrayList<>(listaClosenessCompleta);
        listaNodosEntradaSemilla.removeAll(listaClosenessSemilla); //CONJUNTO NODOS CANDIDATOS A ENTRAR

        //Collections.reverse(listaClosenessSemilla); //SE ORDENA DE MENOR A MAYOR EL CONJUNTO DE SEMILLAS
        HashSet<Integer> conjuntoSemillas=new HashSet<>();
        for(Pair<Integer,Float> parSemilla: listaClosenessSemilla){
            conjuntoSemillas.add(parSemilla.getKey());
        }
        //REVISAR ESTA PARTE DEL CODIGO
        float numeroBeta=(float)Math.random();
        System.out.println("NUMERO BETA: "+numeroBeta);
        int numeroNodosEscoger=(int)(numeroBeta*grafoND.tamañoGrafo());
        System.out.println("NUMERO NODOS ESCOGIDOS: "+numeroNodosEscoger);
        HashSet<Pair<Integer,Float>> semillasSalidaAcotadas=new HashSet<>(listaClosenessSemilla);
        HashSet<Pair<Integer,Float>> nodosEntradaAcotados=new HashSet<>();
        //System.out.println("LISTA CLOSENESS SEMILLA: "+listaClosenessSemilla);
        Collections.sort(listaNodosEntradaSemilla,new ComparadorCloseness());
        for(Pair<Integer,Float> parEntradaSemilla: listaNodosEntradaSemilla){
            if(nodosEntradaAcotados.size()==numeroNodosEscoger){
                break;
            }
            nodosEntradaAcotados.add(parEntradaSemilla);
        }

        //System.out.println("LISTA NODOS ENTRADA SEMILLA: "+listaNodosEntradaSemilla);
        //System.out.println("SEMILLAS ACOTADAS: "+semillasSalidaAcotadas);
        //System.out.println("NUMERO NODOS ENTRADA ACOTADOS: "+nodosEntradaAcotados.size());
        /*if(semillasSalidaAcotadas.isEmpty()||nodosEntradaAcotados.isEmpty()) {
            System.out.println("--------------------------------------------------------------------");
            System.out.println("EL NUMERO BETA CONDICIONA DEMASIADO LA ACOTACION DE LAS LISTAS");
            System.out.println("--------------------------------------------------------------------");

        }*/
        int promedioLongitudInfectados = 0;
        //int indice=0;
        long finalTiempoPre=System.currentTimeMillis();
        while(true) {
            for (Pair<Integer,Float> parSalida : semillasSalidaAcotadas) {
                for (Pair<Integer,Float> parEntrada : nodosEntradaAcotados) {
                    long inicioInterior=System.currentTimeMillis();
                    //System.out.println("---------- BETA IMPROVEMENT: "+indice+" ------------");
                    //System.out.println("CONJUNTO SEMILLA INICIAL: ");
                    /*for(Pair<Integer,Float> parSemilla: listaClosenessSemilla) {
                        System.out.println(parSemilla);
                    }
                    System.out.println("----------------------");
                     */
                    Integer nodoSalida=parSalida.getKey();
                    Integer nodoEntrada=parEntrada.getKey();
                    HashSet<Integer> conjuntoNuevasSemillas = this.realizarIntercambios(nodoSalida, nodoEntrada, conjuntoSemillas);
                    /*System.out.println("NODO SALE: " + parSalida);
                    System.out.println("NODO ENTRA: " + parEntrada);
                    System.out.println("----------------------");
                    System.out.println("CONJUNTO NUEVA SEMILLA: " + conjuntoNuevasSemillas);
                    System.out.println("----------------------");
                     */
                    //long inicioSolucion=System.currentTimeMillis();
                    Solution solucionAux=new Solution(grafoND,conjuntoNuevasSemillas,solucion.getProbabilidadArcos());
                    for (int i = 1; i < NUMERO_SIMULACIONES + 1; i++) {
                        HashSet<Integer> conjuntoInfectados = solucionAux.procedimientoCascada();
                        promedioLongitudInfectados=promedioLongitudInfectados+conjuntoInfectados.size();
                        //mapaConjuntos.put(i, conjuntoInfectados);
                        /*System.out.println("SOLUCION " + i);
                        System.out.println("Conjunto semillas: " + conjuntoNuevasSemillas);
                        System.out.println("Tamaño conj: " + conjuntoInfectados.size());
                        System.out.println("Conjunto infectados: " + conjuntoInfectados);
                        System.out.println("------------------");
                         */
                    }
                    //long finalSolucion = System.currentTimeMillis();
                    /*for (Integer clave : mapaConjuntos.keySet()) {
                        promedioLongitudInfectados = promedioLongitudInfectados + mapaConjuntos.get(clave).size();
                    }

                     */
                    int promedio = promedioLongitudInfectados / NUMERO_SIMULACIONES;
                    /*ArrayList<HashSet<Integer>> listaSacada = mapaPromedioConjSemilla.get(promedio);
                    if (listaSacada == null) {
                        listaSacada = new ArrayList<>();
                        listaSacada.add(conjuntoNuevasSemillas);
                        mapaPromedioConjSemilla.put(promedio, listaSacada);
                    } else {
                        listaSacada.add(conjuntoNuevasSemillas);
                    }
                    */
                    this.promedioMaximo = Math.max(this.promedioMaximo, promedio);
                    //System.out.println("MAYOR PROMEDIO: "+this.promedioMaximo);
                    /*System.out.println("PROMEDIO INFECTADOS: " + promedio);
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println();
                    System.out.println("--------------------------------------------------------------------");+
                     */
                    promedioLongitudInfectados = 0;
                    //indice++;
                    //this.tiempoSolution=finalSolucion-inicioSolucion;
                    long finalInterior=System.currentTimeMillis();
                    double tiempoInterior=finalInterior-inicioInterior;
                    System.out.println("TIEMPO INTERIOR: "+tiempoInterior/1000);
                    break;
                }
                double tiempoPre=finalTiempoPre-inicioTiempoPre;
                System.out.println("TIEMPO PRE: "+tiempoPre/1000);
                break;
            }
            /*System.out.println("TABLA PROMEDIO-CONJUNTOS SEMILLAS");
            for (Map.Entry<Integer, ArrayList<HashSet<Integer>>> entrada : mapaPromedioConjSemilla.entrySet()) {
                System.out.println(entrada);
            }
            System.out.println();
            System.out.println("NUMERO DE CONJUNTOS DE INFECCION MAXIMA: " + mapaPromedioConjSemilla.get(mayorPromedio).size());
            System.out.println("CONJUNTOS INFECCION MAXIMA: " + mapaPromedioConjSemilla.get(mayorPromedio) + " -- PROMEDIO DE INFECCION: " + mayorPromedio);
             */
            break;
        }
    }

    @Override
    public int getMayorPromedio() {
        return this.promedioMaximo;
    }



    private HashSet<Integer> realizarIntercambios(Integer nodoCandidatoSalir, Integer nodoCandidatoEntrar, HashSet<Integer> conjuntoNodosSemilla) {
        HashSet<Integer> conjuntoSemillaSolucion=new HashSet<>(conjuntoNodosSemilla);
        conjuntoSemillaSolucion.remove(nodoCandidatoSalir);
        conjuntoSemillaSolucion.add(nodoCandidatoEntrar);
        return conjuntoSemillaSolucion;
    }
}
