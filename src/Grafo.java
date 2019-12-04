import java.awt.geom.Arc2D;
import java.util.Collection;
import java.util.Collections;

public interface Grafo<V,E> {
    //V-> tipo del nodo         E-> tipo del peso del arco
    Collection<Nodo<V>> nodos(); //devuelve lista de nodos del grafo
    Collection<Arco<E,V>> arcos(); //devuelve lista de arcos del grafo
    Collection<Arco<E,V>> arcosIncidentes(Nodo<V> nodo); //devuelve lista de arcos relacionados con un nodo
    Nodo<V> nodoOpuesto(Nodo<V> nodo, Arco<E,V> arco); //devuelve el nodo opuesto de un arco pasado y un nodo
    Collection<Nodo<V>> nodosFinales(Arco<E,V> arco); //devuelve los nodos que forman el arco pasado
    Boolean sonAdyacentes(Nodo<V> nodo1, Nodo<V> nodo2); //devuelve si existe un arco que conecte dos nodos pasados
    Nodo<V> insertarNodo(V nodoInfo); //inserta nodo en el grafo, devuelve puntero al nodo
    Arco<E,V> insertarArco(Nodo<V> nodoOrigen, Nodo<V> nodoDestino, E pesoArco); //inserta arco en el grafo, devuelve puntero al arco
    Collection<Nodo<V>> nodosVecinos(Nodo<V> nodo);
    Nodo<V> dameNodo(V nodoInfo); //metodo auxiliar usado para los test
    Arco<E,V> dameArco(V nodoO, V nodoD); //metodo auxiliar usado para los test
}
