import java.util.Collection;
import java.util.HashSet;
/**
 * @author Sergio Hernandez Dominguez
 */
//Interfaz con un unico metodo construir que a partir de una Instance, genera una solucion.
public interface Constructive {
    HashSet<Integer> construirSolucion(Grafo grafo);


}
