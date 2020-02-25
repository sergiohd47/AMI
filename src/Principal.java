import javafx.util.Pair;

import java.util.*;

/**
 * @author Sergio Hernandez Dominguez
 */
public class Principal {
    private static final int NODOS_SEMILLA=3; // EN ESTE CASO SE ESCOGEN TRES NODOS SEMILLAS
    private static final int NUMERO_SIMULACIONES_SOLUTION=30;
    private static final int NUMERO_SIMULACIONES_EXPERIMENTO=100;

    //              RUTAS SNAP
    //      FORMATO .TXT
    private static final String RUTA_COLLEGEMSG_1899="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/formatoTXT/snapCollegeMsg/CollegeMsg.txt";
    private static final String RUTA_EMAILEU_1005="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/formatoTXT/snapEmailEU/email-Eu-core.txt";
    private static final String RUTA_FACEBOOKCOMB_4039="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/formatoTXT/snapFacebComb/facebook_combined.txt";
    private static final String RUTA_GNUTELLA5_8846="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/formatoTXT/snapGNutella5/p2p-Gnutella05.txt";
    private static final String RUTA_GNUTELLA6_8717="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/formatoTXT/snapGNutella6/p2p-Gnutella06.txt";
    private static final String RUTA_GNUTELLA8_6301="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/formatoTXT/snapGNutella8/p2p-Gnutella08.txt";
    private static final String RUTA_GNUTELLA9_8114="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/formatoTXT/snapGNutella9/p2p-Gnutella09.txt";
    private static final String RUTA_GRQC_5242="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/formatoTXT/snapGRQC/CA-GrQc.txt";
    private static final String RUTA_WIKIVOTE_7115="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/formatoTXT/snapWikiVote/Wiki-Vote.txt";
    //      FORMATO .CSV
    private static final String RUTA_BITCOINOTC_5881="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/formatoCSV/snapBitcoinOTC/soc-sign-bitcoinotc.csv";
    private static final String RUTA_BITCOINALPHA_3783="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/formatoCSV/snapBitcoinAlpha/soc-sign-bitcoinalpha.csv";
    private static final String RUTA_GOVERMENT_7057="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/formatoCSV/snapGoverment/government_edges.csv";
    private static final String RUTA_TVSHOW_3892="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/formatoCSV/snapTVShow/tvshow_edges.csv";
    private static final String RUTA_POLITICIAN_5908="/Users/sergiohernandezdominguez/Desktop/universidad/TFG/SNAP/formatoCSV/snapPolitician/politician_edges.csv";


    public static void main(String args[]){
        //              INSTANCE
        HashMap<Integer,ArrayList<HashSet<Integer>>> mapaPromedioConjunto=new HashMap<>();
        int promedio=Integer.MIN_VALUE;
        ArrayList<HashSet<Integer>> lista=new ArrayList<>();
        mapaPromedioConjunto.put(promedio, lista);

        long inicioInstance=System.currentTimeMillis();
        Instance instance=new Instance();
        ArrayList<Pair<Integer, Integer>> listaNodos=instance.leerFichero(RUTA_GOVERMENT_7057);
        Grafo grafoND=instance.construirGrafo(listaNodos);
        System.out.println("NUMERO NODOS GRAFO: "+grafoND.tamañoGrafo());
        float probabilidadArcos=instance.getProbabilidadArcos();
        long finalInstance=System.currentTimeMillis();

        long inicioConstructivo=0;
        long finalConstructivo=0;
        //              CONSTRUCTIVOS
        for(int i=1;i<=NUMERO_SIMULACIONES_EXPERIMENTO;i++) {
            inicioConstructivo = System.currentTimeMillis();
            RandomConstructive constructiveRandom = new RandomConstructive(NODOS_SEMILLA);
            //GradeConstructive constructiveGrade=new GradeConstructive(NODOS_SEMILLA); //CREA UN CONJUNTO CON LOS NODOS QUE MAYOR GRADO TIENEN
            //ClosenessConstructive constructiveCloseness = new ClosenessConstructive(NODOS_SEMILLA); //CREA UN CONJUNTO CON LOS NODOS QUE MAYOR CENTRALIDAD TIENEN
            //NormalClosenessConstructive normalConstructive=new NormalClosenessConstructive(NODOS_SEMILLA); //CREA UN CONJUNTO CON LOS NODOS QUE MAYOR CENTRALIDAD NORMALIZADA TIENEN
            //HashSet<Integer> conjuntoNodosSemilla=constructiveCloseness.construirSolucion(grafoND);
            //HashSet<Integer> conjuntoNodosSemilla=normalConstructive.construirSolucion(grafoND);
            HashSet<Integer> conjuntoNodosSemilla = constructiveRandom.construirSolucion(grafoND);
            //HashSet<Integer> conjuntoNodosSemilla= normalConstructive.construirSolucion(grafoND); //CONJUNTO SEMILLA (en ultima fase: CONJUNTO NODOS CANDIDATOS A ENTRAR)
            finalConstructivo = System.currentTimeMillis();

            //ArrayList<Pair<Integer,Float>> listaClosenessSemilla= constructiveCloseness.getListaClosenessSemilla();
            //ArrayList<Pair<Integer,Float>> listaClosenessSemilla= ((NormalClosenessConstructive) normalConstructive).getListaClosenessSemilla();

            //              SOLUTION
            long inicioSolution = 0;
            long finalSolution = 0;
            int promedioInfeccion = 0;
            Solution solution = new Solution(grafoND, conjuntoNodosSemilla, probabilidadArcos);
            for (int j = 1; i <= NUMERO_SIMULACIONES_SOLUTION; i++) {
                System.out.println("------ SOLUCION " + j + " -------------");
                System.out.println("CONJUNTO SEMILLAS: " + conjuntoNodosSemilla);
                inicioSolution = inicioSolution + System.currentTimeMillis();
                HashSet<Integer> conjuntoInfectados = solution.procedimientoCascada();
                promedioInfeccion = promedioInfeccion + conjuntoInfectados.size();
                finalSolution = finalSolution + System.currentTimeMillis();
                System.out.println("CONJUNTO INFECTADOS: " + conjuntoInfectados);
                System.out.println("LONGITUD CONJ. INFECTADOS: " + conjuntoInfectados.size());
                System.out.println("-----------------------------------");
            }
            System.out.println("PROMEDIO INFECCION: " + promedioInfeccion / NUMERO_SIMULACIONES_SOLUTION);
            ArrayList<HashSet<Integer>> listaAux=mapaPromedioConjunto.get(promedioInfeccion);
            if(listaAux==null){
                listaAux=new ArrayList<>();
                listaAux.add(conjuntoNodosSemilla);
                mapaPromedioConjunto.put(promedioInfeccion,listaAux);
            }else {
                listaAux.add(conjuntoNodosSemilla);
            }
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
        System.out.println("TIEMPO INSTANCIA: "+tiempoInstancia/1000);

        double tiempoConstructivo=(double)finalConstructivo-inicioConstructivo;
        System.out.println("TIEMPO CONSTRUCTIVO: "+tiempoConstructivo/1000);

        //double tiempoSolucion=randomImprovement.getTiempoSolucion();
        //System.out.println("TIEMPO SOLUCION: "+tiempoSolucion);
        //double tiempoSolucion=closenessImprovement.getTiempoSolucion();
        //System.out.println("TIEMPO SOLUCION: "+tiempoSolucion);
        //double tiempoSolucion=betaImprovement.getTiempoSolucion();
       // System.out.println("TIEMPO SOLUCION: "+tiempoSolucion);

        double tiempoSolution=(double)finalSolution-inicioSolution;
        System.out.println("TIEMPO SOLUTION: "+tiempoSolution/1000);

        double tiempoMejoras=(double)finalImprovement-inicioImprovement;
        System.out.println("TIEMPO MEJORAS: "+tiempoMejoras/1000);


    }
}
