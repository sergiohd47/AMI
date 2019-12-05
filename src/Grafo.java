import java.awt.geom.Arc2D;
import java.util.Collection;
import java.util.Collections;
/**
 * @author Sergio Hernandez Dominguez
 */
public interface Grafo {
    //Como el peso es el mismo para todos los arcos, no se hace distincion
    Collection<Integer> nodos(); //devuelve lista de nodos del grafo
    Boolean sonAdyacentes(int nodo1, int nodo2); //devuelve si existe un arco que conecte dos nodos pasados
    void insertarNodo(int nodo); //inserta nodo en el grafo
    void insertarArco(int nodoOrigen, int nodoDestino); //inserta arco en el grafo, devuelve puntero al arco
    Collection<Integer> nodosVecinos(int nodo);
    int tamañoGrafo();
}
