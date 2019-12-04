import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;

public class Principal {
    private static final int NODOS_SEMILLA=3; // EN ESTE CASO SE ESCOGEN TRES NODOS SEMILLAS
    //Ruta del fichero pasada a fuego. Mas adelante mejorar para pasar varias rutas diferentes
    private static final String RUTA_FICHERO="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/snapPrueba/snapGrafoPrueba.txt";

    public static void main(String args[]){
        Instance instance=new Instance();
        ArrayList<Pair<Integer, Integer>> listaNodos=instance.leerFichero(RUTA_FICHERO);
        Grafo<Integer, Float> grafoND=new GrafoND<>();
        grafoND=instance.construirGrafo(listaNodos);
        Constructive constructive=new RandomConstructive(NODOS_SEMILLA);
        HashSet<Nodo<Integer>> conjuntoNodosSemilla= constructive.construirSolucion(grafoND);
        Solution solucion=new Solution();
        float probSolucion=solucion.getProbabilidadSolucion();
        HashSet<Nodo<Integer>> conjuntoInfectados=solucion.procedimientoCascada(grafoND,conjuntoNodosSemilla);
        /*System.out.println("-------------------");
        System.out.println("Numero nodos: "+grafoND.nodos().size());
        System.out.println("-------------------");
        System.out.println("Numero arcos: "+grafoND.arcos().size());
        System.out.println("-------------------");
        System.out.println("CONJUNTO SEMILLA");
        for(Nodo<Integer> nodo: conjuntoNodosSemilla){
            System.out.println(nodo);
        }
        System.out.println("-------------------");
        System.out.println("NODOS GRAFO");
        for(Nodo<Integer> nodo: grafoND.nodos()){
            System.out.println(nodo);
        }
         */
        System.out.println("-------------------");
        System.out.println("ARCOS GRAFO");
        for(Arco<Float,Integer> arco: grafoND.arcos()){
            System.out.println(arco);
        }
        System.out.println("-------------------");
        System.out.println("PROBABILIDAD SOLUCION");
        System.out.println(probSolucion);
        System.out.println("-------------------");
        /*System.out.println("NODOS INFECTADOS");
        System.out.println("Numero nodos: "+conjuntoInfectados.size());
        System.out.println("-------------------");
        for(Nodo<Integer> nodo: conjuntoInfectados){
            System.out.println(nodo);
        }

         */
        //PROBANDO GITHUB



    }
}
