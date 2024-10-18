import java.io.*;
import java.util.*;

public class BigNumArithmetic {
    String [] equation;
    static LStack stack = new LStack();
    static LStack result = new LStack();
    LList listResult = new LList();
    LList l = new LList();
    String resultString;
    String r;
    public static void main(String[] args) throws Exception {

        BigNumArithmetic b = new BigNumArithmetic();

        /*BigInteger value1 = new BigInteger();
        LList l = value1.bigInteger("34");
        l.addFront(4);
        System.out.println(value1.bigIntegerString(l));
        System.out.println(value1.bigIntegerString(l));*/

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
                    else if (equation[i].equals("*")) { b.mult(equation); }
                    else if (!equation[i].equals("+") || !equation[i].equals("*") || !equation[i].equals("^")) {
                        String string = equation[i];
                        string = zeros(string);
                        stack.push(string);
                    }
                }
                System.out.println(stack.pop());
                result.clear();
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

        if (val1Length < val2Length) {
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

                listResult.addFront(s);

                //if (over == 0){result.push(s);}


                val1temp.prev();
                val2temp.prev();
            }
            if (over == 1) {
                l = value1.getBigInteger();
                l.addFront(1);
                String p = value1.bigIntegerString(l);
                result.push(p);
                System.out.println("P " + p);
            }

            String k = value1.bigIntegerString(listResult);
            result.push(k);
            result.clear();
            System.out.println("K "+k);
            resultString = "";
                Object e = result.pop();
                String q = String.valueOf(e);
                //resultString = resultString + q;
            
            if (resultString.contains("null")){
            resultString = resultString.substring(4);}
            stack.push(resultString);
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

        LList val1temp = value1.bigInteger(temp11);
        LList val2temp = value2.bigInteger(temp22);

        String s = value1.bigIntegerString(val2temp);

        int val1Length = value1.bigIntegerSize();
        int val2Length = value2.bigIntegerSize();


        if (val1Length == val2Length || val1Length > val2Length) {
            val1temp.moveToEnd();
            val2temp.moveToEnd();

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

        BigNumArithmetic c = new BigNumArithmetic();

                String[] adding = c.splitUp(statement);
                for (int x=0; x<adding.length; x++) {
                    if (adding[x].equals("+")) { c.add(adding); }
                    else if (adding[x].equals("*")) { c.mult(adding); }
                    else if (!adding[x].equals("+") || !adding[x].equals("*") || !adding[x].equals("^")) {
                        String string = adding[x];
                        string = zeros(string);
                        stack.push(string);
                    }
                }
    }
}