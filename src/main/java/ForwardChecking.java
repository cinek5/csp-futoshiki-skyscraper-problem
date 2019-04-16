import java.util.*;

/**
 * Created by Cinek on 15.04.2019.
 */
public class ForwardChecking extends Solver {

    private int numOfIterations = 0;
    //private NextVariableSelectionStrategy nextVariableSelectionStrategy = new  IterativeNextVariableSelectionStrategy();
    private NextVariableSelectionStrategy nextVariableSelectionStrategy = new MRVHeuristicsSelectionStrategy();




    public void solve(Problem problem) {
        Map<Cell, Set<Integer>> domains = initialDomains(problem.getN());
        if (problem.getRepresentation()[0][0] != 0) {
            Pair next = nextVariableSelectionStrategy.selectNextVariable(problem, 0, 0, domains);
            solve(problem, next.row, next.col, domains);
        } else {
            solve(problem, 0, 0, domains);
        }

    }

    public void solve(Problem problem, int row, int col, Map<Cell,Set<Integer>> remainingValues) {

        for (Integer val: new HashSet<>(remainingValues.get(new Cell(row, col)))) {


            if (problem.isValid(row, col, val)) {
                problem.setValue(row, col, val);


                Map<Cell, Set<Integer>> whatWasRemoved = removeNotValidValues(remainingValues, row, col, problem);

                if (areSetsNotEmpty(problem, remainingValues)) {


                    Pair nextVariable = nextVariableSelectionStrategy.selectNextVariable(problem, row, col, remainingValues);
                    if (nextVariable.row != -1 && nextVariable.col != -1) {
                        solve(problem, nextVariable.row, nextVariable.col, remainingValues);
                    }

                }
                if (problem.allAssigned()) {
                    printArray(problem.getRepresentation());
                    System.out.println("\n");
                }
                numOfIterations++;
                undoRemovingRemainingValues(remainingValues,whatWasRemoved);
                backtrack(problem, row, col, val);

            } else {
                numOfIterations++;
            }
        }

    }

//    private int selectValue(Set<Integer> values, int row, int col)
//    {
//
//    }

    public int getNumOfIterations()
    {
        return numOfIterations;
    }





    private boolean areSetsNotEmpty(Problem problem, Map<Cell, Set<Integer>> remainingValues)
    {
        for (Map.Entry<Cell, Set<Integer>> entry : remainingValues.entrySet())
        {
            Set<Integer> set = entry.getValue();
            Cell cell  = entry.getKey();

            if (set.isEmpty() && !problem.hasValue(cell.row, cell.col))
            {
                return false;
            }
        }
        return true;
    }







}
