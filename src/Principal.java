import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Sergio Hernandez Dominguez
 */
public class Principal {
    private static final int NODOS_SEMILLA=3; // EN ESTE CASO SE ESCOGEN TRES NODOS SEMILLAS
    //Ruta del fichero pasada a fuego. Mas adelante mejorar para pasar varias rutas diferentes
    private static final String RUTA_FICHERO="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/snapPrueba/snapGrafoPrueba.txt";

    public static void main(String args[]){
        HashMap<Integer,HashSet<Integer>> mapaConjuntos=new HashMap<>();
        Instance instance=new Instance();
        ArrayList<Pair<Integer, Integer>> listaNodos=instance.leerFichero(RUTA_FICHERO);
        Grafo grafoND=instance.construirGrafo(listaNodos);
        float probabilidadArcos=instance.getProbabilidadArcos();
        Constructive constructive=new RandomConstructive(NODOS_SEMILLA);
        HashSet<Integer> conjuntoNodosSemilla= constructive.construirSolucion(grafoND);
        for(int i=1;i<31;i++) {
            Solution solucion = new Solution();
            HashSet<Integer> conjuntoInfectados = solucion.procedimientoCascada(grafoND, conjuntoNodosSemilla, probabilidadArcos);
            mapaConjuntos.put(i,conjuntoInfectados);
            System.out.println("SOLUCION "+i);
            System.out.println("Conjunto semilla: "+conjuntoNodosSemilla);
            System.out.println("Tamaño conj: "+conjuntoInfectados.size());
            System.out.println("Conjunto infectados: "+conjuntoInfectados);
            System.out.println("------------------");
        }
        int numeroConj10=0;
        int numeroConj3=0;
        int numeroConjCualq=0;
        for(Integer clave: mapaConjuntos.keySet()){
            if(mapaConjuntos.get(clave).size()==10){
                numeroConj10++;
            }else if(mapaConjuntos.get(clave).size()==3){
                numeroConj3++;
            }else{
                numeroConjCualq++;
            }
        }
        System.out.println("Porcentaje longitud conjunto=3: "+(float)(numeroConj3*100)/30+"%");
        System.out.println("Porcentaje longitud conjunto=10: "+(float)(numeroConj10*100)/30+"%");
        System.out.println("Porcentaje longitud conjunto cualquiera: "+(float)(numeroConjCualq*100)/30+"%");
    }
}
