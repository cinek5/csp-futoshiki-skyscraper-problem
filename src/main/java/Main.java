import java.io.FileNotFoundException;

/**
 * Created by Cinek on 09.04.2019.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Loader loader = new Loader();
        FutoshikiProblem problem = loader.loadFutoshikiProblemData("C:\\Users\\Cinek\\Documents\\projektyJAVA\\csp-futoshiki-skyscraper-problem\\src\\main\\resources\\test_futo_7_0.txt");
        System.out.println();
        Backtracking backtracking = new Backtracking();
        backtracking.solve(problem);
    }
}
