import java.io.*;
import java.util.*;

public class BigNumArithmetic {
    String [] equation;
    LStack stack = new LStack();
    public void main(String[] args) throws Exception {

        String fileName = args[0];

        try{
        File file = new File (fileName);
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            line = noExSpaces(line);
            String[] equation = splitUp(line);
                for (int i=0; i<equation.length; i++) {
                    if (equation[0].equals("+")) { this.add(equation); }
                    else if (!equation[0].equals("+") || !equation[0].equals("*")) {
                        String string = equation[0];
                        int num = Integer.parseInt(string);
                        stack.push(num);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Invalid File");
        }
    }



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
    public String zeros(String part) { 
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

//addition method
    public void add(String[] equation) {
        int num1 = stack.pop(); 
        int num2 = stack.pop();
        int result = num1 + num2;
        stack.push(result);
    }
}