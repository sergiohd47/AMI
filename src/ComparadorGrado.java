import javafx.util.Pair;

import java.util.Comparator;

/**
 * @author Sergio Hernandez Dominguez
 */
public class ComparadorGrado implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Pair<Integer,Integer> par1=(Pair<Integer,Integer>)o1;
        Pair<Integer,Integer> par2=(Pair<Integer,Integer>)o2;
        return par1.getValue()-par2.getValue();
    }
}
