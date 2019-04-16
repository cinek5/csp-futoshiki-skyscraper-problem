/**
 * Created by Cinek on 16.04.2019.
 */
public class ConfigForwardChecking {

    //public  static NextVariableSelectionStrategy selectionStrategy = new MaximumConstraintsHeuristicsSelectionStrategy();
    public static NextVariableSelectionStrategy selectionStrategy = new IterativeNextVariableSelectionStrategy();
    //public static  NextVariableSelectionStrategy selectionStrategy = new MRVHeuristicsSelectionStrategy();

}
