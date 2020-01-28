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
        if(grafo.tamañoGrafo()<this.numeroNodosAEscoger){
            throw new RuntimeException("Grafo con longitud menor que numero de semillas.");
        }
        Collection conjuntoNodosGrafo=grafo.nodos();
        HashSet<Integer> conjuntoNodosSemilla=new HashSet<>();
        ArrayList<Integer> listaNodos=new ArrayList<>();
        listaNodos.addAll(conjuntoNodosGrafo);
        Random random=new Random();
        for(int i=0;i<this.numeroNodosAEscoger;i++){
            conjuntoNodosSemilla.add(listaNodos.get(random.nextInt(listaNodos.size())));
        }
        return conjuntoNodosSemilla;
    }
}
