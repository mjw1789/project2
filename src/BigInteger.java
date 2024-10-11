public class BigInteger {
  
  public void bigInteger(String input) {
    LList list = new LList();
    int length = input.length();
    for (int i=0; i<length; i++) {
      list.addFront(input.charAt(i));
    }
  }

}
