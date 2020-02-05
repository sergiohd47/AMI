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
    private static final int NUMERO_SIMULACIONES=30; //NUMERO SIMULACIONES QUE HACE EL ALGORITMO (debido al teoria central del limite, se elige 30)

    public static void main(String args[]){
        HashMap<Integer,HashSet<Integer>> mapaConjuntos=new HashMap<>();
        HashMap<Integer, ArrayList<HashSet<Integer>>> mapaPromedioConjSemilla=new HashMap<>();
        int mayorPromedio=Integer.MIN_VALUE;
        ArrayList<HashSet<Integer>> lista=new ArrayList<>();
        mapaPromedioConjSemilla.put(mayorPromedio,lista);
        Instance instance=new Instance();
        ArrayList<Pair<Integer, Integer>> listaNodos=instance.leerFichero(RUTA_FICHERO_PRUEBAS);
        Grafo grafoND=instance.construirGrafo(listaNodos);
        float probabilidadArcos=instance.getProbabilidadArcos();
        //long inicioConstructivo= System.currentTimeMillis(); SE COMENTA  LO REFERENTE A LOS TIEMPOS
        //Constructive constructive=new RandomConstructive(NODOS_SEMILLA);
        //Constructive constructive=new GradeConstructive(NODOS_SEMILLA); //CREA UN CONJUNTO CON LOS NODOS QUE MAYOR GRADO TIENEN
        Constructive constructive=new ClosenessConstructive(NODOS_SEMILLA); //CREA UN CONJUNTO CON LOS NODOS QUE MAYOR CENTRALIDAD TIENEN
        ArrayList<Pair<Integer,Float>> listaClosenessSemilla= ((ClosenessConstructive) constructive).getListaClosenessSemilla();
        ArrayList<Pair<Integer,Float>> listaClosenessCompleta=((ClosenessConstructive)constructive).getListaClosenessCompleta();
        //Constructive normalConstructive=new NormalClosenessConstructive(NODOS_SEMILLA); //CREA UN CONJUNTO CON LOS NODOS QUE MAYOR CENTRALIDAD NORMALIZADA TIENEN
        //ArrayList<Pair<Integer,Float>> listaClosenessSemilla= ((NormalClosenessConstructive) normalConstructive).getListaClosenessSemilla();
        System.out.println("NUMERO NODOS GRAFO: "+grafoND.tamañoGrafo());
        HashSet<Integer> conjuntoNodosSemilla= constructive.construirSolucion(grafoND);
        //HashSet<Integer> conjuntoNodosSemilla= normalConstructive.construirSolucion(grafoND); //CONJUNTO SEMILLA (en ultima fase: CONJUNTO NODOS CANDIDATOS A ENTRAR)

        /*          CAMBIOS RANDOM DE NODOS
        HashSet<Integer> conjuntoNodosEntradaSemilla= new HashSet<>();
        for(Integer nodo: grafoND.nodos()){
            conjuntoNodosEntradaSemilla.add(nodo);
        }
        conjuntoNodosEntradaSemilla.removeAll(conjuntoNodosSemilla); //CONJUNTO NODOS CANDIDATOS A ENTRAR
        int promedioLongitudInfectados = 0;
        while(true) {
            Improvement randomImprovement=new RandomImprovement();
            for(Integer nodoSalida: conjuntoNodosSemilla) {
                for(Integer nodoEntrada: conjuntoNodosEntradaSemilla) {
                    System.out.println("CONJUNTO SEMILLA INICIAL: "+conjuntoNodosSemilla);
                    System.out.println("----------------------");
                    HashSet<Integer> conjuntoNuevasSemillas= (HashSet<Integer>) randomImprovement.improve(nodoSalida,nodoEntrada,conjuntoNodosSemilla);
                    System.out.println("NODO SALE: "+nodoSalida);
                    System.out.println("NODO ENTRA: "+nodoEntrada);
                    System.out.println("----------------------");
                    System.out.println("CONJUNTO NUEVA SEMILLA: " + conjuntoNuevasSemillas);
                    System.out.println("----------------------");
                    for (int i = 1; i < NUMERO_SIMULACIONES + 1; i++) {
                        Solution solucion = new Solution();
                        HashSet<Integer> conjuntoInfectados = solucion.procedimientoCascada(grafoND, conjuntoNuevasSemillas, probabilidadArcos);
                        mapaConjuntos.put(i, conjuntoInfectados);
                        System.out.println("SOLUCION " + i);
                        System.out.println("Conjunto semillas: "+conjuntoNuevasSemillas);
                        System.out.println("Tamaño conj: " + conjuntoInfectados.size());
                        System.out.println("Conjunto infectados: " + conjuntoInfectados);
                        System.out.println("------------------");
                    }
                    //long finalSolucion = System.currentTimeMillis(); SE COMENTA  LO REFERENTE A LOS TIEMPOS
                    for (Integer clave : mapaConjuntos.keySet()) {
                        promedioLongitudInfectados = promedioLongitudInfectados + mapaConjuntos.get(clave).size();
                    }
                    int promedio=promedioLongitudInfectados/NUMERO_SIMULACIONES;
                    ArrayList<HashSet<Integer>> listaSacada=mapaPromedioConjSemilla.get(promedio);
                    if(listaSacada==null){
                        listaSacada=new ArrayList<>();
                        listaSacada.add(conjuntoNuevasSemillas);
                        mapaPromedioConjSemilla.put(promedio,listaSacada);
                    }else{
                        listaSacada.add(conjuntoNuevasSemillas);
                    }

                    mayorPromedio=Math.max(mayorPromedio,promedio);
                    System.out.println("PROMEDIO INFECTADOS: " + promedio);
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println();
                    System.out.println("--------------------------------------------------------------------");
                    promedioLongitudInfectados=0;

                }
            }
            System.out.println("TABLA PROMEDIO-CONJUNTOS SEMILLAS");
            for (Map.Entry<Integer,ArrayList<HashSet<Integer>>> entrada: mapaPromedioConjSemilla.entrySet()){
                System.out.println(entrada);
            }
            System.out.println();
            System.out.println("NUMERO DE CONJUNTOS DE INFECCION MAXIMA: "+mapaPromedioConjSemilla.get(mayorPromedio).size());
            System.out.println("CONJUNTOS INFECCION MAXIMA: " + mapaPromedioConjSemilla.get(mayorPromedio)+ " -- PROMEDIO DE INFECCION: "+mayorPromedio);
            break;
        }*/
        //             CAMBIOS EN FUNCION DEL CLOSENESS
        /*ArrayList<Pair<Integer,Float>> listaNodosEntradaSemilla= new ArrayList<>();
        listaNodosEntradaSemilla.addAll(listaClosenessCompleta);
        listaNodosEntradaSemilla.removeAll(listaClosenessSemilla); //CONJUNTO NODOS CANDIDATOS A ENTRAR

        Collections.reverse(listaClosenessSemilla); //SE ORDENA DE MENOR A MAYOR EL CONJUNTO DE SEMILLAS
        HashSet<Integer> conjuntoSemillas=new HashSet<>();
        for(Pair<Integer,Float> parSemilla: listaClosenessSemilla){
            conjuntoSemillas.add(parSemilla.getKey());
        }
        int promedioLongitudInfectados = 0;
        while(true) {
            Improvement closenessImprovement = new ClosenessImprovement();
            for (Pair<Integer,Float> parSalida : listaClosenessSemilla) {
                for (Pair<Integer,Float> parEntrada : listaNodosEntradaSemilla) {
                    System.out.println("CONJUNTO SEMILLA INICIAL: ");
                    for(Pair<Integer,Float> parSemilla: listaClosenessSemilla) {
                        System.out.println(parSemilla);
                    }
                    System.out.println("----------------------");
                    Integer nodoSalida=parSalida.getKey();
                    Integer nodoEntrada=parEntrada.getKey();
                    HashSet<Integer> conjuntoNuevasSemillas = (HashSet<Integer>) closenessImprovement.improve(nodoSalida, nodoEntrada, conjuntoSemillas);
                    System.out.println("NODO SALE: " + parSalida);
                    System.out.println("NODO ENTRA: " + parEntrada);
                    System.out.println("----------------------");
                    System.out.println("CONJUNTO NUEVA SEMILLA: " + conjuntoNuevasSemillas);
                    System.out.println("----------------------");
                    for (int i = 1; i < NUMERO_SIMULACIONES + 1; i++) {
                        Solution solucion = new Solution();
                        HashSet<Integer> conjuntoInfectados = solucion.procedimientoCascada(grafoND, conjuntoNuevasSemillas, probabilidadArcos);
                        mapaConjuntos.put(i, conjuntoInfectados);
                        System.out.println("SOLUCION " + i);
                        System.out.println("Conjunto semillas: " + conjuntoNuevasSemillas);
                        System.out.println("Tamaño conj: " + conjuntoInfectados.size());
                        System.out.println("Conjunto infectados: " + conjuntoInfectados);
                        System.out.println("------------------");
                    }
                    //long finalSolucion = System.currentTimeMillis(); SE COMENTA  LO REFERENTE A LOS TIEMPOS
                    for (Integer clave : mapaConjuntos.keySet()) {
                        promedioLongitudInfectados = promedioLongitudInfectados + mapaConjuntos.get(clave).size();
                    }
                    int promedio = promedioLongitudInfectados / NUMERO_SIMULACIONES;
                    ArrayList<HashSet<Integer>> listaSacada = mapaPromedioConjSemilla.get(promedio);
                    if (listaSacada == null) {
                        listaSacada = new ArrayList<>();
                        listaSacada.add(conjuntoNuevasSemillas);
                        mapaPromedioConjSemilla.put(promedio, listaSacada);
                    } else {
                        listaSacada.add(conjuntoNuevasSemillas);
                    }

                    mayorPromedio = Math.max(mayorPromedio, promedio);
                    System.out.println("PROMEDIO INFECTADOS: " + promedio);
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println();
                    System.out.println("--------------------------------------------------------------------");
                    promedioLongitudInfectados = 0;

                }
            }
            System.out.println("TABLA PROMEDIO-CONJUNTOS SEMILLAS");
            for (Map.Entry<Integer, ArrayList<HashSet<Integer>>> entrada : mapaPromedioConjSemilla.entrySet()) {
                System.out.println(entrada);
            }
            System.out.println();
            System.out.println("NUMERO DE CONJUNTOS DE INFECCION MAXIMA: " + mapaPromedioConjSemilla.get(mayorPromedio).size());
            System.out.println("CONJUNTOS INFECCION MAXIMA: " + mapaPromedioConjSemilla.get(mayorPromedio) + " -- PROMEDIO DE INFECCION: " + mayorPromedio);
            break;
        }
        */
        //              CAMBIOS NORMALIZADOS EN FUNCION DEL CLOSSENES
        ArrayList<Pair<Integer, Float>> listaNodosEntradaSemilla = new ArrayList<>(listaClosenessCompleta);
        listaNodosEntradaSemilla.removeAll(listaClosenessSemilla); //CONJUNTO NODOS CANDIDATOS A ENTRAR

        Collections.reverse(listaClosenessSemilla); //SE ORDENA DE MENOR A MAYOR EL CONJUNTO DE SEMILLAS
        HashSet<Integer> conjuntoSemillas=new HashSet<>();
        for(Pair<Integer,Float> parSemilla: listaClosenessSemilla){
            conjuntoSemillas.add(parSemilla.getKey());
        }
        int promedioLongitudInfectados = 0;
        while(true) {
            Float numeroBeta=(float)Math.random();
            System.out.println("NUMERO BETA: "+numeroBeta);
            HashSet<Pair<Integer,Float>> semillasSalidaAcotadas=new HashSet<>();
            HashSet<Pair<Integer,Float>> nodosEntradaAcotados=new HashSet<>();
            for(Pair<Integer,Float> parSemilla: listaClosenessSemilla){
                if(parSemilla.getValue()<numeroBeta){
                    semillasSalidaAcotadas.add(parSemilla);
                }
            }
            for(Pair<Integer,Float> parEntrada: listaNodosEntradaSemilla){
                if(parEntrada.getValue()>numeroBeta){
                    nodosEntradaAcotados.add(parEntrada);
                }
            }
            Improvement normalClosenessImprovement = new NormalClosenessImprovement();
            for (Pair<Integer,Float> parSalida : semillasSalidaAcotadas) {
                for (Pair<Integer,Float> parEntrada : nodosEntradaAcotados) {
                    System.out.println("CONJUNTO SEMILLA INICIAL: ");
                    for(Pair<Integer,Float> parSemilla: listaClosenessSemilla) {
                        System.out.println(parSemilla);
                    }
                    System.out.println("----------------------");
                    Integer nodoSalida=parSalida.getKey();
                    Integer nodoEntrada=parEntrada.getKey();
                    HashSet<Integer> conjuntoNuevasSemillas = (HashSet<Integer>) normalClosenessImprovement.improve(nodoSalida, nodoEntrada, conjuntoSemillas);
                    System.out.println("NODO SALE: " + parSalida);
                    System.out.println("NODO ENTRA: " + parEntrada);
                    System.out.println("----------------------");
                    System.out.println("CONJUNTO NUEVA SEMILLA: " + conjuntoNuevasSemillas);
                    System.out.println("----------------------");
                    for (int i = 1; i < NUMERO_SIMULACIONES + 1; i++) {
                        Solution solucion = new Solution();
                        HashSet<Integer> conjuntoInfectados = solucion.procedimientoCascada(grafoND, conjuntoNuevasSemillas, probabilidadArcos);
                        mapaConjuntos.put(i, conjuntoInfectados);
                        System.out.println("SOLUCION " + i);
                        System.out.println("Conjunto semillas: " + conjuntoNuevasSemillas);
                        System.out.println("Tamaño conj: " + conjuntoInfectados.size());
                        System.out.println("Conjunto infectados: " + conjuntoInfectados);
                        System.out.println("------------------");
                    }
                    //long finalSolucion = System.currentTimeMillis(); SE COMENTA  LO REFERENTE A LOS TIEMPOS
                    for (Integer clave : mapaConjuntos.keySet()) {
                        promedioLongitudInfectados = promedioLongitudInfectados + mapaConjuntos.get(clave).size();
                    }
                    int promedio = promedioLongitudInfectados / NUMERO_SIMULACIONES;
                    ArrayList<HashSet<Integer>> listaSacada = mapaPromedioConjSemilla.get(promedio);
                    if (listaSacada == null) {
                        listaSacada = new ArrayList<>();
                        listaSacada.add(conjuntoNuevasSemillas);
                        mapaPromedioConjSemilla.put(promedio, listaSacada);
                    } else {
                        listaSacada.add(conjuntoNuevasSemillas);
                    }

                    mayorPromedio = Math.max(mayorPromedio, promedio);
                    System.out.println("PROMEDIO INFECTADOS: " + promedio);
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println();
                    System.out.println("--------------------------------------------------------------------");
                    promedioLongitudInfectados = 0;
                    semillasSalidaAcotadas.remove(parSalida);
                    nodosEntradaAcotados.remove(parEntrada);
                }
            }
            System.out.println("TABLA PROMEDIO-CONJUNTOS SEMILLAS");
            for (Map.Entry<Integer, ArrayList<HashSet<Integer>>> entrada : mapaPromedioConjSemilla.entrySet()) {
                System.out.println(entrada);
            }
            System.out.println();
            System.out.println("NUMERO DE CONJUNTOS DE INFECCION MAXIMA: " + mapaPromedioConjSemilla.get(mayorPromedio).size());
            System.out.println("CONJUNTOS INFECCION MAXIMA: " + mapaPromedioConjSemilla.get(mayorPromedio) + " -- PROMEDIO DE INFECCION: " + mayorPromedio);
            break;
        }
        /*double tiempoConstructivo=(double)finalConstructivo-inicioConstructivo;
        System.out.println("TIEMPO CONSTRUCTIVO: "+tiempoConstructivo);
        double tiempoSolucion=(double)finalSolucion-inicioSolucion;
        System.out.println("TIEMPO SOLUCION: "+tiempoSolucion);
        */

    }
}
