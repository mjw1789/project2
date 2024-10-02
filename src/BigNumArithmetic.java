import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BigNumArithmetic {
    public static void main(String[] args) throws Exception {
        //String fileName = args[0];

        try{
        File file = new File ("read.txt");
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            line = noExSpaces(line);
            String[] part = splitUp(line);

            int i = 0;

            while (i < part.length) {
                part[i] = zeros(part[i]);
                i++;
            }

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
    //split one line at the spaces that are left
    public static String[] splitUp(String line) { //also should not be static
        String[] part = line.split(" ");
        return part;
    }

    //get rid of all leading zeros
    public static String zeros(String part) { // once again should not be static
        int x = 0;
        String p = "";
        
        if (part.equals(" ")) {
            return null; //not sure if this will work once tested
            //the point is that if the first space is zero
            //then we do nothing and move on
        }
        while (x < part.length() && part.charAt(x) == '0') {
            x++;
        }
        while (x < part.length()) {
            p += part.charAt(x);
            x++;
        }
        return p;
    }
}