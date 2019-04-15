import java.util.Map;
import java.util.Set;

/**
 * Created by Cinek on 10.04.2019.
 */
public interface NextVariableSelectionStrategy {
    Pair selectNextVariable(Problem problem, int row, int col, Map<Cell, Set<Integer>> remainingValues);
}
