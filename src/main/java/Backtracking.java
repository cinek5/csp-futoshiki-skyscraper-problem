import java.util.*;

/**
 * Created by Cinek on 09.04.2019.
 */
public class Backtracking extends Solver {

    private int numOfIterations = 0;
    private NextVariableSelectionStrategy nextVariableSelectionStrategy = new  IterativeNextVariableSelectionStrategy();
    //private NextVariableSelectionStrategy nextVariableSelectionStrategy = new MRVHeuristicsSelectionStrategy();



    public void solve(Problem problem) {
        Map<Cell, Set<Integer>> domains  = initialDomains(problem.getN());
        if (nextVariableSelectionStrategy instanceof  IterativeNextVariableSelectionStrategy) {
            if (problem.getRepresentation()[0][0] != 0) {
                Pair next = nextVariableSelectionStrategy.selectNextVariable(problem, 0, 0, domains);
                solve(problem, next.row, next.col, domains);
            } else {
                solve(problem, 0, 0, domains);
            }
        }
        else
        {
            Pair next = nextVariableSelectionStrategy.selectNextVariable(problem,0,0,domains);
            solve(problem, next.row, next.col, domains);
        }

    }

    public void solve(Problem problem, int row, int col,Map<Cell, Set<Integer>> remainingVariables ) {
        for (int val = 1; val <= problem.getN(); val++) {
            if (problem.isValid(row, col, val)) {
                problem.setValue(row, col, val);



                Map<Cell, Set<Integer>> whatWasRemoved = removeNotValidValues(remainingVariables, row, col, problem);


                Pair nextVariable = nextVariableSelectionStrategy.selectNextVariable(problem, row, col, remainingVariables);
                if (nextVariable.row != -1 && nextVariable.col != -1) {
                    undoRemovingRemainingValues(remainingVariables, whatWasRemoved);
                    solve(problem, nextVariable.row, nextVariable.col, remainingVariables);
                }
                if (problem.allAssigned())
                {
                    printArray(problem.getRepresentation());
                    System.out.println("\n");
                }
                numOfIterations++;
                backtrack(problem, row, col, val);

            }
            else
            {
                numOfIterations++;
            }

        }
    }

    public int getNumOfIterations()
    {
        return numOfIterations;
    }









}
