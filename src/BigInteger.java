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
  
  
  /*
   * This function:
   * 1) creates a new LList
   * 2) gets the length of the provided input argument
   * 3) loops through a for loop adding each digit of the String 
   *    into the LList until the end of the String is reached
   * 4) returns the LList
   */
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

  /*
   * This function:
   * 1) gets the length of the two lists passed in as arguments
   * 2) checks if the lengths are uneven and if they are, adds zeros to the front to make them even
   * 3) continues with the add function once the two lists are an equal length
   * 4) moves the linked list position to the end for both list1 and list2
   * 5) iterates from beginning to end and adds values in the same position of both linked lists together
   * 6) if the two values add up to more than 9 it carries a one over for the next values (carrying the one)
   * 7) if there is a 1 that carries over at the end it is added to the fron using the add front method
   * 8) returns the result of the addition to BigNumArithmetic to be printed
   */

  public LList add (LList list1, LList list2) {
    LList result = new LList();
    int over = 0;

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

  /*
   * This function:
   * 1) Takes in the list1 and list2 LList arguments
   * 2) gets the size of both lists
   * 3) switches list positions if list2Size is greater than list1Size
   * 4) move both LLists to the end position
   * 5) multiply by each individual digit
   * 6) If there is a number that needs to be carried over set it to over
   * 7) If there is no number to carry over then set over to 0
   * 8) iterate through list1 until you reach the beginning of it
   * 9) after exiting the for loop add zeros to the end of the LList based on the multiplier number
   * 10) if the over is not set to 0 add the over number to the beginning of the list
   * 11) add one to the multiplier
   * 12) add prodTotal to the listMulti LList to get the total
   * 13) clear listMulti
   * 14) go to the previous link in list2 and go back to the end of list1
   * 15) Loop through this process until there is nothing previous left in list2
   * 16) push the prodTOTAL LList to the stack
   */

  public LList mult(LList list1, LList list2) {
    prodTOTAL.clear();
    
    int over = 0;
    int prod = 0;
    int digit1 = 0;
    int digit2 = 0;
    int prodLength = 0;
    String statment = "";
    LList listMulti = new LList();

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

    list1.moveToEnd();
    list2.moveToEnd();

    int multiplier = 0;
    if (list1Size == list2Size || list1Size > list2Size) {
      for (int i = list2Size - 1; i >= 0; i--){
        prodLength = 0;
        int valmain = (Integer) list2.getValue();

          for (int x = list1Size - 1; x >= 0; x--){
            int val = (Integer) list1.getValue();
            prod = valmain * val + over;

            String prodString = String.valueOf(prod);
            prodLength = String.valueOf(prod).length();
            

            if (prodLength > 1) {
              String[] digits = prodString.split("");
              digit1 = Integer.valueOf(digits[0]);
              digit2 = Integer.valueOf(digits[1]);
              over = digit1;
              listMulti.addFront(digit2);
            } else {
              listMulti.addFront(prod);
              over = 0;
            }
            list1.prev();
          }

        for (int w=0; w < multiplier; w++) {listMulti.append(0);}
        
        if (over != 0) {
         
          listMulti.addFront(over);}
          
        multiplier = multiplier + 1;
        prod = 0;
        
        prodTOTAL = add(prodTOTAL, listMulti);
        
        listMulti.clear();
        list2.prev();
        list1.moveToEnd();
        over = 0;
    }
  }
    return prodTOTAL;
}

  public LList exp_by_squaring(LList numList, int exponent) {

    int n = exponent;
    LList x = numList;
    LList xx = mult (x,x);
    LList sec = exp_by_squaring(xx, (n - 1) / 2);

    
    if (n == 0){ resultTemp.append(1);}
    else if (n % 2 == 0) {resultTemp = exp_by_squaring(xx , n / 2);}
    else if (n % 2 != 0) {resultTemp = mult(x, sec);}

    resultString = bigIntegerString(resultTemp);
    stack.push(resultString);
    return resultTemp;
}

  /*
   * This function:
   * 1) Iterates through each position of the LList provided in the argument
   * 2) add each digit to the result String
   * 3) return the result String
   */
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
