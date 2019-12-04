import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

//GRAFO NO DIRIGIDO
public class GrafoND<V,E> implements Grafo<V,E>{
    //private HashSet<Nodo<V>> conjuntoNodos;
    //private HashSet<Arco<E,V>> conjuntoArcos;
    private HashMap<V,Nodo<V>> tablaNodos;
    private HashMap<Pair<V,V>,Arco<E,V>> tablaArcos;

    public GrafoND(){
        this.tablaNodos=new HashMap<>();
        this.tablaArcos=new HashMap<>();
    }
    @Override
    public Collection<Nodo<V>> nodos() {
        //return this.conjuntoNodos;
        HashSet<Nodo<V>> conjSolucion=new HashSet<>();
        for(V nodoInfo:this.tablaNodos.keySet()){
            conjSolucion.add(this.tablaNodos.get(nodoInfo));
        }
        return conjSolucion;
    }

    @Override
    public Collection<Arco<E, V>> arcos() {
        //return this.conjuntoArcos;
        HashSet<Arco<E,V>> conjSolucion=new HashSet<>();
        for(Pair<V,V> parNOND :this.tablaArcos.keySet()){
            conjSolucion.add(this.tablaArcos.get(parNOND));
        }
        return conjSolucion;
    }

    @Override
    public Collection<Arco<E,V>> arcosIncidentes(Nodo<V> nodo) {
        HashSet<Arco<E,V>> conjuntoSolucion=new HashSet<>();
        for(Arco<E,V> arco: this.arcos()){
            if((arco.getNodoDestino()==nodo)||(arco.getNodoOrigen()==nodo)){
                conjuntoSolucion.add(arco);
            }
        }
        return conjuntoSolucion;
    }

    @Override
    public Nodo<V> nodoOpuesto(Nodo<V> nodo, Arco<E,V> arco) {
        if(arco.getNodoOrigen()==nodo){
            return arco.getNodoDestino();
        }else{
            return arco.getNodoOrigen();
        }
    }

    @Override
    public Collection<Nodo<V>> nodosFinales(Arco<E,V> arco) {
        ArrayList<Nodo<V>> listaNodos=new ArrayList<>();
        listaNodos.add(arco.getNodoOrigen());
        listaNodos.add(arco.getNodoDestino());
        return listaNodos;
    }

    @Override
    public Boolean sonAdyacentes(Nodo<V> nodo1, Nodo<V> nodo2) {
        for(Arco<E,V> arco: this.arcos()){
            if((arco.getNodoOrigen()==nodo1)&&(arco.getNodoDestino()==nodo2)||(arco.getNodoOrigen()==nodo2)&&(arco.getNodoDestino()==nodo1)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Nodo<V> insertarNodo(V nodoInfo) {
        /*Nodo<V> nuevoNodo=new Nodo<>(nodoInfo);
        this.conjuntoNodos.add(nuevoNodo);
        return nuevoNodo;
         */
        Nodo<V> nuevoNodo=this.tablaNodos.get(nodoInfo);
        if(nuevoNodo==null){
            nuevoNodo=new Nodo<>(nodoInfo);
            this.tablaNodos.put(nodoInfo,nuevoNodo);
        }
        return nuevoNodo;
    }

    @Override
    public Arco<E, V> insertarArco(Nodo<V> nodoOrigen, Nodo<V> nodoDestino, E pesoArco) {
        /*Arco<E,V> nuevoArco=new Arco<>(pesoArco,nodoOrigen, nodoDestino);
        this.conjuntoArcos.add(nuevoArco);
        return nuevoArco;
         */
        Arco<E,V> nuevoArco=this.tablaArcos.get(new Pair<>(nodoOrigen.getNodoInfo(),nodoDestino.getNodoInfo()));
        if(nuevoArco==null){
            nuevoArco=new Arco<>(pesoArco,nodoOrigen,nodoDestino);
            Pair<V,V> parComprobacionInv=new Pair<>(nodoDestino.getNodoInfo(),nodoOrigen.getNodoInfo());
            Arco<E,V> arcoAux=this.tablaArcos.get(parComprobacionInv);
            if(arcoAux!=null){
                this.tablaArcos.remove(parComprobacionInv);
            }
            this.tablaArcos.put(new Pair<>(nodoOrigen.getNodoInfo(),nodoDestino.getNodoInfo()),nuevoArco);
        }
        return nuevoArco;
    }
    @Override
    public Collection<Nodo<V>> nodosVecinos(Nodo<V> nodo){
        HashSet<Nodo<V>> conjuntoNodos=new HashSet<>();
        for(Arco<E,V> arco: this.arcosIncidentes(nodo)){
            conjuntoNodos.add(this.nodoOpuesto(nodo,arco));
        }
        return conjuntoNodos;
     }
     @Override
     public Nodo<V> dameNodo(V nodoInfo){
        return this.tablaNodos.get(nodoInfo);
     }
     @Override
     public Arco<E,V> dameArco(V nodoO, V nodoD){
        return this.tablaArcos.get(new Pair<>(nodoO,nodoD));
     }
}
