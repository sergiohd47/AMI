import java.util.Collection;
import java.util.HashSet;

/**
 * @author Sergio Hernandez Dominguez
 */
public interface Grafo {
    //Como el peso es el mismo para todos los arcos, no se hace distincion
    Collection<Integer> nodos(); //devuelve lista de nodos del grafo
    Boolean sonAdyacentes(int nodo1, int nodo2); //devuelve si existe un arco que conecte dos nodos pasados
    void insertarNodo(int nodo); //inserta nodo en el grafo
    void insertarArco(int nodoOrigen, int nodoDestino); //inserta arco en el grafo, devuelve puntero al arco
    HashSet<Integer> nodosVecinos(int nodo); //devuelve collection de nodos vecinos a un nodo pasado
    int tamañoGrafo(); //devuelve el tamaño del grafo (numero nodos)
    int gradoNodo(int nodo); //devuelve el grado de un nodo pasado
    int distanciaEntreNodos(int nodoOrigen, int nodoDestino); //devuelve el numero de aristas minimo que unen ambos nodos
    int[] distanciaANodos(int nodo); //devuelve un vector con todas las distancias de un nodo a todos los demas
}
