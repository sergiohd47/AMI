import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GrafoNDTest {
    private Grafo<Integer,Float> crearGrafo(){
        Grafo<Integer,Float> grafo=new GrafoND<>();
        Nodo<Integer> nodo1=grafo.insertarNodo(1);
        Nodo<Integer> nodo2=grafo.insertarNodo(2);
        Nodo<Integer> nodo3=grafo.insertarNodo(3);
        Nodo<Integer> nodo4=grafo.insertarNodo(4);
        grafo.insertarArco(nodo1,nodo2, Float.MIN_VALUE);
        grafo.insertarArco(nodo1,nodo3, Float.MIN_VALUE);

        grafo.insertarArco(nodo2,nodo1, Float.MIN_VALUE);
        grafo.insertarArco(nodo2,nodo3, Float.MIN_VALUE);

        grafo.insertarArco(nodo3,nodo1, Float.MIN_VALUE);
        grafo.insertarArco(nodo3,nodo2, Float.MIN_VALUE);
        grafo.insertarArco(nodo3,nodo4, Float.MIN_VALUE);

        grafo.insertarArco(nodo4,nodo3, Float.MIN_VALUE);
        return grafo;
    }
    @Test
    void nodos() {
        Grafo<Integer,Float> grafo=crearGrafo();
        assertEquals(grafo.nodos().size(),4);
    }

    @Test
    void arcos() {
        Grafo<Integer,Float> grafo=crearGrafo();
        assertEquals(grafo.arcos().size(),4);
    }

    @Test
    void arcosIncidentes() {
        Grafo<Integer,Float> grafo=crearGrafo();
        Nodo<Integer> nodo=grafo.dameNodo(3);
        Nodo<Integer> nodo2=grafo.dameNodo(4);
        assertEquals(grafo.arcosIncidentes(nodo).size(),3);
        assertEquals(grafo.arcosIncidentes(nodo2).size(),1);
    }

    @Test
    void nodoOpuesto() {
        Grafo<Integer,Float> grafo=crearGrafo();
        Nodo<Integer> nodo=grafo.dameNodo(3);
        Nodo<Integer> nodo2=grafo.dameNodo(1);
        Nodo<Integer> nodo3=grafo.dameNodo(4);
        Arco<Float,Integer> arco=grafo.dameArco(1,3);
        assertEquals(grafo.nodoOpuesto(nodo,arco),nodo2);
        assertNotEquals(grafo.nodoOpuesto(nodo,arco),nodo3);
    }

    @Test
    void nodosFinales() {
        Grafo<Integer,Float> grafo=crearGrafo();
        Nodo<Integer> nodo=grafo.dameNodo(3);
        Nodo<Integer> nodo2=grafo.dameNodo(1);
        Arco<Float,Integer> arco=grafo.dameArco(1,3);
        assertEquals(grafo.nodosFinales(arco).size(),2);
        assertEquals(grafo.nodosFinales(arco).remove(0),nodo);
        assertEquals(grafo.nodosFinales(arco).remove(0),nodo2);
    }

    @Test
    void sonAdyacentes() {
        Grafo<Integer,Float> grafo=crearGrafo();
        Nodo<Integer> nodo=grafo.dameNodo(3);
        Nodo<Integer> nodo2=grafo.dameNodo(1);
        Nodo<Integer> nodo3=grafo.dameNodo(4);
        assertTrue(grafo.sonAdyacentes(nodo,nodo2));
        assertFalse(grafo.sonAdyacentes(nodo2,nodo3));
    }

    @Test
    void insertarNodo() {
        Grafo<Integer,Float> grafo=crearGrafo();
        grafo.insertarNodo(5);
        assertEquals(grafo.nodos().size(),5);
    }

    @Test
    void insertarArco() {
        Grafo<Integer,Float> grafo=crearGrafo();
        Nodo<Integer> nodo1=grafo.dameNodo(1);
        Nodo<Integer> nodo4=grafo.dameNodo(4);
        grafo.insertarArco(nodo1,nodo4, Float.MIN_VALUE);
        assertEquals(grafo.arcos().size(),5);
        grafo.insertarArco(nodo4,nodo1, Float.MIN_VALUE);
        assertEquals(grafo.arcos().size(),5);
    }

    @Test
    void nodosVecinos() {
        Grafo<Integer,Float> grafo=crearGrafo();
        Nodo<Integer> nodo3=grafo.dameNodo(3);
        assertEquals(grafo.nodosVecinos(nodo3).size(),3);
    }

    @Test
    void dameNodo() {
        Grafo<Integer,Float> grafo=crearGrafo();
        Nodo<Integer> nodo3=grafo.dameNodo(3);
        assertEquals(nodo3.getNodoInfo(),3);
        Nodo<Integer> nodo2=grafo.dameNodo(1);
        assertNotEquals(nodo2.getNodoInfo(),3);
    }
    @Test
    void dameArco(){
        Grafo<Integer,Float> grafo=crearGrafo();
        Arco<Float,Integer> arco=grafo.dameArco(1,3);
        ArrayList<Nodo<Integer>> lista=new ArrayList<>();
        lista.add(new Nodo<Integer>(1));
        lista.add(new Nodo<Integer>(3));
        assertTrue(grafo.nodosFinales(arco).containsAll(lista));
    }
}