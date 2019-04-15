/**
 * Created by Cinek on 10.04.2019.
 */
public interface Problem {
    boolean isValid(int row, int col, int value);
    void setValue(int row, int col, int value);
    void unsetValue(int row, int col, int value);
    boolean hasValue(int row, int col);
    boolean allAssigned();
    int[][] getRepresentation();
    int getN();

}
