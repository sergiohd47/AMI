import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
/**
 * @author Sergio Hernandez Dominguez
 */
//Guarda la informacion correspondiente a la solucion. Tendra una referencia a la Instance y despues, la estructura necesaria
// para representar la solucion al problema.
public class Solution {
    public HashSet<Integer> procedimientoCascada(Grafo grafo, HashSet<Integer> conjuntoNodoSemilla, float probabilidadArcos){
        boolean terminado=false;
        HashSet<Integer> conjuntoNodosActivos=new HashSet<>();
        HashSet<Integer> conjuntoNodosFuturosInfec=new HashSet<>();
        conjuntoNodosActivos.addAll(conjuntoNodoSemilla);
        HashSet<Integer> conjuntoNodosActivosUltimos=new HashSet<>();
        conjuntoNodosActivosUltimos.addAll(conjuntoNodosActivos);
        while(!terminado){
            //System.out.println("Dentro bucle while");
            for(Integer nodo: conjuntoNodosActivosUltimos){
                //System.out.println("Dentro bucle for1");
                for(Integer nodoPosInfect: grafo.nodosVecinos(nodo)){
                    //System.out.println("Dentro bucle for2");
                    float probabilidadSolucion=(float)Math.random()*1;
                    if(((probabilidadArcos>probabilidadSolucion)&&!conjuntoNodosActivos.contains(nodoPosInfect))){
                        //System.out.println("Dentro bucle if");
                        conjuntoNodosFuturosInfec.add(nodoPosInfect);
                    }
                }
            }
            //System.out.println("Fuera bucle for1");
            //System.out.println("Tamaño conj Infect: "+conjuntoNodosFuturosInfec.size());
            conjuntoNodosActivosUltimos.addAll(conjuntoNodosFuturosInfec);
            conjuntoNodosActivos.addAll(conjuntoNodosActivosUltimos);
            //System.out.println("Tamaño conj: "+conjuntoNodosActivosUltimos.size());
            terminado=conjuntoNodosActivosUltimos.isEmpty();
        }
        return conjuntoNodosActivos;
    }
}
