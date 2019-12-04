import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

//Esta clase almacena en memoria la informacion de cada una de las instancias (ficheros) SNAP
public class Instance {
    protected float probabilidadArco;
    public Instance(){
        this.probabilidadArco=(float)Math.random()*1; //numero aleatorio entre 0 y 1 (simula la probabilidad) (7 decimales)
    }

    public ArrayList<Pair<Integer,Integer>> leerFichero(String rutaFichero){
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
                int nodoDestino=Integer.parseInt(linea.split("\t")[1]);
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
        return listaNodos;
    }
    public  Grafo<Integer, Float> construirGrafo(ArrayList<Pair<Integer, Integer>> listaNodos) throws RuntimeException{
        if(listaNodos.isEmpty()){
            throw new RuntimeException("Lista nodos vacia");
        }
        Grafo<Integer, Float> grafoND=new GrafoND<>();
        for(Pair<Integer, Integer> parValores: listaNodos){
            Nodo<Integer> nodoO=grafoND.insertarNodo(parValores.getKey());
            Nodo<Integer> nodoD=grafoND.insertarNodo(parValores.getValue());
            grafoND.insertarArco(nodoO,nodoD,this.probabilidadArco);
        }
        return grafoND;
    }
}
