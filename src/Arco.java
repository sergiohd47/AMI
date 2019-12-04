import java.util.Objects;

public class Arco<E,V> {
    private E pesoArco;
    private Nodo<V> nodoOrigen;
    private Nodo<V> nodoDestino;


    public Arco(E pesoArco, Nodo<V> nodoOrigen, Nodo<V> nodoDestino) {
        this.pesoArco = pesoArco;
        this.nodoOrigen = nodoOrigen;
        this.nodoDestino = nodoDestino;
    }


    public E getPesoArco() {
        return pesoArco;
    }

    public void setPesoArco(E pesoArco) {
        this.pesoArco = pesoArco;
    }

    public Nodo<V> getNodoOrigen() {
        return nodoOrigen;
    }

    public void setNodoOrigen(Nodo<V> nodoOrigen) {
        this.nodoOrigen = nodoOrigen;
    }

    public Nodo<V> getNodoDestino() {
        return nodoDestino;
    }

    public void setNodoDestino(Nodo<V> nodoDestino) {
        this.nodoDestino = nodoDestino;
    }
    @Override
    public boolean equals(Object o){
        if(o==null){
            return false;
        }
        if(this.getClass()!=o.getClass()){
            return false;
        }
        final Arco<E,V> arco=(Arco<E,V>)o;
        return ((this.getNodoOrigen()==arco.getNodoOrigen())&&(this.getNodoDestino()==arco.getNodoDestino())&&
                this.getPesoArco()==arco.getPesoArco());
    }
    @Override
    public String toString(){
        return "Arco : "+ nodoOrigen.toString()+" || "+nodoDestino.toString()+ " pesoArco: "+this.pesoArco;
    }

}
