import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Sergio Hernandez Dominguez
 */
class GrafoNDTest {

    Grafo crearGrafo() {
        Grafo grafoND=new GrafoND(4);

        grafoND.insertarNodo(1);
        grafoND.insertarNodo(2);
        grafoND.insertarNodo(3);
        grafoND.insertarNodo(4);


        grafoND.insertarArco(1,2);
        grafoND.insertarArco(1,3);

        grafoND.insertarArco(2,1);
        grafoND.insertarArco(2,3);

        grafoND.insertarArco(3,1);
        grafoND.insertarArco(3,2);
        grafoND.insertarArco(3,4);

        grafoND.insertarArco(4,3);
        return grafoND;
    }

    @Test
    void nodos() {
        Grafo grafo=crearGrafo();
        assertEquals(grafo.nodos().size(),4);
    }

    @Test
    void sonAdyacentes() {
        Grafo grafo=crearGrafo();
        assertTrue(grafo.sonAdyacentes(1,2));
        grafo.insertarArco(4,1);
        assertTrue(grafo.sonAdyacentes(4,1));
        assertTrue(grafo.sonAdyacentes(1,4));
    }

    @Test
    void nodosVecinos() {
        Grafo grafo=crearGrafo();
        assertEquals(grafo.nodosVecinos(3).size(),3);
        assertNotEquals(grafo.nodosVecinos(1).size(),3);
    }
    @Test
    void tamañoGrafoTest(){
        Grafo grafo=crearGrafo();
        assertEquals(grafo.tamañoGrafo(),4);

    }
    @Test
    void gradoNodoTest(){
        Grafo grafo=crearGrafo();
        assertEquals(grafo.gradoNodo(3),3);
        assertEquals(grafo.gradoNodo(2),grafo.gradoNodo(1));
    }
    @Test
    void nodosVecinosException() {
        Grafo grafo=crearGrafo();
        RuntimeException runtimeException=assertThrows(RuntimeException.class,()->grafo.nodosVecinos(8));
        assertEquals("Nodo no existe", runtimeException.getMessage());
    }
    @Test
    void distanciaEntreNodosTest(){
        Grafo grafo=crearGrafo();
        assertEquals(grafo.distanciaEntreNodos(1,4),2);
        assertEquals(grafo.distanciaEntreNodos(1,3),1);
        assertNotEquals(grafo.distanciaEntreNodos(1,4),1);
    }
}