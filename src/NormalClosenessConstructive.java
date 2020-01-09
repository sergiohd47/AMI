import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Sergio Hernandez Dominguez
 */
public class NormalClosenessConstructive implements Constructive {
    private int numeroNodosEscoger;
    private ArrayList<Pair<Integer,Float>> listaClosenessCompleta;
    private ArrayList<Pair<Integer,Float>> listaClosenessSemilla;
    public NormalClosenessConstructive(int numeroNodosEscoger, ArrayList<Pair<Integer,Float>> listaCloseness){
        this.numeroNodosEscoger=numeroNodosEscoger;
        this.listaClosenessCompleta=listaCloseness;
    }
    @Override
    public HashSet<Integer> construirSolucion(Grafo grafo) {
        HashSet<Integer> solucion=new HashSet<>();
        float valorAlpha=(float)Math.random()*1;
        Pair<Integer,Float> parGMax=this.listaClosenessCompleta.get(0);
        Pair<Integer,Float> parGMin=this.listaClosenessCompleta.get(this.listaClosenessCompleta.size()-1);
        float valorM=parGMax.getValue()-(valorAlpha*(parGMax.getValue()-parGMin.getValue()));
        for(Pair<Integer,Float> par: this.listaClosenessCompleta){
            if(par.getValue()>=valorM){
                solucion.add(par.getKey());
                this.listaClosenessSemilla.add(par);
                if(solucion.size()==this.numeroNodosEscoger){
                    break;
                }
            }
        }
        return solucion;
    }

    public ArrayList<Pair<Integer, Float>> getListaClosenessSemilla() {
        return this.listaClosenessSemilla;
    }
}
