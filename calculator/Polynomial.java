/*
 * This class represnts a function raised to a power (x^4)
 * @Author: Maya Yagan
 */ 
public class Polynomial implements Calculations{
  /*
   * This field stores the operand (the base)
   */ 
  private Object operand;
  /*
   * This field stores the power of the function (the exponent) 
   */ 
  private double power;
  
  /*
   * Constructs a new polynomial with the given operand and power values 
   * @param operand: the function to be raised to the power
   * @param power: the power of the function
   */ 
  public Polynomial(Object operand, double power){
    this.operand = operand;
    this.power = power;
  }
  
  /*
   * This method returns the operand
   * @return the operand
   */ 
  public Object getOperand(){
    return operand;
  }
  
  /*
   * This method returns the power 
   * @return the power
   */ 
  public double getPower(){
    return power;
  }
  
  /*
   * This method returns the Polynomial as a String
   * @return the polynomial in the form of x^3
   */ 
  public String toString(){
    if(getOperand() instanceof BinaryOP)
      return "(" + ((BinaryOP)getOperand()).toString() + ")^" + getPower(); 
    return getOperand() + "^" + getPower();
  }
  
  /*
   * This method compares two Polynomials 
   * @param o: the object to be compared to
   * @return true if the compared objects have the same power and operands, otherwise false
   */ 
  public boolean equals(Object o){
    if(o instanceof Polynomial)
      return (((Polynomial)o).getPower() == this.getPower()) && (((Polynomial)o).getOperand().equals(this.getOperand()));
    return false; 
  }
}