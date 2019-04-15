import java.util.Map;
import java.util.Set;

/**
 * Created by Cinek on 15.04.2019.
 */
public class IterativeNextVariableSelectionStrategy implements NextVariableSelectionStrategy {

    public Pair selectNextVariable(Problem problem, int row, int col, Map<Cell, Set<Integer>> remainingValues) {
        int nextcol = -1;
        int nextrow = -1;
        if (col + 1 >= problem.getN()) {
            if (row + 1 >= problem.getN()) {
                nextrow = -1;
                nextcol = -1;
            } else {
                nextrow = row + 1;
                nextcol = 0;
            }
        } else {
            nextcol = col + 1;
            nextrow = row;
        }

        if (nextrow != -1 && nextcol != -1 && problem.getRepresentation()[nextrow][nextcol] != 0) {
            return selectNextVariable(problem, nextrow, nextcol, remainingValues);
        }
        return new Pair(nextrow, nextcol);
    }
}
