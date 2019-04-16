import java.io.FileNotFoundException;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by Cinek on 09.04.2019.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Loader loader = new Loader();

//        List<String> suffixes = asList("4_0","4_1","4_2","4_3","5_0","5_1", "5_2","5_3",
//                "6_0","6_1","6_2", "6_3" );

        for (String suffix: asList("6_0")) {
            String basefilename = "test_sky_";
            String filename=basefilename+suffix+".txt";
            //Problem problem = loader.loadFutoshikiProblemData("C:\\Users\\Cinek\\Documents\\projektyJAVA\\csp-futoshiki-skyscraper-problem\\src\\main\\resources\\"+filename);
            Problem problem = loader.loadSkyscraperProblem("C:\\Users\\Cinek\\Documents\\projektyJAVA\\csp-futoshiki-skyscraper-problem\\src\\main\\resources\\" + filename);

            System.out.println("Plik  " + filename);
            System.out.println();
            System.out.println();
            System.out.println(" Backtracking bez heurystyk, bez zapisywania wartości:   ");
            long start = System.nanoTime();
            CleanBacktracking cleanBacktracking = new CleanBacktracking();
            cleanBacktracking.solve(problem);
            long end = System.nanoTime();

            System.out.println("Czas wykonania: " + (end - start) / 1000000000.0 + " s");
            System.out.println("Liczba iteracji: " + cleanBacktracking.getNumOfIterations());


//        System.out.println();
//        System.out.println();
//        System.out.println(" Backtracking:  ");
//        long start = System.nanoTime();
//        Backtracking backtracking = new Backtracking();
//        backtracking.solve(problem);
//        long end = System.nanoTime();
//
//        System.out.println("Czas wykonania: "+(end-start)/1000000000.0 + " s");
//        System.out.println("Liczba iteracji: "+backtracking.getNumOfIterations());


            System.out.println("\n\n\n");

            System.out.println(" Forward checking:  ");
            start = System.nanoTime();
            ForwardChecking forwardChecking = new ForwardChecking();
            forwardChecking.solve(problem);
            end = System.nanoTime();
            System.out.println("Czas wykonania: " + (end - start) / 1000000000.0 + " s");
            System.out.println("Liczba iteracji: " + forwardChecking.getNumOfIterations());

        }


    }
}
