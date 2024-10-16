import java.io.*;
import java.util.*;

public class BigNumArithmetic {
    String [] equation;
    static LStack stack = new LStack();
    static LStack result = new LStack();
    String resultString;
    public static void main(String[] args) throws Exception {

        BigNumArithmetic b = new BigNumArithmetic();

        String fileName = args[0];

        try{
        File file = new File (fileName);
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            line = b.noExSpaces(line);
            String[] equation = b.splitUp(line);
                for (int i=0; i<equation.length; i++) {
                    if (equation[i].equals("+")) { b.add(equation); }
                    else if (!equation[i].equals("+") || !equation[i].equals("*")) {
                        String string = equation[i];
                        stack.push(string);
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
        Object temp1 = stack.pop();
        Object temp2 = stack.pop();
        String temp11 = temp1.toString();
        String temp22 = temp2.toString();
        int over = 0;

        BigInteger value1 = new BigInteger();
        BigInteger value2 = new BigInteger();

        LList val1temp = value1.bigInteger(temp11);
        LList val2temp = value2.bigInteger(temp22);

        int val1Length = value1.bigIntegerSize();
        int val2Length = value2.bigIntegerSize();

        if (val1Length == val2Length) {
            int total;

            val1temp.moveToEnd();
    
            val2temp.moveToEnd();


            for (int i = val1Length -1; i >= 0; i--) {

                int val1int = (Integer) val1temp.getValue();
                int val2int = (Integer) val2temp.getValue();
                total = val1int + val2int + over;

                if (total > 9) {
                    over = 1;
                    total -= 10;
                }
                else {
                    over = 0;
                }

                value1.getBigInteger().setValue(total);

                String s = Integer.toString(total);
                //System.out.println(s);
                stack.push(s);

                val1temp.prev();
                val2temp.prev();
            }
            
            if (over != 0) {
                value1.getBigInteger().addFront(1);
            }
            while (!result.isEmpty()) {
                Object e = result.pop();
                String q = String.valueOf(e);
                System.out.println(q);
                resultString = resultString + q;
                System.out.println(resultString);
            }
        }
        else if (val1Length < val2Length) {
            while(val1Length < val2Length) {
                value1.getBigInteger().addFront(0);
                val1Length++;
                }
        } else {
            while(val1Length > val2Length) {
                value2.getBigInteger().addFront(0);
                val2Length++;
                }
            }

        val1temp.moveToEnd();
    
        val2temp.moveToEnd();

        for (int i = val1Length -1; i >= 0; i--) {
            int val1int = (Integer) val1temp.getValue();
            int val2int = (Integer) val2temp.getValue();
            int total = val1int + val2int;

            if (total > 9) {
                over = 1;
                total -= 10;
            }
            else {
                over = 0;
            }

            value1.getBigInteger().setValue(total);

            val1temp.prev();
            val2temp.prev();
        }
        if (over != 0) {
            value1.getBigInteger().addFront(1);
        }
    }

    public void mult(String[] equation) {
        Object temp1 = stack.pop();
        Object temp2 = stack.pop();
        String temp11 = temp1.toString();
        String temp22 = temp2.toString();
        int over = 0;
        char space = ' ';
        String statement = "";
        int count = 0;

        BigInteger value1 = new BigInteger();
        BigInteger value2 = new BigInteger();

        value1.bigInteger(temp11);
        value2.bigInteger(temp22);

        int val1Length = value1.bigIntegerSize();
        int val2Length = value2.bigIntegerSize();

        LList val1temp = value1.getBigInteger();
        val1temp.moveToEnd();

        LList val2temp = value2.getBigInteger();
        val2temp.moveToEnd();

        if (val1Length == val2Length || val1Length > val2Length) {
            for (int i = val2Length - 1; i >= 0; i--){
                String number = "";
                int moveOver = 0;
                int val2int = (Integer) val2temp.getValue();
                for (int x = val1Length -1; x >= 0; x--) {
                    int val1int = (Integer) val1temp.getValue();
                    int total = (val1int * val2int) + over;
                    String totaltemp = Integer.toString(total);
                    for (int z = 0; z < moveOver; z++) {
                        totaltemp = totaltemp + "0";
                    }
                    number = totaltemp + number;
                    if(total > 9) {
                        over = total - 10;
                    }
                    else {
                        total = 0;
                    }
                }
                statement = statement + " " + number;
            }
        } else {
            for (int i = val1Length - 1; i >= 0; i--){
                String number = "";
                int moveOver = 0;
                int val1int = (Integer) val1temp.getValue();
                for (int x = val2Length -1; x >= 0; x--) {
                    int val2int = (Integer) val2temp.getValue();
                    int total = (val2int * val1int) + over;
                    String totaltemp = Integer.toString(total);
                    for (int z = 0; z < moveOver; z++) {
                        totaltemp = totaltemp + "0";
                    }
                    number = totaltemp + number;
                    if(total > 9) {
                        over = total - 10;
                    }
                    else {
                        total = 0;
                    }
                }
                statement = statement + " " + number;
            }
        }
        for (int t = 0; t < statement.length(); t++) {
            if (statement.charAt(t) == space) {
                count++;
            }
        }
        for (int c = 0; c < count - 1; c++) {
            statement = statement + " +";
        }
    }
}