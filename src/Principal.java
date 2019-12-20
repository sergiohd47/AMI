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
    private static final String RUTA_FICHERO_PRUEBAS="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/snapPrueba/snapGrafoPrueba.txt";
    private static final String RUTA_FICHERO_REAL="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/snap2/email-Eu-core.txt";

    public static void main(String args[]){
        HashMap<Integer,HashSet<Integer>> mapaConjuntos=new HashMap<>();
        Instance instance=new Instance();
        ArrayList<Pair<Integer, Integer>> listaNodos=instance.leerFichero(RUTA_FICHERO_PRUEBAS);
        Grafo grafoND=instance.construirGrafo(listaNodos);
        float probabilidadArcos=instance.getProbabilidadArcos();
        long inicioConstructivo= System.currentTimeMillis();
        Constructive constructive=new RandomConstructive(NODOS_SEMILLA);
        System.out.println("NUMERO NODOS GRAFO: "+grafoND.tamañoGrafo());
        HashSet<Integer> conjuntoNodosSemilla= constructive.construirSolucion(grafoND);
        long finalConstructivo=System.currentTimeMillis();
        long inicioSolucion=System.currentTimeMillis();
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
        long finalSolucion=System.currentTimeMillis();
        /*
        int numeroConj3=0;
        int numeroConj4=0;
        int numeroConj5=0;
        int numeroConj6=0;
        int numeroConj7=0;
        int numeroConj8=0;
        int numeroConj9=0;
        int numeroConj10=0;
         */

        int promedioLongitudInfectados=0;
        for(Integer clave: mapaConjuntos.keySet()){
            promedioLongitudInfectados=promedioLongitudInfectados+mapaConjuntos.get(clave).size();
            /*
            switch (mapaConjuntos.get(clave).size()){
                case 3:
                    numeroConj3++;
                    break;
                case 4:
                    numeroConj4++;
                    break;
                case 5:
                    numeroConj5++;
                    break;
                case 6:
                    numeroConj6++;
                    break;
                case 7:
                    numeroConj7++;
                    break;
                case 8:
                    numeroConj8++;
                    break;
                case 9:
                    numeroConj9++;
                    break;
                default:
                    numeroConj10++;
            }
             */
        }
        double tiempoConstructivo=(double)finalConstructivo-inicioConstructivo;
        System.out.println("TIEMPO CONSTRUCTIVO: "+tiempoConstructivo);
        double tiempoSolucion=(double)finalSolucion-inicioSolucion;
        System.out.println("TIEMPO SOLUCION: "+tiempoSolucion);
        /*
        System.out.println("Porcentaje longitud conjunto=3: ("+numeroConj3+"/30)  "+(float)(numeroConj3*100)/30+"%");
        System.out.println("Porcentaje longitud conjunto=4: ("+numeroConj4+"/30)  "+(float)(numeroConj4*100)/30+"%");
        System.out.println("Porcentaje longitud conjunto=5: ("+numeroConj5+"/30)  "+(float)(numeroConj5*100)/30+"%");
        System.out.println("Porcentaje longitud conjunto=6: ("+numeroConj6+"/30)  "+(float)(numeroConj6*100)/30+"%");
        System.out.println("Porcentaje longitud conjunto=7: ("+numeroConj7+"/30)  "+(float)(numeroConj7*100)/30+"%");
        System.out.println("Porcentaje longitud conjunto=8: ("+numeroConj8+"/30)  "+(float)(numeroConj8*100)/30+"%");
        System.out.println("Porcentaje longitud conjunto=9: ("+numeroConj9+"/30)  "+(float)(numeroConj9*100)/30+"%");
        System.out.println("Porcentaje longitud conjunto=10: ("+numeroConj10+"/30)  "+(float)(numeroConj10*100)/30+"%");
         */
        System.out.println("PROMEDIO INFECTADOS: "+promedioLongitudInfectados/30);

    }
}
