import java.util.Collection;
import java.util.HashSet;

/**
 * @author Sergio Hernandez Dominguez
 */
public class RandomImprovement implements Improvement {

    @Override
    public HashSet<Integer> improve(Integer nodoCandidatoSalir, Integer nodoCandidatoEntrar, Collection conjuntoNodosSemilla) {
        HashSet<Integer> conjuntoSemillaSolucion=new HashSet<>();
        conjuntoSemillaSolucion.addAll(conjuntoNodosSemilla);
        conjuntoSemillaSolucion.remove(nodoCandidatoSalir);
        conjuntoSemillaSolucion.add(nodoCandidatoEntrar);
        return conjuntoSemillaSolucion;
    }
}
