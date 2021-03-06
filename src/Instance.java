import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sergio Hernandez Dominguez
 */
//Esta clase almacena en memoria la informacion de cada una de las instancias (ficheros) SNAP
public class Instance {

    private int numeroNodos;
    private int nodoMaximo;
    public Instance(){
        this.numeroNodos=0;

    }

    public ArrayList<Pair<Integer,Integer>> leerFichero(String rutaFichero){
        //SE USA EL SISTEMA DE PATRONES Y DE REGEX PARA DISTINGUIR ENTRE DOS TIPOS DE FICHEROS, LOS .txt y LOS .csv
        String patronTXT=".*.txt.*";
        String patronCSV=".*.csv.*";
        Pattern patTXT=Pattern.compile(patronTXT);
        Pattern patCSV=Pattern.compile(patronCSV);
        Matcher matTXT=patTXT.matcher(rutaFichero);
        Matcher matCSV=patCSV.matcher(rutaFichero);
        HashSet<Integer> nodos=new HashSet<>();
        ArrayList<Pair<Integer,Integer>> listaNodos= new ArrayList<>();
        File archivo=null;
        FileReader fr=null;
        BufferedReader br=null;
        if(matTXT.matches()) {
            try {
                archivo = new File(rutaFichero);
                fr = new FileReader(archivo);
                br = new BufferedReader(fr);
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.indexOf("#") == 0) { //Ignoran las lineas que empiezan por "#"
                        continue;
                    }
                    //EN LA REGEX DEL FICHERO DE PRUEBA CREADO POR MI, ES NECESARIO PONER "\t".
                    //EN LA REGEX DEL FICHERO DE SNAP "snap2/email-Eu-core.txt", LA REGEX ES UN ESPACIO " ".
                    //REGEX: "\\s+" numero de espacios que sean
                    int nodoOrigen = Integer.parseInt(linea.split("\\s+")[0]);
                    nodos.add(nodoOrigen);
                    int nodoDestino = Integer.parseInt(linea.split("\\s+")[1]);
                    nodos.add(nodoDestino);
                    listaNodos.add(new Pair<>(nodoOrigen, nodoDestino)); //lista de pares de nodoOrigen y nodoDestino de cada arco.
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fr != null) {
                        fr.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }else if(matCSV.matches()){
            try {
                archivo = new File(rutaFichero);
                fr = new FileReader(archivo);
                br = new BufferedReader(fr);
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.indexOf("#") == 0) { //Ignoran las lineas que empiezan por "#"
                        continue;
                    }
                    //EN LA REGEX DEL FICHERO DE PRUEBA CREADO POR MI, ES NECESARIO PONER "\t".
                    //EN LA REGEX DEL FICHERO DE SNAP "snap2/email-Eu-core.txt", LA REGEX ES UN ESPACIO " ".
                    //REGEX: "\\s+" numero de espacios que sean
                    int nodoOrigen = Integer.parseInt(linea.split(",")[0]);
                    nodos.add(nodoOrigen);
                    int nodoDestino = Integer.parseInt(linea.split(",")[1]);
                    nodos.add(nodoDestino);
                    listaNodos.add(new Pair<>(nodoOrigen, nodoDestino)); //lista de pares de nodoOrigen y nodoDestino de cada arco.
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fr != null) {
                        fr.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        this.numeroNodos=nodos.size();
        this.nodoMaximo= Collections.max(nodos);
        return listaNodos;
    }
    public  Grafo construirGrafo(ArrayList<Pair<Integer, Integer>> listaNodos) throws RuntimeException{
        if(listaNodos.isEmpty()){
            throw new RuntimeException("Lista nodos vacia");
        }
        Grafo grafoND=new GrafoND(this.numeroNodos,this.nodoMaximo);
        for(Pair<Integer, Integer> parValores: listaNodos){
            if(!grafoND.nodos().contains(parValores.getKey())){
                grafoND.insertarNodo(parValores.getKey());
            }
            if(!grafoND.nodos().contains(parValores.getValue())){
                grafoND.insertarNodo(parValores.getValue());
            }
            grafoND.insertarArco(parValores.getKey(),parValores.getValue());
        }
        return grafoND;
    }

}
