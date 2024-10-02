import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BigNumArithmetic {
    public static void main(String[] args) throws Exception {
        String fileName = args[0];

        try{
        File file = new File (fileName);
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            System.out.println(line);
        }

        } catch (FileNotFoundException e) {
            System.out.println("Invalid File");

            //testing this out
        }
    }
}
