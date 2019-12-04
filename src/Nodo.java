public class Nodo<V> {
    private V nodoInfo;

    public Nodo(V nodoInfo) {
        this.nodoInfo = nodoInfo;

    }
    public V getNodoInfo() {
        return nodoInfo;
    }

    public void setNodoInfo(V nodoInfo) {
        this.nodoInfo = nodoInfo;
    }

    @Override
    public String toString(){
        return "Nodo: "+this.getNodoInfo()+".";
    }

    @Override
    public boolean equals(Object o){
        if(o==null){
            return false;
        }
        if(this.getClass()!=o.getClass()){
            return false;
        }
        final Nodo<V> nodo=(Nodo<V>)o;
        return this.getNodoInfo()==nodo.getNodoInfo();
    }
}
