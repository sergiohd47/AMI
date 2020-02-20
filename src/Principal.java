import javafx.util.Pair;

import java.util.*;

/**
 * @author Sergio Hernandez Dominguez
 */
public class Principal {
    private static final int NODOS_SEMILLA=3; // EN ESTE CASO SE ESCOGEN TRES NODOS SEMILLAS
    private static final int NUMERO_SIMULACIONES=30;
    //Ruta del fichero pasada a fuego. Mas adelante mejorar para pasar varias rutas diferentes
    private static final String RUTA_FICHERO_PRUEBAS="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/formatoTXT/snapPrueba/snapGrafoPrueba.txt";
    private static final String RUTA_FICHERO_PRUEBAS_CSV="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/formatoCSV/snapPrueba/snapPrueba.csv";




    public static void main(String args[]){
        //              INSTANCE
        long inicioInstance=System.currentTimeMillis();
        Instance instance=new Instance();
        ArrayList<Pair<Integer, Integer>> listaNodos=instance.leerFichero(RUTA_FICHERO_PRUEBAS_CSV);
        System.out.println(listaNodos);
        Grafo grafoND=instance.construirGrafo(listaNodos);
        System.out.println("NUMERO NODOS GRAFO: "+grafoND.tamañoGrafo());
        float probabilidadArcos=instance.getProbabilidadArcos();
        long finalInstance=System.currentTimeMillis();

        //              CONSTRUCTIVOS
        long inicioConstructivo= System.currentTimeMillis();
        RandomConstructive constructiveRandom=new RandomConstructive(NODOS_SEMILLA);
        //GradeConstructive constructiveGrade=new GradeConstructive(NODOS_SEMILLA); //CREA UN CONJUNTO CON LOS NODOS QUE MAYOR GRADO TIENEN
        ClosenessConstructive constructiveCloseness=new ClosenessConstructive(NODOS_SEMILLA); //CREA UN CONJUNTO CON LOS NODOS QUE MAYOR CENTRALIDAD TIENEN
        //NormalClosenessConstructive normalConstructive=new NormalClosenessConstructive(NODOS_SEMILLA); //CREA UN CONJUNTO CON LOS NODOS QUE MAYOR CENTRALIDAD NORMALIZADA TIENEN
        //HashSet<Integer> conjuntoNodosSemilla=constructiveCloseness.construirSolucion(grafoND);
        //HashSet<Integer> conjuntoNodosSemilla=normalConstructive.construirSolucion(grafoND);
        HashSet<Integer> conjuntoNodosSemilla= constructiveRandom.construirSolucion(grafoND);
        //HashSet<Integer> conjuntoNodosSemilla= normalConstructive.construirSolucion(grafoND); //CONJUNTO SEMILLA (en ultima fase: CONJUNTO NODOS CANDIDATOS A ENTRAR)
        long finalConstructivo=System.currentTimeMillis();

        //ArrayList<Pair<Integer,Float>> listaClosenessSemilla= constructiveCloseness.getListaClosenessSemilla();
        //ArrayList<Pair<Integer,Float>> listaClosenessSemilla= ((NormalClosenessConstructive) normalConstructive).getListaClosenessSemilla();

        //              SOLUTION
        Solution solution=new Solution(grafoND,conjuntoNodosSemilla,probabilidadArcos);
        for (int i=1;i<=NUMERO_SIMULACIONES;i++) {
            System.out.println("CONJUNTO SEMILLAS: "+conjuntoNodosSemilla);
            HashSet<Integer> conjuntoInfectados = solution.procedimientoCascada();
            System.out.println("CONJUNTO INFECTADOS: "+conjuntoInfectados);

        }

        //              IMPROVEMENTS
        long inicioImprovement=System.currentTimeMillis();
        //Improvement randomImprovement=new RandomImprovement();
        //randomImprovement.improve(solution);
        //Improvement closenessImprovement=new ClosenessImprovement();
        //closenessImprovement.improve(solution);
        //Improvement betaImprovement=new BetaImprovement();
        //betaImprovement.improve(solution);
        long finalImprovement=System.currentTimeMillis();

        //              TIEMPOS
        double tiempoInstancia=(double)finalInstance-inicioInstance;
        System.out.println("TIEMPO INSTANCIA: "+tiempoInstancia);

        double tiempoConstructivo=(double)finalConstructivo-inicioConstructivo;
        System.out.println("TIEMPO CONSTRUCTIVO: "+tiempoConstructivo);

        //double tiempoSolucion=randomImprovement.getTiempoSolucion();
        //System.out.println("TIEMPO SOLUCION: "+tiempoSolucion);
        //double tiempoSolucion=closenessImprovement.getTiempoSolucion();
        //System.out.println("TIEMPO SOLUCION: "+tiempoSolucion);
        //double tiempoSolucion=betaImprovement.getTiempoSolucion();
       // System.out.println("TIEMPO SOLUCION: "+tiempoSolucion);

        double tiempoMejoras=(double)finalImprovement-inicioImprovement;
        System.out.println("TIEMPO MEJORAS: "+tiempoMejoras);


    }
}
