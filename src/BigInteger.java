public class BigInteger {
    LList list = new LList();
    private int size;
    private BigNumArithmetic main = new BigNumArithmetic();
    private LStack stack;
    private String resultString;
    private int total;
    LList resultTemp;
  
  public LList bigInteger(String input) {
    LList list = new LList();
    int length = input.length();
    size = length;
    for (int i=0; i<length; i++) {
      char c = input.charAt(i);
      int val = Character.getNumericValue(c);
      list.append(val);
    }
    return list;
  }

  public void add () {
    LList result = new LList();
    stack = main.getStack();
    int over = 0;

    //pop of 2 most recent items on the stack
    Object tempObject1 = stack.pop();
    Object tempObject2 = stack.pop();

    //turn those items into strings
    String tempString1 = tempObject1.toString();
    String tempString2 = tempObject2.toString();
    
    //input strings into bigInteger to turn them into linked lists
    LList list1 = this.bigInteger(tempString1);
    LList list2 = this.bigInteger(tempString2);

    //System.out.println(this.bigIntegerString(list1));
    //System.out.println(this.bigIntegerString(list2));

    //get size of lists
    int list1Size = list1.length();
    int list2Size = list2.length();

    //if one list is smaller then the other add zeros to the front so they are the same size
    if (list1Size < list2Size) {
      while(list1Size < list2Size) {
        list1.addFront(0);
        list1Size++;
        }
    } else {
      while(list1Size > list2Size) {
        list2.addFront(0);
        list2Size++;
        }
      }

      //once the two lists are an equal length add them
      if (list1Size == list2Size) {

        list1.moveToEnd();
        list2.moveToEnd();

        for (int i = list1Size -1; i >= 0; i--) {

          int val1int = (Integer) list1.getValue();
          int val2int = (Integer) list2.getValue();
          total = val1int + val2int;


          //if you carry a number over add that to the total
          if (over == 1) {
          total = total + over;
          }

          if (total > 9) {
              over = 1;
              total = total - 10;
          }
          else {
              over = 0;
          }

          result.addFront(total);

          /*resultString = total + resultString;
          if (resultString.contains("null")){
            resultString = resultString.substring(0, resultString.length() - 4);} */

            //System.out.println("RESULT "+ resultString);

          list1.prev();
          list2.prev();

      }
    }       
            if (over == 1) {
              result.addFront(1);
            }
            stack.push(this.bigIntegerString(result));
            //resultString = "";

      //System.out.println(this.bigIntegerString(list1));
      //System.out.println(this.bigIntegerString(list2));

  }

  public LList mult(LList list1, LList list2) {
    stack = main.getStack();
    int over = 0;
    int count = 0;
    String statment = "";

    LList list11 = list1;
    LList list22 = list2;

    //get size of lists
    int list1Size = list1.length();
    int list2Size = list2.length();

    list1.moveToEnd();
    list2.moveToEnd();

    if (list2Size > list1Size) {

      //swap places
      LList temp1 = list2;
      LList temp2 = list1;

      list1 = temp1;
      list2 = temp2;

      list1Size = list1.length();
      list2Size = list2.length();
    }

    if (list1Size == list2Size || list1Size > list2Size) {

      for (int i = list2Size - 1; i >= 0; i--){
        String number = "";
        int moveOver = 0;
        int val2int = (Integer) list2.getValue();
        for (int x = list1Size -1; x >= 0; x--) {
            int val1int = (Integer) list1.getValue();
            int total = (val1int * val2int) + over;
            String totaltemp = Integer.toString(total);
            number = number + totaltemp;
            System.out.println(number);

            if (total < 9) {
              over = 0;
            }
            while(total > 9) {
                total /= 10;
            }
        }
        for (int z = 0; z < moveOver; z++) {
          number = number + "0";
        }
        moveOver++;
        statment = statment + number;
      }
    }
    /*else {
      for (int i = list1Size - 1; i >= 0; i--){
        String number = "";
        int moveOver = 0;
        int val1int = (Integer) list1.getValue();
        for (int x = list2Size -1; x >= 0; x--) {
            int val2int = (Integer) list2.getValue();
            int total = (val1int * val2int) + over;
            String totaltemp = Integer.toString(total);
            number = number + totaltemp;

            if (total < 9) {
              over = 0;
            }
            while(total > 9) {
                total /= 10;
            }
        }
        for (int z = 0; z < moveOver; z++) {
          number = number + "0";
        }
        moveOver++;
        count++;
        statment = statment + number;
      }
    }*/
    for (int c = 0; c < count -1; c++) {
      statment = statment + " +";
    }

    System.out.println("ANSWER " + statment);

    return list;

    /*BigNumArithmetic c = new BigNumArithmetic();

                String[] adding = c.splitUp(statment);
                for (int x=0; x<adding.length; x++) {
                    if (adding[x].equals("+")) { c.add(adding); }
                    else if (adding[x].equals("*")) { c.mult(adding); }
                    else if (!adding[x].equals("+") || !adding[x].equals("*") || !adding[x].equals("^")) {
                        String string = adding[x];
                        string = zeros(string);
                        stack.push(string);
                    }
                }*/
  }

  /*public LList exp_by_squaring(LList numList, int exponent) {

    int n = exponent;
    LList x = numList;
    LList xx = mult (x,x);
    LList sec = exp_by_squaring(xx, (n - 1) / 2);

    
    if (n == 0){ resultTemp.append(1);}
    else if (n % 2 == 0) {resultTemp = exp_by_squaring(xx , n / 2);}
    else if (n % 2 != 0) {resultTemp = mult(x, sec);}

    bigIntegerString(resultTemp);
    stack.push(resultString);
    return resultTemp;
}*/

  public String bigIntegerString(LList input) {
    String result = "";
    
    input.moveToStart();
    input.next();
    for (int i=0; i<input.length(); i++) {
      result = result + input.getValue();
      input.next();
    }
    return result;
  }
}
