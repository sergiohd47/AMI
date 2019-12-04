import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

//Guarda la informacion correspondiente a la solucion. Tendra una referencia a la Instance y despues, la estructura necesaria
// para representar la solucion al problema.
public class Solution {
    private float probabilidadSolucion;
    public Solution(){
        this.probabilidadSolucion=(float)Math.random()*1;
    }
    public HashSet<Nodo<Integer>> procedimientoCascada(Grafo grafo, HashSet<Nodo<Integer>> conjuntoNodoSemilla){
        boolean terminado=false;
        HashSet<Nodo<Integer>> conjuntoNodosActivos=new HashSet<>();
        conjuntoNodosActivos.addAll(conjuntoNodoSemilla);
        HashSet<Nodo<Integer>> conjuntoNodosActivosUltimos=new HashSet<>();
        conjuntoNodosActivosUltimos.addAll(conjuntoNodosActivos);
        while(!terminado){
            System.out.println("dentro while solution");
            HashSet<Nodo<Integer>> conjuntoNodosFuturosInfec=new HashSet<>();
            for(Nodo<Integer> nodo: conjuntoNodosActivosUltimos){
                System.out.println("dentro for solution");
                /*for(Arco<Float,Integer> arcoSacado: grafo.arcosIncidentes(nodo)){
                    Nodo<Integer> nodoPosInfect=grafo.nodoOpuesto(nodo,arcoSacado);
                    if((!conjuntoNodosActivos.contains(nodoPosInfect))&&(arcoSacado.getPesoArco()>this.probabilidadSolucion)){
                        conjuntoNodosFuturosInfec.add(nodoPosInfect);
                    }

                }

                 */

                System.out.println("numeroNodos: "+grafo.arcosIncidentes(nodo).size());
                Iterator<Arco<Float,Integer>> it=grafo.arcosIncidentes(nodo).iterator();
                System.out.println("it.hasNext(): "+it.hasNext());
                while(it.hasNext()){
                    System.out.println("dentro while iterator solution");
                    Nodo<Integer> nodoPosInfect=grafo.nodoOpuesto(nodo,it.next());
                    if((!conjuntoNodosActivos.contains(nodoPosInfect))&&(it.next().getPesoArco()>this.probabilidadSolucion)){
                        conjuntoNodosFuturosInfec.add(nodoPosInfect);
                    }
                }
                conjuntoNodosActivosUltimos.addAll(conjuntoNodosFuturosInfec);
                conjuntoNodosActivos.addAll(conjuntoNodosActivosUltimos);
                terminado=conjuntoNodosActivosUltimos.isEmpty();
            }
        }
        return conjuntoNodosActivos;
    }
    public Float getProbabilidadSolucion(){
        return this.probabilidadSolucion;
    }
}
