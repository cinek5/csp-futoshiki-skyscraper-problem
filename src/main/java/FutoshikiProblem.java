import java.util.*;

import static java.util.Arrays.asList;

/**
 * Created by Cinek on 09.04.2019.
 */
public class FutoshikiProblem  implements Problem{
    private int[][] representation;
    private int n;

    List<Set<Integer>> rows;
    List<Set<Integer>> cols;

    List<FutoshikiLessThanConstraint> lessThanConstraints;

    Map<Cell, Integer>  numOfConstraintsPerCell;



    public int[][] getRepresentation() {
        return representation;
    }

    public FutoshikiProblem(int n)
    {
        this.n = n;
        representation = new int[n][n];
        initCols();
        initRows();
    }

    public int getN()
    {
        return n;
    }

    @Override
    public int getNumberOfConstraints(int row, int col) {
        Cell cell = new Cell(row,col);
        int valuesInRowAndCol = rows.get(row).size() + cols.get(col).size();
        if (numOfConstraintsPerCell.containsKey(cell))
        {
            return (numOfConstraintsPerCell.get(cell)*20) + valuesInRowAndCol;
        }
        else
        {
            return valuesInRowAndCol;
        }
    }

    public void setLessThanConstraints(List<FutoshikiLessThanConstraint> lessThanConstraints)
    {
        this.lessThanConstraints = lessThanConstraints;
        numOfConstraintsPerCell = getNumOfConstraintsPerCell();
    }


    public void setValue(int row, int col, int val)
    {
        representation[row][col]= val;

        if (val!=0)
        {
            addValue(row, col, val);
        }

    }

    public void unsetValue(int row, int col, int val)
    {
        representation[row][col] = 0;
        removeValue(row,col,val);
    }

    @Override
    public boolean hasValue(int row, int col) {
        return representation[row][col]!=0;
    }

    @Override
    public boolean allAssigned() {
        for (int row=0; row<n; row++)
            for(int col=0; col<n; col++)
            {
                if (representation[row][col]==0)
                    return false;
            }
        return  true;
    }

    public void addValue(int row, int col, int val)
    {
        rows.get(row).add(val);
        cols.get(col).add(val);
    }

    public void removeValue(int row, int col, int val)
    {
        rows.get(row).remove(val);
        cols.get(col).remove(val);
    }

    private void initRows()
    {
        rows = new ArrayList<>(n);
        for (int i=0; i<n; i++)
        {
            rows.add(new HashSet<>(n));
        }
    }
    private void initCols()
    {
        cols = new ArrayList<>(n);
        for (int i=0; i<n; i++)
        {
            cols.add(new HashSet<>(n));
        }

    }

    public boolean isValid(int r, int c, int val)
    {
        return checkUniqueInRowOrColConstraint(r,c,val) && checkLessThanConstraints(r, c, val);
    }

    private Map<Cell ,Integer> getNumOfConstraintsPerCell()
    {
        Map<Cell, Integer> constraintsPerCell = new HashMap<>();
        for (FutoshikiLessThanConstraint constraint: lessThanConstraints)
        {
            Pair greater =constraint.getGreater();
            Pair lesser =constraint.getLesser();

            Cell cell1 = new Cell(greater.row, greater.col);
            Cell cell2 = new Cell(lesser.row, lesser.col);

            for (Cell cell : asList(cell1,cell2))
            {
                if (constraintsPerCell.containsKey(cell))
                {
                   int numOfConstraints=  constraintsPerCell.get(cell);
                   constraintsPerCell.remove(cell);
                   constraintsPerCell.put(cell, numOfConstraints+1);
                }
                else {
                    constraintsPerCell.put(cell, 1);
                }

            }

        }
        return constraintsPerCell;
    }



    private boolean checkUniqueInRowOrColConstraint(int r, int c, int val)
    {
        Set<Integer> row = rows.get(r);
        Set<Integer> col = cols.get(c);

        return !row.contains(val) && !col.contains(val);

    }
    private boolean checkLessThanConstraints(int r, int c, int val)
    {
        for (FutoshikiLessThanConstraint constraint : lessThanConstraints)
        {
            Pair lesser = constraint.getLesser();
            Pair greater = constraint.getGreater();

            int lsr = representation[lesser.row][lesser.col];
            int grt = representation[greater.row][greater.col];
            if (lesser.row == r && lesser.col==c)
            {
                lsr = val;
            }
            if (greater.row == r && greater.col == c)
            {
                grt = val;
            }

            if (lsr != 0 && grt!=0  && lsr>=grt)
            {
                return false;
            }
        }
        return true;
    }
}
