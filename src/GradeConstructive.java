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
        Collections.sort(listaNodoGrado,this.comparadorGrado); //SE ORDENA EN FUNCION DEL GRADO, ORDEN: DE MENOR A MAYOR
        Collections.reverse(listaNodoGrado);
        for(int i=0;i<this.numeroNodosEscoger;i++){
            conjuntoSolucion.add(listaNodoGrado.get(i).getKey());
        }
        return conjuntoSolucion;
    }
}
