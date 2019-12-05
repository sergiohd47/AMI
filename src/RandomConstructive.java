import java.util.*;
/**
 * @author Sergio Hernandez Dominguez
 */
// SE ESCOGEN ALEATORIAMENTE UNA SERIE DE NODOS
public class RandomConstructive implements Constructive {
    private int numeroNodosAEscoger;
    public RandomConstructive(int numeroK){
        this.numeroNodosAEscoger=numeroK;
    }

    @Override
    public HashSet<Integer> construirSolucion(Grafo grafo) throws RuntimeException {
        Collection conjuntoNodosGrafo=grafo.nodos();
        HashSet<Integer> conjuntoNodosSemilla=new HashSet<>();
        ArrayList<Integer> listaNodos=new ArrayList<>();
        listaNodos.addAll(conjuntoNodosGrafo);
        Collections.shuffle(listaNodos); //DESORDENA LA LISTA DE NODOS PARA CONSEGUIR UNA MAYOR ALEATORIEDAD
        if(listaNodos.size()<this.numeroNodosAEscoger){
            throw new RuntimeException("Conjunto con longitud menor que numero de semillas.");
        }
        for(int i=0;i<this.numeroNodosAEscoger;i++){
            conjuntoNodosSemilla.add(listaNodos.get(i));
        }
        return conjuntoNodosSemilla;
    }
}
