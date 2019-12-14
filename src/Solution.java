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
        HashSet<Integer> conjuntoNodosVisitados=new HashSet<>();
        conjuntoNodosActivosUltimos.addAll(conjuntoNodosActivos);
        while(!terminado){
            conjuntoNodosFuturosInfec.clear();
            for(Integer nodo: conjuntoNodosActivosUltimos){
                if(conjuntoNodosVisitados.contains(nodo)){
                    continue;
                }
                conjuntoNodosVisitados.add(nodo);
                System.out.println("NODO FOR1: "+nodo);
                System.out.println("CONJUNTO NODOS VISITADOS: "+conjuntoNodosVisitados);
                for(Integer nodoPosInfect: grafo.nodosVecinos(nodo)){
                    System.out.println("NODO FOR2(VECINOS): "+nodoPosInfect);
                    float probabilidadSolucion=(float)Math.random()*1;
                    System.out.println("PA: "+probabilidadArcos);
                    System.out.println("PS: "+probabilidadSolucion);
                    if((!conjuntoNodosActivos.contains(nodoPosInfect)&&(probabilidadArcos>probabilidadSolucion))){
                        System.out.println("NODO AÑADIDO: "+nodoPosInfect);
                        conjuntoNodosFuturosInfec.add(nodoPosInfect);
                    }
                }
                System.out.println("------------------------");
            }
            conjuntoNodosActivosUltimos.addAll(conjuntoNodosFuturosInfec);
            conjuntoNodosActivos.addAll(conjuntoNodosActivosUltimos);
            terminado=conjuntoNodosFuturosInfec.isEmpty();
        }
        return conjuntoNodosActivos;
    }
}
