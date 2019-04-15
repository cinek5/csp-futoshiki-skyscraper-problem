/**
 * Created by Cinek on 09.04.2019.
 */
public class Backtracking implements Solver {

    private int numOfBacktracks = 0;
    private NextVariableSelectionStrategy nextVariableSelectionStrategy = new  IterativeNextVariableSelectionStrategy();

    public void solve(Problem problem) {
        if (problem.getRepresentation()[0][0] != 0) {
            Pair next = nextVariableSelectionStrategy.selectNextVariable(problem, 0, 0);
            solve(problem, next.row, next.col);
        } else {
            solve(problem, 0, 0);
        }

    }

    public void solve(Problem problem, int row, int col) {
        for (int val = 1; val <= problem.getN(); val++) {
            if (problem.isValid(row, col, val)) {
                problem.setValue(row, col, val);

                Pair nextVariable = nextVariableSelectionStrategy.selectNextVariable(problem, row, col);
                if (nextVariable.row != -1 && nextVariable.col != -1) {
                    solve(problem, nextVariable.row, nextVariable.col);
                    if (problem.allAssigned())
                    {
                        printArray(problem.getRepresentation());
                        System.out.println("\n");
                    }
                    numOfBacktracks++;
                    backtrack(problem, row, col, val);
                }

            }

        }
    }

    public int getNumOfBacktracks()
    {
        return numOfBacktracks;
    }


    private void backtrack(Problem problem, int row, int col, int val) {
        problem.unsetValue(row, col, val);
    }

//    private void backtrackIfPossible(FutoshikiProblem problem, int row, int col, int val) {
//        Pair nextVariable = nextVariableSelectionStrategy.selectNextVariable(problem, row, col);
//        if (nextVariable.row != -1 && nextVariable.col != -1) {
//            System.out.println("nawrot");
//            problem.unsetValue(row, col, val);
//        }
//    }

    private void printArray(int[][] arr) {
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr.length; col++) {
                System.out.print(" " + arr[row][col] + " ");
            }
            System.out.println();
        }
    }


}
