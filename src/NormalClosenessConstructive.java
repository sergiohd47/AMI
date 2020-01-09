import javafx.util.Pair;

import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * @author Sergio Hernandez Dominguez
 */
public class NormalClosenessConstructive implements Constructive {
    private int numeroNodosEscoger;
    private ArrayList<Pair<Integer,Float>> listaClosenessSemilla;
    private ComparadorCloseness comparadorCentralidad;
    public NormalClosenessConstructive(int numeroNodosEscoger){
        this.numeroNodosEscoger=numeroNodosEscoger;
        this.listaClosenessSemilla=new ArrayList<>();
        this.comparadorCentralidad=new ComparadorCloseness();
    }
    @Override
    public HashSet<Integer> construirSolucion(Grafo grafo) {
        HashSet<Integer> solucion=new HashSet<>();
        ArrayList<Pair<Integer,Float>> listaCloseness=construirListaCloseness(grafo);
        for(int i=0;i<this.numeroNodosEscoger;i++) {
            float valorAlpha = (float) Math.random() * 1;
            Pair<Integer, Float> parGMax = listaCloseness.remove(0);
            Pair<Integer, Float> parGMin = listaCloseness.get(listaCloseness.size() - 1);
            float valorUmbral = parGMax.getValue() - (valorAlpha * (parGMax.getValue() - parGMin.getValue()));
            for (Pair<Integer, Float> par : listaCloseness) {
                if (par.getValue() >= valorUmbral) {
                    solucion.add(par.getKey());
                    this.listaClosenessSemilla.add(par);
                    break;
                }
            }
        }
        return solucion;
    }


    private ArrayList<Pair<Integer, Float>> construirListaCloseness(Grafo grafo) {
        ArrayList<Pair<Integer, Float>> listaNodosCC=new ArrayList<>();
        for(Integer nodo: grafo.nodos()){
            listaNodosCC.add(new Pair<>(nodo,closenessCentrality(grafo,nodo)));
        }
        Collections.sort(listaNodosCC,this.comparadorCentralidad); //SE ORDENA EN FUNCION DE LA CENTRALIDAD, EN ORDEN: DE MENOR A MAYOR
        Collections.reverse(listaNodosCC);
        return listaNodosCC;
    }

    private Float closenessCentrality(Grafo grafo, Integer nodo) {
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
}
