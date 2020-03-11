import javafx.util.Pair;

import java.util.*;

/**
 * @author Sergio Hernandez Dominguez
 */
//IMPROVE DONDE SE REALIZAN CAMBIOS EN FUNCION DE SU CENTRALIDAD
public class ClosenessImprovement implements Improvement {
    private static final int NUMERO_SIMULACIONES=30; //NUMERO SIMULACIONES QUE HACE EL ALGORITMO (debido al teoria central del limite, se elige 30)
    private int mayorPromedio=Integer.MIN_VALUE;
    @Override
    public void improve(Solution solucion) {
        Grafo grafoND=solucion.getGrafo();
        ArrayList<Pair<Integer,Float>> listaNodosEntradaSemilla= new ArrayList<>();
        ArrayList<Pair<Integer, Float>> listaClosenessCompleta=new ArrayList<>();
        for(Integer nodo: grafoND.nodos()){
            listaClosenessCompleta.add(new Pair<>(nodo,grafoND.closenessCentrality(nodo)));
        }
        ArrayList<Pair<Integer, Float>> listaClosenessSemilla=new ArrayList<>();
        for(Integer nodoSemilla: solucion.getConjuntoNodoSemilla()){
            listaClosenessSemilla.add(new Pair<>(nodoSemilla,grafoND.closenessCentrality(nodoSemilla)));
        }
        listaNodosEntradaSemilla.addAll(listaClosenessCompleta);
        listaNodosEntradaSemilla.removeAll(listaClosenessSemilla); //CONJUNTO NODOS CANDIDATOS A ENTRAR

        Collections.reverse(listaClosenessSemilla); //SE ORDENA DE MENOR A MAYOR EL CONJUNTO DE SEMILLAS
        HashSet<Integer> conjuntoSemillas=new HashSet<>();
        for(Pair<Integer,Float> parSemilla: listaClosenessSemilla){
            conjuntoSemillas.add(parSemilla.getKey());
        }
        int promedioLongitudInfectados = 0;
        int indice=0;
        while(true) {
            for (Pair<Integer,Float> parSalida : listaClosenessSemilla) {
                for (Pair<Integer,Float> parEntrada : listaNodosEntradaSemilla) {
                    //System.out.println("CONJUNTO SEMILLA INICIAL: ");
                    /*for(Pair<Integer,Float> parSemilla: listaClosenessSemilla) {
                        System.out.println(parSemilla);
                    }*/
                    System.out.println("---------- CLOSENESS IMPROVEMENT: "+indice+" ------------");
                    Integer nodoSalida=parSalida.getKey();
                    Integer nodoEntrada=parEntrada.getKey();
                    HashSet<Integer> conjuntoNuevasSemillas = this.realizarIntercambios(nodoSalida, nodoEntrada, conjuntoSemillas);
                    /*
                    System.out.println("NODO SALE: " + parSalida);
                    System.out.println("NODO ENTRA: " + parEntrada);
                    System.out.println("----------------------");
                    System.out.println("CONJUNTO NUEVA SEMILLA: " + conjuntoNuevasSemillas);
                    System.out.println("----------------------");
                     */
                    long inicioSolucion=System.currentTimeMillis();
                    for (int i = 1; i < NUMERO_SIMULACIONES + 1; i++) {
                        HashSet<Integer> conjuntoInfectados = solucion.procedimientoCascada();
                        promedioLongitudInfectados=promedioLongitudInfectados+conjuntoInfectados.size();
                        /*mapaConjuntos.put(i, conjuntoInfectados);
                        System.out.println("SOLUCION " + i);
                        System.out.println("Conjunto semillas: " + conjuntoNuevasSemillas);
                        System.out.println("Tamaño conj: " + conjuntoInfectados.size());
                        System.out.println("Conjunto infectados: " + conjuntoInfectados);
                        System.out.println("------------------");
                         */
                    }
                    /*long finalSolucion = System.currentTimeMillis();
                    for (Integer clave : mapaConjuntos.keySet()) {
                        promedioLongitudInfectados = promedioLongitudInfectados + mapaConjuntos.get(clave).size();
                    }*/
                    int promedio = promedioLongitudInfectados / NUMERO_SIMULACIONES;
                    this.mayorPromedio=Math.max(this.mayorPromedio,promedio);
                    /*ArrayList<HashSet<Integer>> listaSacada = mapaPromedioConjSemilla.get(promedio);
                    if (listaSacada == null) {
                        listaSacada = new ArrayList<>();
                        listaSacada.add(conjuntoNuevasSemillas);
                        mapaPromedioConjSemilla.put(promedio, listaSacada);
                    } else {
                        listaSacada.add(conjuntoNuevasSemillas);
                    }

                    mayorPromedio = Math.max(mayorPromedio, promedio);
                    System.out.println("PROMEDIO INFECTADOS: " + promedio);
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println();
                    System.out.println("--------------------------------------------------------------------");
                    promedioLongitudInfectados = 0;
                    //this.tiempoSolucion=finalSolucion-inicioSolucion;
                     */
                    promedioLongitudInfectados=0;
                    indice++;
                }
            }
            /*System.out.println("TABLA PROMEDIO-CONJUNTOS SEMILLAS");
            for (Map.Entry<Integer, ArrayList<HashSet<Integer>>> entrada : mapaPromedioConjSemilla.entrySet()) {
                System.out.println(entrada);
            }
             */
            //System.out.println();
            //System.out.println("NUMERO DE CONJUNTOS DE INFECCION MAXIMA: " + mapaPromedioConjSemilla.get(mayorPromedio).size());
            //System.out.println("CONJUNTOS INFECCION MAXIMA: " + mapaPromedioConjSemilla.get(mayorPromedio) + " -- PROMEDIO DE INFECCION: " + mayorPromedio);
            break;
        }
    }

    @Override
    public int getMayorPromedio() {
        return this.mayorPromedio;
    }



    private HashSet<Integer> realizarIntercambios(Integer nodoCandidatoSalir, Integer nodoCandidatoEntrar, HashSet<Integer> conjuntoNodosSemilla) {
        HashSet<Integer> conjuntoSemillaSolucion=new HashSet<>();
        conjuntoSemillaSolucion.addAll(conjuntoNodosSemilla);
        conjuntoSemillaSolucion.remove(nodoCandidatoSalir);
        conjuntoSemillaSolucion.add(nodoCandidatoEntrar);
        return conjuntoSemillaSolucion;
    }
}
