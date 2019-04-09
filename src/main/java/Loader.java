import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Cinek on 09.04.2019.
 */
public class Loader {

    private static Map<Character,Integer> mapRowByLetter = new HashMap<>();
    static
    {
        mapRowByLetter.put('A', 0);
        mapRowByLetter.put('B', 1);
        mapRowByLetter.put('C', 2);
        mapRowByLetter.put('D', 3);
        mapRowByLetter.put('E', 4);
        mapRowByLetter.put('F', 5);
        mapRowByLetter.put('G', 6);
        mapRowByLetter.put('H', 7);
        mapRowByLetter.put('I', 8);
        mapRowByLetter.put('J', 9);
    }


    public FutoshikiProblem loadFutoshikiProblemData(String path) throws FileNotFoundException
    {
        File file = new File(path);
        Scanner reader = new Scanner(file);

        int n = Integer.parseInt(reader.nextLine());

        FutoshikiProblem problem = new FutoshikiProblem(n);

        omitLines(reader,1);

        for(int i=0; i<n;i++)
        {
            String row = reader.nextLine();
            String[] rows = row.split(";");
            for (int j=0; j<n; j++)
            {
                int val = Integer.parseInt(rows[j]);
                problem.setValue(i,j,val);
            }
        }
        omitLines(reader,1);
        List<FutoshikiLessThanConstraint> constraintList = new ArrayList<>();
        while(reader.hasNextLine())
        {
            String[] constraints = reader.nextLine().split(";");
            String lesser = constraints[0];
            String greater =constraints[1];
            Character lesserRowLetter = lesser.charAt(0);
            int  lesserRowColumn = Integer.parseInt(String.valueOf(lesser.charAt(1)))-1;
            Character greaterRowLetter = greater.charAt(0);
            int  greaterRowColumn = Integer.parseInt(String.valueOf(greater.charAt(1)))-1;

            Pair lesserPair = new Pair(mapRowByLetter.get(lesserRowLetter), lesserRowColumn);
            Pair greaterPair = new Pair(mapRowByLetter.get(greaterRowLetter), greaterRowColumn);
            FutoshikiLessThanConstraint futoshikiLessThanConstraint= new FutoshikiLessThanConstraint(lesserPair, greaterPair);
            constraintList.add(futoshikiLessThanConstraint);
        }
        problem.setLessThanConstraints(constraintList);

        return problem;
    }



    private void omitLines(Scanner scanner, int numberOfLines)
    {
        for (int i=0; i< numberOfLines; i++ )
        {
            scanner.nextLine();
        }
    }
    private void omitWords(Scanner scanner, int numberOfWords)
    {
        for (int i=0; i<numberOfWords; i++)
        {
            scanner.next();
        }
    }


}