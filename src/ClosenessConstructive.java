import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * @author Sergio Hernandez Dominguez
 */
public class ClosenessConstructive implements Constructive {
    private int numeroNodosEscoger;
    private ComparadorCloseness comparadorCentralidad;
    private ArrayList<Pair<Integer,Float>> listaCloseness;
    public ClosenessConstructive(int numeroNodosEscoger){
        this.numeroNodosEscoger=numeroNodosEscoger;
        this.comparadorCentralidad=new ComparadorCloseness();
        this.listaCloseness=new ArrayList<>();
    }
    @Override
    public HashSet<Integer> construirSolucion(Grafo grafo) {
        if(grafo.tamañoGrafo()<this.numeroNodosEscoger){
            throw new RuntimeException("Grafo con longitud menor que numero de semillas.");
        }
        HashSet<Integer> conjuntoSolucion=new HashSet<>();
        ArrayList<Pair<Integer,Float>> listaNodosCC=new ArrayList<>();
        for(Integer nodo: grafo.nodos()){
            listaNodosCC.add(new Pair<>(nodo,closenessCentrality(grafo,nodo)));
        }
        Collections.sort(listaNodosCC,this.comparadorCentralidad); //SE ORDENA EN FUNCION DE LA CENTRALIDAD, EN ORDEN: DE MENOR A MAYOR
        Collections.reverse(listaNodosCC);
        for(int i=0;i<this.numeroNodosEscoger;i++){
            conjuntoSolucion.add(listaNodosCC.get(i).getKey());
            this.listaCloseness.add(listaNodosCC.get(i));
        }
        return conjuntoSolucion;
    }

    /*
       EN ESTE METODO SE USA EL METODO DE GRAFOS distanciaEntreNodos, EL CUAL NO FUNCIONA CORRECTAMENTE. CUANDO SE CONSIGA HACER FUNCIONAR, SE USA ESTE.
       private Float closenessCentrality(Grafo grafo, Integer nodo) {
        int valorDistancia=0;
        for(Integer nodoAux: grafo.nodos()){
            if(nodo==nodoAux){
                continue;
            }
            valorDistancia=valorDistancia+grafo.distanciaEntreNodos(nodo,nodoAux);
        }
        return (float)1/valorDistancia;
    }
     */
    private Float closenessCentrality(Grafo grafo, int nodo){
        int valorDistancia=0;
        int[] distancias=grafo.distanciaANodos(nodo);
        for(int distanciaNodo: distancias){
            valorDistancia=valorDistancia+distanciaNodo;
        }
        return (float)1/valorDistancia;
    }

    public ArrayList<Pair<Integer, Float>> getListaCloseness() {
        return listaCloseness;
    }
}
