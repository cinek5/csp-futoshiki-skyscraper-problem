import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Cinek on 09.04.2019.
 */
public class FutoshikiProblem  implements Problem{
    private int[][] representation;
    private int n;

    List<Set<Integer>> rows;
    List<Set<Integer>> cols;

    List<FutoshikiLessThanConstraint> lessThanConstraints;



    public FutoshikiProblem getCopy()
    {
        FutoshikiProblem futoshikiProblem = new FutoshikiProblem(n);

        return futoshikiProblem;
    }

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

    public void setLessThanConstraints(List<FutoshikiLessThanConstraint> lessThanConstraints)
    {
        this.lessThanConstraints = lessThanConstraints;
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
