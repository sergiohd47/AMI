import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
/**
 * @author Sergio Hernandez Dominguez
 */
public class Principal {
    private static final int NODOS_SEMILLA=3; // EN ESTE CASO SE ESCOGEN TRES NODOS SEMILLAS
    //Ruta del fichero pasada a fuego. Mas adelante mejorar para pasar varias rutas diferentes
    private static final String RUTA_FICHERO="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/snapPrueba/snapGrafoPrueba.txt";

    public static void main(String args[]){
        Instance instance=new Instance();
        ArrayList<Pair<Integer, Integer>> listaNodos=instance.leerFichero(RUTA_FICHERO);
        Grafo grafoND=instance.construirGrafo(listaNodos);
        float probabilidadArcos=instance.getProbabilidadArcos();
        for(int i=1;i<31;i++) {
            Constructive constructive=new RandomConstructive(NODOS_SEMILLA);
            HashSet<Integer> conjuntoNodosSemilla= constructive.construirSolucion(grafoND);
            Solution solucion = new Solution();
            HashSet<Integer> conjuntoInfectados = solucion.procedimientoCascada(grafoND, conjuntoNodosSemilla, probabilidadArcos);
            System.out.println("CONJUNTO SEMILLA: "+conjuntoNodosSemilla);
            System.out.println(i+" TAMAÑO INFECTADOS: " + conjuntoInfectados.size());
            System.out.println("CONJUNTO INFECTADOS: "+conjuntoInfectados);
            System.out.println("------------------------------------------");
        }
    }
}
