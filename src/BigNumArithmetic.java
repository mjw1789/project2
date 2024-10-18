import java.io.*;
import java.util.*;

public class BigNumArithmetic {
    private static LStack stack = new LStack();

    public static void main(String[] args) throws Exception {

        BigInteger bigInteger = new BigInteger();
        BigNumArithmetic bigNumArithemtic = new BigNumArithmetic();

    
        String fileName = args[0];

        try{
        File file = new File (fileName);
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            line = bigNumArithemtic.noExSpaces(line);
            String[] equation = bigNumArithemtic.splitUp(line);
                for (int i=0; i<equation.length; i++) {
                    if (equation[i].equals("+")) { bigInteger.add(); }
                    else if (equation[i].equals("*")) { bigInteger.mult(equation); }
                    else if (!equation[i].equals("+") || !equation[i].equals("*") || !equation[i].equals("^")) {
                        String string = equation[i];
                        string = zeros(string);
                        stack.push(string);
                    }
                }
                System.out.println(stack.pop());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Invalid File");
        }
    }

//getter method for stack
    public LStack getStack() {return stack;}

    //this is removing all of the extra spaces from one line of input
    public String noExSpaces(String line) {
        line = line.replaceAll("( )+", " ");
        return line;
    }

//split one line at the spaces that are left
    public String[] splitUp(String line) {
        String[] part = line.split(" ");
        return part;
    }

//get rid of all leading zeros
    public static String zeros(String part) {
        int x = 0;
        String p = "";
        
        while (x < part.length() && part.charAt(x) == '0') {
            x++;
        }
        while (x < part.length()) {
            p += part.charAt(x);
            x++;
        }
        if (p == "") {
            p = "0";
        }
        return p;
    }
}
