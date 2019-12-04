import javafx.util.Pair;

import java.util.*;

//GRAFO NO DIRIGIDO
public class GrafoND implements Grafo{
    //Esta implementacion de grafos se basa en una lista de adyacencia y en una matriz de adyacencia.
    private ArrayList<Integer>[] listaAdyacencia;
    private boolean[][] matrizAdyacencia;

    public GrafoND(){
        this.listaAdyacencia= (ArrayList<Integer>[])new Object();
        for(int i=0;i<this.listaAdyacencia.length;i++){ //EL CERO ESTARA SIEMPRE A NULL PARA DIFERENCIARLO DE LOS DEMAS NODOS (NO HAY NODO 0)
            this.listaAdyacencia[i]=null;
        }
        this.matrizAdyacencia= (boolean[][]) new Object();
        for(int i=0;i<this.listaAdyacencia.length;i++){
            this.matrizAdyacencia[0][i]= false;
            this.matrizAdyacencia[i][0]= false;
        }
    }
    @Override
    public Collection<Integer> nodos() {
        HashSet<Integer> conjuntoSolucion=new HashSet<>();
        for(int i=0;i<this.listaAdyacencia.length;i++){
            if(this.listaAdyacencia[i]!=null) {
                conjuntoSolucion.addAll(this.listaAdyacencia[i]);
            }
        }
        return conjuntoSolucion;
    }

    @Override
    public Boolean sonAdyacentes(int nodo1, int nodo2) {
        return this.matrizAdyacencia[nodo1][nodo2]&&this.matrizAdyacencia[nodo2][nodo1];
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
        if(this.listaAdyacencia[nodo]==null){
            throw new RuntimeException("Nodo no existe");
        }
        return this.listaAdyacencia[nodo];
    }
}
