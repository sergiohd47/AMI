import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.*;
/**
 * @author Sergio Hernandez Dominguez
 */
//GRAFO NO DIRIGIDO
public class GrafoND implements Grafo{
    //Esta implementacion de grafos se basa en una lista de adyacencia y en una matriz de adyacencia.
    private HashSet<Integer>[] listaAdyacencia;
    private boolean[][] matrizAdyacencia;
    private int numeroNodos;

    public GrafoND(int numeroNodos){
        this.numeroNodos=numeroNodos;
        this.listaAdyacencia= new HashSet[this.numeroNodos+1]; //INICIALIZA LA LISTA ADYACENCIA AL NUMERO NODOS +1 (desechar la posicion 0)
        this.matrizAdyacencia= new boolean[this.listaAdyacencia.length][this.listaAdyacencia.length]; //INICIALIZA LA MATRIZ ADYACENCIA
        for(int i=0;i<this.listaAdyacencia.length;i++){
            this.matrizAdyacencia[0][i]= false;
            this.matrizAdyacencia[i][0]= false;
        }
    }
    @Override
    public Collection<Integer> nodos() {
        HashSet<Integer> conjuntoSolucion=new HashSet<>();
        for (HashSet<Integer> listaNodosVecinos : this.listaAdyacencia) {
            if (listaNodosVecinos != null) {
                conjuntoSolucion.addAll(listaNodosVecinos);
            }
        }
        return Collections.unmodifiableCollection(conjuntoSolucion);
    }


    @Override
    public Boolean sonAdyacentes(int nodo1, int nodo2) {
        return this.matrizAdyacencia[nodo1][nodo2]&&this.matrizAdyacencia[nodo2][nodo1];
    }

    @Override
    public void insertarNodo(int nodo){
        this.listaAdyacencia[nodo]=new HashSet<>();
    }

    @Override
    public void insertarArco(int nodoOrigen, int nodoDestino) {
        this.listaAdyacencia[nodoOrigen].add(nodoDestino);
        this.listaAdyacencia[nodoDestino].add(nodoOrigen);
        this.matrizAdyacencia[nodoOrigen][nodoDestino]=true;
        this.matrizAdyacencia[nodoDestino][nodoOrigen]=true;
    }

    @Override
    public Collection<Integer> nodosVecinos(int nodo) throws RuntimeException{
        if(nodo>this.listaAdyacencia.length){
            throw new RuntimeException("Nodo no existe");
        }
        return this.listaAdyacencia[nodo];
    }
    @Override
    public int tamañoGrafo(){
        return this.numeroNodos;
    }

    @Override
    public int gradoNodo(int nodo) {
        return this.listaAdyacencia[nodo].size();
    }

    @Override
    public String toString(){
        String resultado="";
        for(Integer nodo: this.nodos()){
            for(Integer nodoVecino: this.nodosVecinos(nodo)) {
                resultado=resultado+"Nodo: " + nodo + " ---- Nodo vecino: " + nodoVecino+"\n";
            }
        }
        return resultado;
    }

}
