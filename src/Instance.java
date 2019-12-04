import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;

//Esta clase almacena en memoria la informacion de cada una de las instancias (ficheros) SNAP
public class Instance {
    private float probabilidadArcos;
    private int numeroNodos;
    public Instance(){
        this.probabilidadArcos=(float)Math.random()*1; //numero aleatorio entre 0 y 1 (simula la probabilidad) (7 decimales)
        this.numeroNodos=0;
    }

    public ArrayList<Pair<Integer,Integer>> leerFichero(String rutaFichero){
        HashSet<Integer> nodos=new HashSet<>();
        ArrayList<Pair<Integer,Integer>> listaNodos= new ArrayList<>();
        File archivo=null;
        FileReader fr=null;
        BufferedReader br=null;
        try{
            archivo=new File(rutaFichero);
            fr=new FileReader(archivo);
            br=new BufferedReader(fr);
            String linea;
            while((linea=br.readLine())!=null){
                if(linea.indexOf("#")==0){ //Ignoran las lineas que empiezan por "#"
                    continue;
                }
                int nodoOrigen=Integer.parseInt(linea.split("\t")[0]);
                nodos.add(nodoOrigen);
                int nodoDestino=Integer.parseInt(linea.split("\t")[1]);
                nodos.add(nodoDestino);
                listaNodos.add(new Pair<>(nodoOrigen,nodoDestino)); //lista de pares de nodoOrigen y nodoDestino de cada arco.
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(fr!=null){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        this.numeroNodos=nodos.size();
        return listaNodos;
    }
    public  Grafo construirGrafo(ArrayList<Pair<Integer, Integer>> listaNodos) throws RuntimeException{
        if(listaNodos.isEmpty()){
            throw new RuntimeException("Lista nodos vacia");
        }
        Grafo grafoND=new GrafoND(this.numeroNodos);
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
