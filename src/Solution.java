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
            System.out.println("Dentro bucle while");
            HashSet<Integer> conjuntoNodosFuturosInfec=new HashSet<>();
            for(Integer nodo: conjuntoNodosActivosUltimos){
                System.out.println("Dentro bucle for1");
                for(Integer nodoPosInfect: grafo.nodosVecinos(nodo)){
                    System.out.println("Dentro bucle for2");
                    if((!conjuntoNodosActivos.contains(nodoPosInfect)&&probabilidadArcos>this.probabilidadSolucion)){
                        System.out.println("Dentro bucle if");
                        conjuntoNodosFuturosInfec.add(nodoPosInfect);
                    }
                }
            }
            System.out.println("Fuera bucle for1");
            conjuntoNodosActivosUltimos.addAll(conjuntoNodosFuturosInfec);
            conjuntoNodosActivos.addAll(conjuntoNodosActivosUltimos);
            System.out.println("Tamaño conj: "+conjuntoNodosActivosUltimos.size());
            terminado=conjuntoNodosActivosUltimos.isEmpty();
        }
        return conjuntoNodosActivos;
    }
    public Float getProbabilidadSolucion(){
        return this.probabilidadSolucion;
    }
}
