import javafx.util.Pair;

import java.util.*;

/**
 * @author Sergio Hernandez Dominguez
 */
public class Principal {
    private static final int NODOS_SEMILLA=3; // EN ESTE CASO SE ESCOGEN TRES NODOS SEMILLAS
    //Ruta del fichero pasada a fuego. Mas adelante mejorar para pasar varias rutas diferentes
    private static final String RUTA_FICHERO_PRUEBAS="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/snapPrueba/snapGrafoPrueba.txt";
    private static final String RUTA_FICHERO_REAL="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/snap2/email-Eu-core.txt";

    public static void main(String args[]){
        Instance instance=new Instance();
        ArrayList<Pair<Integer, Integer>> listaNodos=instance.leerFichero(RUTA_FICHERO_PRUEBAS);
        Grafo grafoND=instance.construirGrafo(listaNodos);
        float probabilidadArcos=instance.getProbabilidadArcos();
        //long inicioConstructivo= System.currentTimeMillis(); SE COMENTA  LO REFERENTE A LOS TIEMPOS
        Constructive constructiveRandom=new RandomConstructive(NODOS_SEMILLA);
        //Constructive constructiveGrade=new GradeConstructive(NODOS_SEMILLA); //CREA UN CONJUNTO CON LOS NODOS QUE MAYOR GRADO TIENEN
        //Constructive constructiveCloseness=new ClosenessConstructive(NODOS_SEMILLA); //CREA UN CONJUNTO CON LOS NODOS QUE MAYOR CENTRALIDAD TIENEN
        //ArrayList<Pair<Integer,Float>> listaClosenessSemilla= ((ClosenessConstructive) constructive).getListaClosenessSemilla();
        //ArrayList<Pair<Integer,Float>> listaClosenessCompleta=((ClosenessConstructive)constructive).getListaClosenessCompleta();
        //Constructive normalConstructive=new NormalClosenessConstructive(NODOS_SEMILLA); //CREA UN CONJUNTO CON LOS NODOS QUE MAYOR CENTRALIDAD NORMALIZADA TIENEN
        //ArrayList<Pair<Integer,Float>> listaClosenessSemilla= ((NormalClosenessConstructive) normalConstructive).getListaClosenessSemilla();
        System.out.println("NUMERO NODOS GRAFO: "+grafoND.tamañoGrafo());
        HashSet<Integer> conjuntoNodosSemilla= constructiveRandom.construirSolucion(grafoND);
        //HashSet<Integer> conjuntoNodosSemilla= normalConstructive.construirSolucion(grafoND); //CONJUNTO SEMILLA (en ultima fase: CONJUNTO NODOS CANDIDATOS A ENTRAR)
        Solution solution=new Solution(grafoND,conjuntoNodosSemilla,probabilidadArcos);
        //Improvement randomImprovement=new RandomImprovement();
        //randomImprovement.improve(solution);
        //Improvement closenessImprovement=new ClosenessImprovement();
        //closenessImprovement.improve(solution);
        Improvement betaImprovement=new BetaImprovement();
        betaImprovement.improve(solution);
        /*double tiempoConstructivo=(double)finalConstructivo-inicioConstructivo;
        System.out.println("TIEMPO CONSTRUCTIVO: "+tiempoConstructivo);
        double tiempoSolucion=(double)finalSolucion-inicioSolucion;
        System.out.println("TIEMPO SOLUCION: "+tiempoSolucion);
        */

    }
}
