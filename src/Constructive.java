import java.util.Collection;
import java.util.HashSet;

//Interfaz con un unico metodo construir que a partir de una Instance, genera una solucion.
public interface Constructive {
    HashSet<Integer> construirSolucion(Grafo grafo);


}
