import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

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
        //System.out.println("LISTA CLOSENESS INICIAL: ");
        //System.out.println(listaCloseness);
        Random randomPick=new Random();
        Pair<Integer,Float> parEscogidoRandom=listaCloseness.get(randomPick.nextInt(listaCloseness.size()));
        //System.out.println("Primer par escogido: "+parEscogidoRandom);
        solucion.add(parEscogidoRandom.getKey());
        this.listaClosenessSemilla.add(parEscogidoRandom);
        listaCloseness.remove(parEscogidoRandom);
        //System.out.println("LISTA CLOSENESS ANTES BUCLE: ");
        //System.out.println(listaCloseness);
        //System.out.println("----------");
        while(true) {
            //System.out.println("BUCLE");
            ArrayList<Pair<Integer,Float>> listaClosenessAuxiliar=new ArrayList<>();
            float valorAlpha = (float) Math.random();
            Pair<Integer, Float> parGMax = listaCloseness.get(0);
            //System.out.println("PAR GMAX: "+parGMax);
            Pair<Integer, Float> parGMin = listaCloseness.get(listaCloseness.size() - 1);
            //System.out.println("PAR GMIN: "+parGMin);
            float valorUmbral = parGMax.getValue() - (valorAlpha * (parGMax.getValue() - parGMin.getValue()));
            //System.out.println("VALOR UMBRAL: "+valorUmbral);
            //System.out.println("---------");
            //System.out.println("BUCLE LISTA AUX");
            for(Pair<Integer,Float> par: listaCloseness){
                if(par.getValue()>valorUmbral){
                    listaClosenessAuxiliar.add(par);
                }
            }
            //System.out.println("LISTA CLOSENESS AUX");
            //System.out.println(listaClosenessAuxiliar);
            Pair<Integer,Float> parEscogido=listaClosenessAuxiliar.get(randomPick.nextInt(listaClosenessAuxiliar.size()));
            //System.out.println("PAR ESCOGIDO RANDOM: "+parEscogido);
            solucion.add(parEscogido.getKey());
            listaCloseness.remove(parEscogido);
            //System.out.println("LISTA CLOSENESS TRAS BORRADO");
            //System.out.println(listaCloseness);
            this.listaClosenessSemilla.add(parEscogido);
            if(solucion.size()==this.numeroNodosEscoger){
                return solucion;
            }
        }
    }



    private ArrayList<Pair<Integer, Float>> construirListaCloseness(Grafo grafo) {
        ArrayList<Pair<Integer, Float>> listaNodosCC=new ArrayList<>();
        for(Integer nodo: grafo.nodos()){
            listaNodosCC.add(new Pair<>(nodo,closenessCentrality(grafo,nodo)));
        }
        Collections.sort(listaNodosCC,this.comparadorCentralidad); //SE ORDENA EN FUNCION DE LA CENTRALIDAD, EN ORDEN: DE MAYOR A MENOR
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
