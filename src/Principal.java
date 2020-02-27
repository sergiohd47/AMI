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

        //long inicioInstance=System.currentTimeMillis();
        Instance instance=new Instance();
        ArrayList<Pair<Integer, Integer>> listaNodos=instance.leerFichero(RUTA_BITCOINOTC_5881);
        Grafo grafoND=instance.construirGrafo(listaNodos);
        System.out.println("NUMERO NODOS GRAFO: "+grafoND.tamañoGrafo());

        //long finalInstance=System.currentTimeMillis();

        double tiempoConstructivo=0;
        double tiempoSolution=0;
        long inicioConstructivo;
        long finalConstructivo;
        long inicioSolution;
        long finalSolution;

        inicioConstructivo = System.currentTimeMillis();
        //ClosenessConstructive constructiveCloseness = new ClosenessConstructive(NODOS_SEMILLA);
        GradeConstructive constructiveGrade=new GradeConstructive(NODOS_SEMILLA);
        //HashSet<Integer> conjuntoNodosSemilla=constructiveCloseness.construirSolucion(grafoND);
        HashSet<Integer> conjuntoNodosSemilla=constructiveGrade.construirSolucion(grafoND);
        finalConstructivo = System.currentTimeMillis();
        //System.out.println("CONJUNTO SEMILLA : "+conjuntoNodosSemilla);
        tiempoConstructivo=tiempoConstructivo+(double)(finalConstructivo-inicioConstructivo);

        //              CONSTRUCTIVOS
        for(int i=1;i<=NUMERO_SIMULACIONES_EXPERIMENTO;i++) {
            System.out.println("------ SIMULACION " + i + " -------------");
            float probabilidadArcos= (float) 0.25;
            //float probabilidadArcos= (float) 0.5;
            //float probabilidadArcos= (float) 0.75;
            //float probabilidadArcos=(float)Math.random(); //numero aleatorio entre 0 y 1 (simula la probabilidad) (7 decimales)

            //inicioConstructivo = System.currentTimeMillis();
            //RandomConstructive constructiveRandom = new RandomConstructive(NODOS_SEMILLA);
            //GradeConstructive constructiveGrade=new GradeConstructive(NODOS_SEMILLA); //CREA UN CONJUNTO CON LOS NODOS QUE MAYOR GRADO TIENEN
            //ClosenessConstructive constructiveCloseness = new ClosenessConstructive(NODOS_SEMILLA); //CREA UN CONJUNTO CON LOS NODOS QUE MAYOR CENTRALIDAD TIENEN
            //NormalClosenessConstructive normalConstructive=new NormalClosenessConstructive(NODOS_SEMILLA); //CREA UN CONJUNTO CON LOS NODOS QUE MAYOR CENTRALIDAD NORMALIZADA TIENEN
            //HashSet<Integer> conjuntoNodosSemilla=constructiveCloseness.construirSolucion(grafoND);
            //HashSet<Integer> conjuntoNodosSemilla=constructiveGrade.construirSolucion(grafoND);
            //HashSet<Integer> conjuntoNodosSemilla = constructiveRandom.construirSolucion(grafoND);
            //HashSet<Integer> conjuntoNodosSemilla= normalConstructive.construirSolucion(grafoND); //CONJUNTO SEMILLA (en ultima fase: CONJUNTO NODOS CANDIDATOS A ENTRAR)
            //finalConstructivo = System.currentTimeMillis();
            //System.out.println("CONJUNTO SEMILLA "+i+" : "+conjuntoNodosSemilla);
            //tiempoConstructivo=tiempoConstructivo+(double)(finalConstructivo-inicioConstructivo);

            //              SOLUTION

            int promedioInfeccion = 0;
            Solution solution = new Solution(grafoND, conjuntoNodosSemilla, probabilidadArcos);
            for (int j = 1; j <= NUMERO_SIMULACIONES_SOLUTION; j++) {
                System.out.println("------ SOLUCION " + j + " -------------");
                //System.out.println("CONJUNTO SEMILLAS: " + conjuntoNodosSemilla);
                inicioSolution = System.currentTimeMillis();
                HashSet<Integer> conjuntoInfectados = solution.procedimientoCascada();
                finalSolution = System.currentTimeMillis();
                promedioInfeccion = promedioInfeccion + conjuntoInfectados.size();
                //System.out.println("CONJUNTO INFECTADOS: " + conjuntoInfectados);
                //System.out.println("LONGITUD CONJUNTOS INFECTADOS: " + conjuntoInfectados.size());
                //System.out.println("-----------------------------------");
                tiempoSolution=tiempoSolution+(double)(finalSolution-inicioSolution);
            }
            promedioInfeccion=promedioInfeccion/NUMERO_SIMULACIONES_SOLUTION;
            System.out.println("PROMEDIO INFECCION: " + promedioInfeccion);
            System.out.println("-----------------------------------");
            ArrayList<HashSet<Integer>> listaAux=mapaPromedioConjunto.get(promedioInfeccion);
            if(listaAux==null){
                listaAux=new ArrayList<>();
                listaAux.add(conjuntoNodosSemilla);
                mapaPromedioConjunto.put(promedioInfeccion,listaAux);
            }else {
                listaAux.add(conjuntoNodosSemilla);
            }
        }
        int promedioMaximo=Collections.max(mapaPromedioConjunto.keySet());
        System.out.println("PROMEDIO INFECCION MAXIMA: "+promedioMaximo);
        System.out.println("LISTA SEMILLAS INFECCION MAXIMA: "+mapaPromedioConjunto.get(promedioMaximo));
        //              IMPROVEMENTS
        //long inicioImprovement=System.currentTimeMillis();
        //Improvement randomImprovement=new RandomImprovement();
        //randomImprovement.improve(solution);
        //Improvement closenessImprovement=new ClosenessImprovement();
        //closenessImprovement.improve(solution);
        //Improvement betaImprovement=new BetaImprovement();
        //betaImprovement.improve(solution);
        //long finalImprovement=System.currentTimeMillis();

        //              TIEMPOS
        //double tiempoInstancia=(double)finalInstance-inicioInstance;
        //System.out.println("TIEMPO INSTANCIA (seg): "+tiempoInstancia/1000);

        //double tiempoConstructivo=(double)finalConstructivo-inicioConstructivo;
        System.out.println("TIEMPO CONSTRUCTIVO (seg): "+tiempoConstructivo/1000);

        //double tiempoSolucion=randomImprovement.getTiempoSolucion();
        //System.out.println("TIEMPO SOLUCION: "+tiempoSolucion);
        //double tiempoSolucion=closenessImprovement.getTiempoSolucion();
        //System.out.println("TIEMPO SOLUCION: "+tiempoSolucion);
        //double tiempoSolucion=betaImprovement.getTiempoSolucion();
       // System.out.println("TIEMPO SOLUCION: "+tiempoSolucion);

        //double tiempoSolution=(double)finalSolution-inicioSolution;
        System.out.println("TIEMPO SOLUTION (seg): "+tiempoSolution/1000);

        //double tiempoMejoras=(double)finalImprovement-inicioImprovement;
        //System.out.println("TIEMPO MEJORAS: "+tiempoMejoras/1000);


    }
}
