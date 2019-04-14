import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Cinek on 10.04.2019.
 */
public class SkyscraperProblem implements Problem {

    private int[][] representation;
    private int n;

    List<Set<Integer>> rows;
    List<Set<Integer>> cols;

    public SkyscraperProblem(int[][] representation, int n)
    {
        this.representation = representation;
        this.n = n;
        initCols();
        initRows();
    }

    @Override
    public boolean isValid(int row, int col, int value) {
        return checkUniqueInRowOrColConstraint(row, col, value);
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

        return false;
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
