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
            noExSpaces(line);
            System.out.println(line);
        }

        } catch (FileNotFoundException e) {
            System.out.println("Invalid File");
        }
    }
    //this is removing all of the extra spaces from one line of input
    public static String noExSpaces(String line) { // this needs to not be static but unsure how 
        line = line.replaceAll("( )+", " ");
        return line;

    }
}