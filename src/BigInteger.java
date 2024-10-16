public class BigInteger {
    LList bigInteger;
    private int size;
  
  public void bigInteger(String input) {
    LList list = new LList();
    int length = input.length();
    size = length;
    for (int i=0; i<length; i++) {
      //list.addFront(input.charAt(i));
      list.insert(input.charAt(i));
    }
    this.setValue(list);
  }

  public void setValue(LList bigInteger){
    this.bigInteger = bigInteger;
  }

  public LList getBigInteger() {
    return bigInteger;
  }

  public int bigIntegerSize() {
    return size;
  }

}
