import java.util.*;

/**
 * Created by Cinek on 09.04.2019.
 */
public class CleanBacktracking extends Solver {

    private int numOfIterations = 0;
    private NextVariableSelectionStrategy nextVariableSelectionStrategy = new  IterativeNextVariableSelectionStrategy();




    public void solve(Problem problem) {
        if (problem.getRepresentation()[0][0] != 0) {
            Pair next = nextVariableSelectionStrategy.selectNextVariable(problem, 0, 0, null);
            solve(problem, next.row, next.col);
        } else {
            solve(problem, 0, 0);
        }

    }

    public void solve(Problem problem, int row, int col) {
        for (int val = 1; val <= problem.getN(); val++) {
            if (problem.isValid(row, col, val)) {
                problem.setValue(row, col, val);

                Pair nextVariable = nextVariableSelectionStrategy.selectNextVariable(problem, row, col, null);
                if (nextVariable.row != -1 && nextVariable.col != -1) {
                    solve(problem, nextVariable.row, nextVariable.col);
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
