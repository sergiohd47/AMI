import java.util.Collection;
import java.util.HashSet;

/**
 * @author Sergio Hernandez Dominguez
 */
//Interfaz que recibe una solucion y la mejora.
public interface Improvement {
    void improve(Solution solucion);
    //double getTiempoSolucion();
    int getMayorPromedio();
}
