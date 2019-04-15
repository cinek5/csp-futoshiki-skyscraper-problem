import com.sun.rowset.internal.Row;

import java.util.*;

/**
 * Created by Cinek on 10.04.2019.
 */
public class SkyscraperProblem implements Problem {

    private int[][] representation;
    private int n;

    private List<Set<Integer>> rows;
    private List<Set<Integer>> cols;
    private Map<Integer, RowConstraint> rowConstraints;
    private Map<Integer, ColConstraint> colConstraints;

    public SkyscraperProblem(int[][] representation, int n, Map<Integer, RowConstraint> rowConstraints, Map<Integer, ColConstraint> colConstraints)
    {
        this.representation = representation;
        this.n = n;
        this.rowConstraints = rowConstraints;
        this.colConstraints = colConstraints;
        initCols();
        initRows();
    }

    @Override
    public boolean isValid(int row, int col, int value) {
        return checkUniqueInRowOrColConstraint(row, col, value) && checkSkyscraperConstraints(row, col, value);
    }

    @Override
    public void setValue(int row, int col, int value) {
        representation[row][col]= value;

        if (value!=0)
        {
            addValue(row, col, value);
        }

    }

    @Override
    public void unsetValue(int row, int col, int value) {
        representation[row][col] = 0;
        removeValue(row,col,value);
    }

    @Override
    public boolean hasValue(int row, int col) {
        return representation[row][col]!=0;
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

    @Override
    public int[][] getRepresentation() {
        return representation;
    }

    @Override
    public int getN() {
        return n;
    }

    private boolean checkUniqueInRowOrColConstraint(int r, int c, int val)
    {
        Set<Integer> row = rows.get(r);
        Set<Integer> col = cols.get(c);

        return !row.contains(val) && !col.contains(val);

    }
    private boolean checkSkyscraperConstraints(int r, int c, int val)
    {

        if (!checkSkyscraperTop(r,c, val))
        {
            return false;
        }
        if (!checkSkyscraperBottom(r,c,val))
        {
            return false;
        }
        if (!checkSkyscraperLeft(r,c,val))
        {
            return false;
        }
        if (!checkSkyscraperRight(r,c, val))
        {
            return false;
        }

        return true;


    }

    private boolean checkSkyscraperTop(int r, int c, int val)
    {
        if (colConstraints.get(c).getTop()!=0)
        {
            int highest = -1;
            int visible = 0;
            for (int i = 0; i < n; i++) {

                int current=representation[i][c];
                if (i==r)
                {
                    current = val;
                }
                if ( current!=0 && current > highest )
                {
                    visible++;
                    highest = current;
                }

            }
            int unassigned = numberOfUnassignedForCol(c);
            if (unassigned == 1)
            {
                return visible == colConstraints.get(c).getTop();
            }

        }
        return true;
    }
    private boolean checkSkyscraperBottom(int r, int c, int val)
    {
        if (colConstraints.get(c).getBottom()!=0)
        {
            int highest = -1;
            int visible = 0;

            for (int i = n-1; i >= 0; i--) {

                int current=representation[i][c];
                if (i==r)
                {
                    current = val;
                }

                if (current!=0 && current>highest )
                {
                    visible++;
                    highest = current;
                }
            }
            int unassigned = numberOfUnassignedForCol(c);
            if (unassigned == 1)
            {
                return visible == colConstraints.get(c).getBottom();
            }

        }
        return true;

    }


    private boolean checkSkyscraperLeft(int r, int c, int val)
    {
        if ( rowConstraints.get(r).getLeft()!=0)
        {
            int highest = -1;
            int visible = 0;
            for (int i = 0; i < n; i++) {
                int current=representation[r][i];
                if (i==c)
                {
                    current = val;
                }
                if (current >=highest )
                {
                    visible++;
                    highest = current;
                }
            }
            int unassigned = numberOfUnassignedForRow(r);
            if (unassigned == 1)
            {
                return visible == rowConstraints.get(r).getLeft();
            }
        }
        return true;

    }

    private boolean checkSkyscraperRight(int r, int c, int val)
    {
        if (rowConstraints.get(r).getRight()!=0)
        {
            int highest = -1;
            int visible = 0;
            for (int i = n-1; i >=0; i--) {
                int current = representation[r][i];
                if (i==c)
                {
                    current = val;
                }
                if (current >= highest )
                {
                    visible++;
                    highest = current;
                }


            }
            int unassigned = numberOfUnassignedForRow(r);
            if (unassigned == 1)
            {
                return visible == rowConstraints.get(r).getRight();
            }

        }
        return true;
    }

    private int numberOfUnassignedForRow(int row)
    {
        return n-rows.get(row).size();
    }
    private int numberOfUnassignedForCol(int col)
    {
        return n-cols.get(col).size();
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


}
