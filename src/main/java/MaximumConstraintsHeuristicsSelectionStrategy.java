import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Cinek on 16.04.2019.
 */
public class MaximumConstraintsHeuristicsSelectionStrategy implements NextVariableSelectionStrategy {
    @Override
    public Pair selectNextVariable(Problem problem, int row, int col, Map<Cell, Set<Integer>> remainingValues) {
        return maxiumumConstraintsHeuristicsNextVariable(problem);
    }


    private Pair maxiumumConstraintsHeuristicsNextVariable(
            Problem problem) {

        int numOfConstraints = -1;
        int col = -1;
        int row = -1;

        for (int r = 0; r < problem.getN(); r++) {
            for (int c = 0; c < problem.getN(); c++) {
                if (problem.getRepresentation()[r][c] != 0) {
                    continue;
                }
                int constraints = problem.getNumberOfConstraints(r, c);
                if (constraints > numOfConstraints) {
                    numOfConstraints = constraints;
                    col = c;
                    row = r;
                }

            }
        }
        return new Pair(row, col);
    }
}
