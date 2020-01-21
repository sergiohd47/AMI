import javafx.util.Pair;
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
        System.out.println("LISTA CLOSENESS COMPLETA");
        System.out.println(listaCloseness);
        System.out.println("---");
        while(true) {
            float valorAlpha = (float) Math.random() * 1;
            Pair<Integer, Float> parGMax = listaCloseness.get(0);
            System.out.println("EJECUCION PASO A PASO");
            System.out.println("Valor alpha: "+valorAlpha);
            System.out.println("ParGMax: "+parGMax);
            Pair<Integer, Float> parGMin = listaCloseness.get(listaCloseness.size() - 1);
            System.out.println("ParGMin: "+parGMin);
            float valorUmbral = parGMax.getValue() - (valorAlpha * (parGMax.getValue() - parGMin.getValue()));
            System.out.println("Valor umbral: "+valorUmbral);
            for (Pair<Integer, Float> par : listaCloseness) {
                if (par.getValue() >= valorUmbral) {
                    solucion.add(par.getKey());
                    Pair<Integer,Float> parBorrado=listaCloseness.remove(0);
                    System.out.println("Semilla introducida: "+par);
                    System.out.println("Par borrado lista: "+parBorrado);
                    System.out.println();
                    this.listaClosenessSemilla.add(par);
                    if(solucion.size()==this.numeroNodosEscoger){
                        return solucion;
                    }
                    break;
                }
            }
        }
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
