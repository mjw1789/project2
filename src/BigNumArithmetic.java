import java.io.*;
import java.util.*;

public class BigNumArithmetic {
    private static LStack stack = new LStack();

    public static void main(String[] args) throws Exception {

        BigInteger bigInteger = new BigInteger();
        BigNumArithmetic bigNumArithemtic = new BigNumArithmetic();

        int numbers = 0;
        int operators = 0;

        String fileName = args[0];

        try{
        File file = new File (fileName);
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            line = bigNumArithemtic.noExSpaces(line);
            String[] equation = bigNumArithemtic.splitUp(line);
                for (int i=0; i<equation.length; i++) {
                    if (equation[i].equals("+")) {
                        bigInteger.add();
                        operators++;
                    }
                    else if (equation[i].equals("*")) {
                        operators++;
                        
                        //pop of 2 most recent items on the stack
                        Object tempObject1 = stack.pop();
                        Object tempObject2 = stack.pop();

                        //turn those items into strings
                        String tempString1 = tempObject1.toString();
                        String tempString2 = tempObject2.toString();
    
                        //input strings into bigInteger to turn them into linked lists
                        LList list1 = bigInteger.bigInteger(tempString1);
                        LList list2 = bigInteger.bigInteger(tempString2);

                        bigInteger.mult(list1, list2); }

                    else if (equation[i].equals("^")) {
                        operators++;

                        //get expoenent
                        Object tempExponent = stack.pop();
                        String expString = tempExponent.toString();
                        int exponent = Integer.parseInt(expString);

                        //get num value
                        Object num = stack.pop();
                        String numString = num.toString();
                        LList numList = bigInteger.bigInteger(numString);

                        //pass values to exp method
                        //bigInteger.exp_by_squaring(numList, exponent);
                        }
                    else {
                        numbers++;
                        String string = equation[i];
                        string = zeros(string);
                        stack.push(string);
                    }
                }

                //if there is one less opperator than numbers than it is corrent and if not that is the else statment
                if (numbers == operators + 1) {
                    System.out.println(line + " = " + stack.pop());
                } else {
                    System.out.println(line + " = ");
                }
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
