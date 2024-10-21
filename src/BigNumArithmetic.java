import java.io.*;
import java.util.*;

public class BigNumArithmetic {
    private static LStack stack = new LStack();

    public static void main(String[] args) throws Exception {

        BigInteger bigInteger = new BigInteger();
        BigNumArithmetic bigNumArithemtic = new BigNumArithmetic();

        int numbers = 0;
        int operators = 0;
        boolean uneven = false;
    
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

                        LList result = bigInteger.add(list1, list2);
                        stack.push(bigInteger.bigIntegerString(result));}
                    else if (equation[i].equals("*")) {
                        operators ++;
                        
                        //pop of 2 most recent items on the stack
                        Object tempObject1 = stack.pop();
                        Object tempObject2 = stack.pop();

                        //turn those items into strings
                        String tempString1 = tempObject1.toString();
                        String tempString2 = tempObject2.toString();
    
                        //input strings into bigInteger to turn them into linked lists
                        LList list1 = bigInteger.bigInteger(tempString1);
                        LList list2 = bigInteger.bigInteger(tempString2);

                        LList result = bigInteger.mult(list1, list2);
                        String resultString = bigInteger.bigIntegerString(result);
                        String temp = zeros(resultString);
                        stack.push(temp);
                        }

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
                        LList r = bigInteger.exp_by_squaring(numList, exponent);
                        stack.push(bigInteger.bigIntegerString(r));
                        
                    

                        LList result = bigInteger.exp_by_squaring(numList, exponent);
                        stack.push(bigInteger.bigIntegerString(result));
                    }
                    else {
                        
                        numbers++;
                        String string = equation[i];
                        string = zeros(string);
                        stack.push(string);
                    }
                }
                
                //check if line is valid (if there are more numbers then operators it will print a blank as the answer)
                if (numbers == operators + 1) {
                    System.out.println(line + " = " + stack.pop());
                    numbers =0;
                    operators =0;
                } else {
                    System.out.println(line + " = ");
                    numbers =0;
                    operators =0;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Invalid File");
        }
    }


//this is removing all of the extra spaces from one line of input
    public String noExSpaces(String line) {
        line = line.replaceAll("( )+", " ");
        if (line.charAt(0) == ' ') {
            line = line.replaceFirst("( )+", "");
        }
        
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