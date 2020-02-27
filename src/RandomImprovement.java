import java.util.*;

/**
 * @author Sergio Hernandez Dominguez
 */
//IMPROVE DONDE SE REALIZAN CAMBIOS RANDOM SOBRE AMBOS CONJUNTOS (todos los cambios posibles)
public class RandomImprovement implements Improvement {
    private static final int NUMERO_SIMULACIONES=30; //NUMERO SIMULACIONES QUE HACE EL ALGORITMO (debido al teoria central del limite, se elige 30)
    private double tiempoSolucion;
    @Override
    public void improve(Solution solucion) {
        HashMap<Integer,HashSet<Integer>> mapaConjuntos=new HashMap<>();
        HashMap<Integer, ArrayList<HashSet<Integer>>> mapaPromedioConjSemilla=new HashMap<>();
        int mayorPromedio=Integer.MIN_VALUE;
        ArrayList<HashSet<Integer>> lista=new ArrayList<>();
        mapaPromedioConjSemilla.put(mayorPromedio,lista);
        Grafo grafoND=solucion.getGrafo();
        HashSet<Integer> conjuntoNodosSemilla=solucion.getConjuntoNodoSemilla();
        HashSet<Integer> conjuntoNodosEntradaSemilla = new HashSet<>(grafoND.nodos());
        conjuntoNodosEntradaSemilla.removeAll(conjuntoNodosSemilla); //CONJUNTO NODOS CANDIDATOS A ENTRAR
        int promedioLongitudInfectados = 0;
        while(true) {
            for(Integer nodoSalida: conjuntoNodosSemilla) {
                for(Integer nodoEntrada: conjuntoNodosEntradaSemilla) {
                    //System.out.println("CONJUNTO SEMILLA INICIAL: "+conjuntoNodosSemilla);
                    //System.out.println("----------------------");
                    HashSet<Integer> conjuntoNuevasSemillas= this.realizarIntercambios(nodoSalida,nodoEntrada,conjuntoNodosSemilla);
                    //System.out.println("NODO SALE: "+nodoSalida);
                    //System.out.println("NODO ENTRA: "+nodoEntrada);
                    //System.out.println("----------------------");
                    //System.out.println("CONJUNTO NUEVA SEMILLA: " + conjuntoNuevasSemillas);
                    //System.out.println("----------------------");
                    long inicioSolucion=System.currentTimeMillis();
                    for (int i = 1; i < NUMERO_SIMULACIONES + 1; i++) {
                        HashSet<Integer> conjuntoInfectados = solucion.procedimientoCascada();
                        mapaConjuntos.put(i, conjuntoInfectados);
                        //System.out.println("SOLUCION " + i);
                        //System.out.println("Conjunto semillas: "+conjuntoNuevasSemillas);
                        //System.out.println("Tamaño conj: " + conjuntoInfectados.size());
                        //System.out.println("Conjunto infectados: " + conjuntoInfectados);
                        //System.out.println("------------------");
                    }
                    long finalSolucion = System.currentTimeMillis();
                    for (Integer clave : mapaConjuntos.keySet()) {
                        promedioLongitudInfectados = promedioLongitudInfectados + mapaConjuntos.get(clave).size();
                    }
                    int promedio=promedioLongitudInfectados/NUMERO_SIMULACIONES;
                    ArrayList<HashSet<Integer>> listaSacada=mapaPromedioConjSemilla.get(promedio);
                    if(listaSacada==null){
                        listaSacada=new ArrayList<>();
                        listaSacada.add(conjuntoNuevasSemillas);
                        mapaPromedioConjSemilla.put(promedio,listaSacada);
                    }else{
                        listaSacada.add(conjuntoNuevasSemillas);
                    }

                    mayorPromedio=Math.max(mayorPromedio,promedio);
                    /*System.out.println("PROMEDIO INFECTADOS: " + promedio);
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println();
                    System.out.println("--------------------------------------------------------------------");*/
                    promedioLongitudInfectados=0;
                    this.tiempoSolucion=finalSolucion-inicioSolucion;
                }
            }
            //System.out.println("TABLA PROMEDIO-CONJUNTOS SEMILLAS");
            /*for (Map.Entry<Integer,ArrayList<HashSet<Integer>>> entrada: mapaPromedioConjSemilla.entrySet()){
                System.out.println(entrada);
            }*/
            //System.out.println();
            //System.out.println("NUMERO DE CONJUNTOS DE INFECCION MAXIMA: "+mapaPromedioConjSemilla.get(mayorPromedio).size());
            //System.out.println("CONJUNTOS INFECCION MAXIMA: " + mapaPromedioConjSemilla.get(mayorPromedio)+ " -- PROMEDIO DE INFECCION: "+mayorPromedio);
            break;
        }
    }

    @Override
    public double getTiempoSolucion() {
        return this.tiempoSolucion;
    }

    private HashSet<Integer> realizarIntercambios(Integer nodoCandidatoSalir, Integer nodoCandidatoEntrar, HashSet<Integer> conjuntoNodosSemilla) {
        HashSet<Integer> conjuntoSemillaSolucion = new HashSet<>(conjuntoNodosSemilla);
        conjuntoSemillaSolucion.remove(nodoCandidatoSalir);
        conjuntoSemillaSolucion.add(nodoCandidatoEntrar);
        return conjuntoSemillaSolucion;
    }
}
