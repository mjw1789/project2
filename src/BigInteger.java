public class BigInteger {
    LList list = new LList();
    private int size;
    private BigNumArithmetic main = new BigNumArithmetic();
    private LStack stack;
    //private LStack result;
    private String resultString;
    private int total;
    LList resultTemp;
    LList prodTOTAL = new LList();
  
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

  public LList add (LList list1, LList list2) {
    LList result = new LList();
    stack = main.getStack();
    int over = 0;

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

          list1.prev();
          list2.prev();

      }
    }       
            if (over == 1) {
              result.addFront(1);
            }
          return result;
  }

  public LList mult(LList list1, LList list2) {
    stack = main.getStack();
    
    int over = 0;
    int count = 0;
    int prod = 0;
    int totalProd = 0;
    int product = 0;
    String statment = "";

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

    System.out.println(bigIntegerString(list2));
    System.out.println(bigIntegerString(list1));

    list1.moveToEnd();
    list2.moveToEnd();
    int multiplier1 = 1;
    if (list1Size == list2Size || list1Size > list2Size) {
      for (int i = list2Size - 1; i >= 0; i--){
        int valmain = (Integer) list2.getValue();
        int multiplier2 = 1;

          for (int x = list1Size - 1; x >= 0; x--){
            int val = (Integer) list1.getValue();
            System.out.println("MAIN" + valmain);
            System.out.println("STUFF" + val);

            prod = valmain * val * multiplier2 * multiplier1;
            System.out.println("PROD " + prod);
            multiplier2 = multiplier2 * 10;
            String s = Integer.toString(prod);
            LList p = bigInteger(s);
            prodTOTAL = add(prodTOTAL, p);
            //totalProd = totalProd + prod;
            list1.prev();
            //System.out.println("TOTALPROD " + "PASS " + x + " " + totalProd);
          }
          //System.out.println("PROD " + totalProd);

        multiplier1 = multiplier1 * 10;
        prod = 0;
        list1.moveToEnd();
        list2.prev();
    }
  }
    System.out.println("PRODUCT");
    System.out.println(bigIntegerString(prodTOTAL));
    return prodTOTAL;
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
