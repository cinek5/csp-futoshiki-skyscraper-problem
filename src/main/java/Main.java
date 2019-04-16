import java.io.FileNotFoundException;

/**
 * Created by Cinek on 09.04.2019.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Loader loader = new Loader();
        String filename = "test_futo_7_1.txt";
        Problem problem = loader.loadFutoshikiProblemData("C:\\Users\\Cinek\\Documents\\projektyJAVA\\csp-futoshiki-skyscraper-problem\\src\\main\\resources\\"+filename);
        //Problem problem = loader.loadSkyscraperProblem("C:\\Users\\Cinek\\Documents\\projektyJAVA\\csp-futoshiki-skyscraper-problem\\src\\main\\resources\\"+filename);

        System.out.println("Plik" + filename);
        System.out.println();
        System.out.println();
        System.out.println(" Backtracking bez heurystyk, bez zapisywania wartości:   ");
        long start = System.nanoTime();
        CleanBacktracking cleanBacktracking = new CleanBacktracking();
        cleanBacktracking.solve(problem);
        long end = System.nanoTime();

        System.out.println("Czas wykonania: "+(end-start)/1000000000.0 + " s");
        System.out.println("Liczba iteracji: "+cleanBacktracking.getNumOfIterations());



        System.out.println();
        System.out.println();
        System.out.println(" Backtracking:  ");
        start = System.nanoTime();
        Backtracking backtracking = new Backtracking();
        backtracking.solve(problem);
        end = System.nanoTime();

        System.out.println("Czas wykonania: "+(end-start)/1000000000.0 + " s");
        System.out.println("Liczba iteracji: "+backtracking.getNumOfIterations());


        System.out.println("\n\n\n");

        System.out.println(" Forward checking:  ");
        start = System.nanoTime();
        ForwardChecking forwardChecking = new ForwardChecking();
        forwardChecking.solve(problem);
        end = System.nanoTime();
        System.out.println("Czas wykonania: "+(end-start)/1000000000.0 + " s");
        System.out.println("Liczba iteracji: "+forwardChecking.getNumOfIterations());




    }
}
