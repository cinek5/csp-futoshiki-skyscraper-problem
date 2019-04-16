import java.util.*;

/**
 * Created by Cinek on 09.04.2019.
 */
public class Backtracking implements Solver {

    private int numOfIterations = 0;
    private NextVariableSelectionStrategy nextVariableSelectionStrategy = new  IterativeNextVariableSelectionStrategy();
    //private NextVariableSelectionStrategy nextVariableSelectionStrategy = new MRVHeuristicsSelectionStrategy();


    private Map<Cell, Set<Integer>> initialDomains(int n)
    {
        Map<Cell, Set<Integer>> map = new HashMap<>();
        for (int row=0; row<n; row++)
        {
            for (int col=0; col<n; col++)
            {
                Set<Integer> set = new HashSet<>(n);
                for (int k=1; k<=n; k++)
                {
                    set.add(k);
                }
                map.put(new Cell(row,col), set);

            }
        }
        return map;
    }

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

                Map<Cell, Set<Integer>> copyRemainingVariables = copyMap(remainingVariables);

                removeNotValidValues(copyRemainingVariables, row, col, problem);


                Pair nextVariable = nextVariableSelectionStrategy.selectNextVariable(problem, row, col, copyRemainingVariables);
                if (nextVariable.row != -1 && nextVariable.col != -1) {
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

    private void removeNotValidValues(Map<Cell, Set<Integer>> remainingValues, int row, int col, Problem problem) {

        for (Cell cell : remainingValues.keySet())
        {
            if (cell.row == row || cell.col == col) {
                Set<Integer> set = remainingValues.get(cell);
                Iterator<Integer> iterator = set.iterator();

                while (iterator.hasNext()) {
                    int value = iterator.next();
                    if (!problem.isValid(cell.row, cell.col, value)) {
                        iterator.remove();
                    }
                }
            }
        }

    }

    private Map<Cell, Set<Integer>> copyMap(Map<Cell, Set<Integer>> source)
    {
        Map<Cell, Set<Integer>> copy = new HashMap<>();
        for (Cell cell : source.keySet())
        {
            Set<Integer> sourceSet = source.get(cell);
            Set<Integer> copySet = new HashSet<>(sourceSet);

            copy.put(cell, copySet);
        }

        return copy;
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
