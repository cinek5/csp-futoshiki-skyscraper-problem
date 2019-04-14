import java.io.FileNotFoundException;

/**
 * Created by Cinek on 09.04.2019.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Loader loader = new Loader();
        Problem problem = loader.loadFutoshikiProblemData("C:\\Users\\Cinek\\Documents\\projektyJAVA\\csp-futoshiki-skyscraper-problem\\src\\main\\resources\\test_futo_7_2.txt");
        System.out.println();
        long start = System.nanoTime();
        Backtracking backtracking = new Backtracking();
        backtracking.solve(problem);
        long end = System.nanoTime();

        System.out.println((end-start)/1000000000.0 + " s");
    }
}
