import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Cinek on 15.04.2019.
 */
public class MRVHeuristicsSelectionStrategy implements NextVariableSelectionStrategy {
    @Override
    public Pair selectNextVariable(Problem problem, int row, int col, Map<Cell, Set<Integer>> remainingValues) {
        return lrvHeuristicsNextVariable(problem, remainingValues);
    }

    private Pair lrvHeuristicsNextVariable(
            Problem problem, Map<Cell, Set<Integer>> remainingValues)
    {
        Cell cell = null;
        Iterator<Cell> it =remainingValues.keySet().iterator();
        int numOfValues=0;
        while(it.hasNext())
        {
            Cell next = it.next();
            if (problem.getRepresentation()[next.row
                    ][next.col] == 0)
            {
                numOfValues = remainingValues.get(next).size();

                cell=next;

                break;
            }


        }

        while(it.hasNext())
        {
            Cell next= it.next();
            int size = remainingValues.get(next).size();
            if (problem.getRepresentation()[next.row
                    ][next.col] == 0 && size<numOfValues )
            {
                numOfValues = size;
                cell = next;
            }

        }


        if (cell == null)
        {
            return new Pair(-1,-1);
        }
        return new Pair(cell.row, cell.col);
    }

}
