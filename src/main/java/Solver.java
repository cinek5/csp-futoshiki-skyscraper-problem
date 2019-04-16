import java.util.*;

/**
 * Created by Cinek on 15.04.2019.
 */
public abstract class Solver {
   abstract void solve(Problem problem);

//    protected Map<Cell, Set<Integer>> removeNotValidValues(Map<Cell, Set<Integer>> remainingValues, int row, int col, Problem problem) {
//
//        Map<Cell, Set<Integer>> whatWasRemoved  = new HashMap<>();
//        for (Cell cell : remainingValues.keySet())
//        {
//            if (cell.row == row || cell.col == col) {
//                Set<Integer> set = remainingValues.get(cell);
//                Iterator<Integer> iterator = set.iterator();
//
//                Set<Integer> removed = new HashSet<>();
//                while (iterator.hasNext()) {
//                    int value = iterator.next();
//                    if (!problem.isValid(cell.row, cell.col, value)) {
//                        iterator.remove();
//                        removed.add(value);
//                    }
//                }
//                if (!removed.isEmpty())
//                {
//                    whatWasRemoved.put(cell, removed);
//                }
//            }
//        }
//        return whatWasRemoved;
//
//    }

    protected Map<Cell, Set<Integer>> removeNotValidValues(Map<Cell, Set<Integer>> remainingValues, int row, int col, Problem problem) {

        Map<Cell, Set<Integer>> whatWasRemoved = new HashMap<>();

        for (int c = 0; c < problem.getN(); c++) {
            Cell cell = new Cell(row, c);

            Set<Integer> removed = removeNotValidValuesForOneVariable(remainingValues, cell, problem);

            if (!removed.isEmpty()) {
                whatWasRemoved.put(cell, removed);
            }

        }

        for (int r = 0; r < problem.getN(); r++) {
            Cell cell = new Cell(r, col);

            Set<Integer> removed = removeNotValidValuesForOneVariable(remainingValues, cell, problem);

            if (!removed.isEmpty()) {
                whatWasRemoved.put(cell, removed);
            }

        }


        return whatWasRemoved;
    }

    private Set<Integer>  removeNotValidValuesForOneVariable(Map<Cell, Set<Integer>> remainingValues, Cell cell, Problem problem)
    {
        Set<Integer> set = remainingValues.get(cell);
        Iterator<Integer> iterator = set.iterator();

        Set<Integer> removed = new HashSet<>();
        while (iterator.hasNext()) {
            int value = iterator.next();
            if (!problem.isValid(cell.row, cell.col, value)) {
                iterator.remove();
                removed.add(value);
            }
        }
        return removed;
    }

    protected void undoRemovingRemainingValues(Map<Cell, Set<Integer>> remainingValues, Map<Cell, Set<Integer>> whatWasRemoved)
    {
        for (Map.Entry<Cell, Set<Integer>> entry : whatWasRemoved.entrySet())
        {
            Cell cell = entry.getKey();
            Set<Integer> removed = entry.getValue();
            remainingValues.get(cell).addAll(removed);
        }
    }
    protected Map<Cell, Set<Integer>> initialDomains(int n)
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
    protected void backtrack(Problem problem, int row, int col, int val) {
        problem.unsetValue(row, col, val);
    }

    protected void printArray(int[][] arr) {
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr.length; col++) {
                System.out.print(" " + arr[row][col] + " ");
            }
            System.out.println();
        }
    }
}
