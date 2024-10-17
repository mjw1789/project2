public class BigInteger {
    LList bigInteger;
    private int size;
  
  public LList bigInteger(String input) {
    LList list = new LList();
    int length = input.length();
    size = length;
    for (int i=0; i<length; i++) {
      char c = input.charAt(i);
      int val = Character.getNumericValue(c);
      list.append(val);
    }
    this.setValue(list);
    return list;
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
