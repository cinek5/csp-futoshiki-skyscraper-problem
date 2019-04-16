/**
 * Created by Cinek on 09.04.2019.
 */
public class FutoshikiLessThanConstraint {
    private Pair lesser;
    private Pair greater;

    public FutoshikiLessThanConstraint(Pair lesser, Pair greater) {
        this.lesser = lesser;
        this.greater = greater;
    }

    public Pair getLesser() {
        return lesser;
    }

    public void setLesser(Pair lesser) {
        this.lesser = lesser;
    }

    public Pair getGreater() {
        return greater;
    }

    public void setGreater(Pair greater) {
        this.greater = greater;
    }

    public boolean isConstrained(int row, int col)
    {
        return (lesser.row==row && lesser.col==col) || (greater.row == row && greater.col == col);
    }
}
