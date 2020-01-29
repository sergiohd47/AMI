import java.util.Collection;
import java.util.HashSet;

/**
 * @author Sergio Hernandez Dominguez
 */
public class ClosenessImprovement implements Improvement {

    @Override
    public Collection improve(Integer nodoCandidatoSalir, Integer nodoCandidatoEntrar, Collection conjuntoNodosSemilla) {
        HashSet<Integer> conjuntoSemillaSolucion=new HashSet<>();
        conjuntoSemillaSolucion.addAll(conjuntoNodosSemilla);
        conjuntoSemillaSolucion.remove(nodoCandidatoSalir);
        conjuntoSemillaSolucion.add(nodoCandidatoEntrar);
        return conjuntoSemillaSolucion;
    }
}
