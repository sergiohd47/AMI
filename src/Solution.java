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
    public HashSet<Integer> procedimientoCascada(Grafo grafo, HashSet<Integer> conjuntoNodoSemilla, float probabilidadArcos){
        boolean terminado=false;
        HashSet<Integer> conjuntoNodosActivos=new HashSet<>();
        conjuntoNodosActivos.addAll(conjuntoNodoSemilla);
        HashSet<Integer> conjuntoNodosActivosUltimos=new HashSet<>();
        conjuntoNodosActivosUltimos.addAll(conjuntoNodosActivos);
        while(!terminado){
            HashSet<Integer> conjuntoNodosFuturosInfec=new HashSet<>();
            for(Integer nodo: conjuntoNodosActivosUltimos){
                for(Integer nodoPosInfect: grafo.nodosVecinos(nodo)){
                    if((!conjuntoNodosActivos.contains(nodoPosInfect)&&probabilidadArcos>this.probabilidadSolucion)){
                        conjuntoNodosFuturosInfec.add(nodoPosInfect);
                    }
                }
            }
            conjuntoNodosActivosUltimos.addAll(conjuntoNodosFuturosInfec);
            conjuntoNodosActivos.addAll(conjuntoNodosActivosUltimos);
            terminado=conjuntoNodosActivosUltimos.isEmpty();
        }
        return conjuntoNodosActivos;
    }
    public Float getProbabilidadSolucion(){
        return this.probabilidadSolucion;
    }
}
