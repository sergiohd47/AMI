import javafx.util.Pair;

import java.util.*;

/**
 * @author Sergio Hernandez Dominguez
 */
public class GradeConstructive implements Constructive {
    private int numeroNodosEscoger;
    private ComparadorGrado comparadorGrado;
    public GradeConstructive(int numeroK){
        this.numeroNodosEscoger=numeroK;
        this.comparadorGrado=new ComparadorGrado();
    }
    @Override
    public HashSet<Integer> construirSolucion(Grafo grafo) throws RuntimeException{
        if(grafo.tamañoGrafo()<this.numeroNodosEscoger){
            throw new RuntimeException("Grafo con longitud menor que numero de semillas.");
        }
        HashSet<Integer> conjuntoSolucion=new HashSet<>();
        ArrayList<Pair<Integer,Integer>> listaNodoGrado=new ArrayList<>();
        for(Integer nodo: grafo.nodos()){
            listaNodoGrado.add(new Pair<>(nodo,grafo.gradoNodo(nodo)));
        }
        Collections.sort(listaNodoGrado,this.comparadorGrado); //SE ORDENA EN FUNCION DEL GRADO, ORDEN: DE MAYOR A MENOR
        for(int i=0;i<this.numeroNodosEscoger;i++){
            conjuntoSolucion.add(listaNodoGrado.get(i).getKey());
        }
        return conjuntoSolucion;
    }

    public HashSet<Integer> construirSolucion(Grafo grafo, float valorAlpha) throws RuntimeException{
        if(grafo.tamañoGrafo()<this.numeroNodosEscoger){
            throw new RuntimeException("Grafo con longitud menor que numero de semillas.");
        }
        HashSet<Integer> conjuntoSolucion=new HashSet<>();
        ArrayList<Pair<Integer,Integer>> listaNodoGrado=new ArrayList<>();
        for(Integer nodo: grafo.nodos()){
            listaNodoGrado.add(new Pair<>(nodo,grafo.gradoNodo(nodo)));
        }
        Collections.sort(listaNodoGrado,this.comparadorGrado); //SE ORDENA EN FUNCION DEL GRADO, ORDEN: DE MAYOR A MENOR
        Random randomPick=new Random();
        Pair<Integer, Integer> nodoEscogidoRandom=listaNodoGrado.get(randomPick.nextInt(listaNodoGrado.size()));
        conjuntoSolucion.add(nodoEscogidoRandom.getKey());
        listaNodoGrado.remove(nodoEscogidoRandom);
        while(true){
            ArrayList<Pair<Integer, Integer>> listaNodoGradoAux=new ArrayList<>();
            Pair<Integer, Integer> parGMax=listaNodoGrado.get(0);
            Pair<Integer, Integer> parGMin=listaNodoGrado.get(listaNodoGrado.size()-1);
            int valorUmbral= (int) (parGMax.getValue() - (valorAlpha * (parGMax.getValue() - parGMin.getValue())));
            for(Pair<Integer,Integer> parNodoGrado: listaNodoGrado){
                if(parNodoGrado.getValue()>valorUmbral){
                    listaNodoGradoAux.add(parNodoGrado);
                }
            }
            Pair<Integer,Integer> parEscogido=listaNodoGradoAux.get(randomPick.nextInt(listaNodoGradoAux.size()));
            conjuntoSolucion.add(parEscogido.getKey());
            listaNodoGrado.remove(parEscogido);
            if(conjuntoSolucion.size()==this.numeroNodosEscoger){
                return conjuntoSolucion;
            }
        }
    }
}
