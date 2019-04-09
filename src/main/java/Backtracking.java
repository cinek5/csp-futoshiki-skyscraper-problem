/**
 * Created by Cinek on 09.04.2019.
 */
public class Backtracking {

    private int numOfSolutions = 0;

    public void solve(FutoshikiProblem problem) {
        if (problem.getRepresentation()[0][0] != 0) {
            Pair next = selectNextVariable(problem, 0, 0);
            solve(problem, next.row, next.col);
        } else {
            solve(problem, 0, 0);
        }

    }

    public void solve(FutoshikiProblem problem, int row, int col) {
        for (int val = 1; val <= problem.getN(); val++) {
            if (problem.isValid(row, col, val)) {
                problem.setValue(row, col, val);

                Pair nextVariable = selectNextVariable(problem, row, col);
                if (nextVariable.row != -1 && nextVariable.col != -1) {
                    solve(problem, nextVariable.row, nextVariable.col);
                    if (allAssigned(problem))
                    {
                        printArray(problem.getRepresentation());
                        System.out.println("\n");
                    }
                    backtrack(problem, row, col, val);
                }

            }

        }
    }
    private boolean allAssigned(FutoshikiProblem problem)
    {
        for (int row=0; row<problem.getN(); row++)
            for(int col=0; col<problem.getN(); col++)
            {
                if (problem.getRepresentation()[row][col]==0)
                    return false;
            }
        return  true;
    }


    private void backtrack(FutoshikiProblem problem, int row, int col, int val) {
        problem.unsetValue(row, col, val);
    }

    private void backtrackIfPossible(FutoshikiProblem problem, int row, int col, int val) {
        Pair nextVariable = selectNextVariable(problem, row, col);
        if (nextVariable.row != -1 && nextVariable.col != -1) {
            System.out.println("nawrot");
            problem.unsetValue(row, col, val);
        }
    }

    private void printArray(int[][] arr) {
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr.length; col++) {
                System.out.print(" " + arr[row][col] + " ");
            }
            System.out.println();
        }
    }

    public Pair selectNextVariable(FutoshikiProblem problem, int row, int col) {
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
            return selectNextVariable(problem, nextrow, nextcol);
        }
        return new Pair(nextrow, nextcol);
    }
}
