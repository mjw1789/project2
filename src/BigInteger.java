public class BigInteger {
    private int size;
  
  public void bigInteger(String input) {
    LList list = new LList();
    int length = input.length();
    size = length;
    for (int i=0; i<length; i++) {
      //list.addFront(input.charAt(i));
      list.insert(input.charAt(i));


    }
  }

  public int bigIntegerSize() {
    return size;
  }

}
