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
    private ArrayList<Pair<Integer,Float>> listaClosenessSemilla;
    private ArrayList<Pair<Integer,Float>> listaClosenessCompleta;
    public ClosenessConstructive(int numeroNodosEscoger){
        this.numeroNodosEscoger=numeroNodosEscoger;
        this.comparadorCentralidad=new ComparadorCloseness();
        this.listaClosenessSemilla=new ArrayList<>();
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
        Collections.sort(listaNodosCC,this.comparadorCentralidad); //SE ORDENA EN FUNCION DE LA CENTRALIDAD, EN ORDEN: DE MAYOR A MENOR
        this.listaClosenessCompleta.addAll(listaNodosCC);
        for(int i=0;i<this.numeroNodosEscoger;i++){
            conjuntoSolucion.add(listaNodosCC.get(i).getKey());
            this.listaClosenessSemilla.add(listaNodosCC.get(i));
        }
        return conjuntoSolucion;
    }
    private Float closenessCentrality(Grafo grafo, int nodo){
        int valorDistancia=0;
        int[] distancias=grafo.distanciaANodos(nodo);
        for(int distanciaNodo: distancias){
            valorDistancia=valorDistancia+distanciaNodo;
        }
        return (float)1/valorDistancia;
    }

    public ArrayList<Pair<Integer, Float>> getListaClosenessSemilla() {
        return this.listaClosenessSemilla;
    }
    public ArrayList<Pair<Integer, Float>> getListaClosenessCompleta() {
        return this.listaClosenessCompleta;
    }

}
