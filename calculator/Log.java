/*
 * This method represnts the natural logarithm
 * @Author: Maya Yagan
 * Side note for this class: I thought at first that it would be better for Log to extend Polynomial since both of them have a field called operand and its corresponding getter method. However, later when I was testing the code I got many errors in the results of calculations of the method value() then I realized that these two classes are not supposed to extend each other. As a result, I decided not to make them extend each other
 */ 
public class Log implements Calculations{
  /*
   * This field stores the operand
   */ 
  private Object operand;
  
  /*
   * Constructs a new Log object with the given operand
   * @param operand: the operand of the logarithm
   */ 
  public Log(Object operand){
    this.operand = operand;
  }
  
  /*
   * This method returns the operand
   * @return the operand
   */ 
  public Object getOperand(){
    return operand;
  }
  
  /*
   * This method returns the log as a String
   * @return the log in the form of Exp[x] as a String
   */ 
  public String toString(){
    return "Exp[" + getOperand() + "]";
  }
  
  /*
   * This method compares two logarithms
   * @param o: the object to compare to
   * @return true if the two objects have the same parameter, otherwise false
   */ 
  public boolean equals(Object o){
    if(o instanceof Log)
      return (((Log)o).getOperand().equals(this.getOperand()));
    return false;
  }
}