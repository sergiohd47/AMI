import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
/**
 * @author Sergio Hernandez Dominguez
 */
//Guarda la informacion correspondiente a la solucion. Tendra una referencia a la Instance y despues, la estructura necesaria
// para representar la solucion al problema.
public class Solution {
    private Grafo grafo;
    private HashSet<Integer> conjuntoNodoSemilla=new HashSet<>();

    public HashSet<Integer> procedimientoCascada(Grafo grafo, HashSet<Integer> conjuntoNodoSemilla, float probabilidadArcos){
        this.grafo=grafo;
        this.conjuntoNodoSemilla=conjuntoNodoSemilla;
        boolean terminado=false;
        HashSet<Integer> conjuntoNodosActivos=new HashSet<>();
        HashSet<Integer> conjuntoNodosFuturosInfec=new HashSet<>();
        conjuntoNodosActivos.addAll(conjuntoNodoSemilla);
        HashSet<Integer> conjuntoNodosActivosUltimos=new HashSet<>();
        HashSet<Integer> conjuntoNodosVisitados=new HashSet<>();
        conjuntoNodosActivosUltimos.addAll(conjuntoNodosActivos);
        while(!terminado){
            conjuntoNodosFuturosInfec.clear();
            for(Integer nodo: conjuntoNodosActivosUltimos){
                if(conjuntoNodosVisitados.contains(nodo)){
                    continue;
                }
                conjuntoNodosVisitados.add(nodo);
                for(Integer nodoPosInfect: grafo.nodosVecinos(nodo)){
                    float probabilidadSolucion=(float)Math.random();
                    if((!conjuntoNodosActivos.contains(nodoPosInfect)&&(probabilidadArcos>probabilidadSolucion))){
                        conjuntoNodosFuturosInfec.add(nodoPosInfect);
                    }
                }
            }
            conjuntoNodosActivosUltimos.addAll(conjuntoNodosFuturosInfec);
            conjuntoNodosActivos.addAll(conjuntoNodosActivosUltimos);
            terminado=conjuntoNodosFuturosInfec.isEmpty();
        }
        return conjuntoNodosActivos;
    }
    public Grafo getGrafo(){
        return this.grafo;
    }
    public HashSet<Integer> getConjuntoNodoSemilla(){
        return this.conjuntoNodoSemilla;
    }
}
