/*
 * This class repersents a double number 
 * @Author: Maya Yagan
 */ 
public class Number implements Calculations{
  /*
   * This field stores the value of the number
   */ 
  private double number;
  
  /*
   * Constructs a new Number object with the given value
   * @param number: the given value of the number
   */ 
  public Number(double number){
    this.number = number;
  }
  
  /*
   * This method returns the value of the number
   * @return the value of the number
   */ 
  public double getNumber(){
    return number;
  }
  
  /*
   * This method returns the number as a String
   * @return the number in the String form
   */ 
  public String toString(){
    return getNumber() + "";
  }
  
  /*
   * This mehtod compares the values of two Numbers and returns true if they are equal, otherwise false
   * @param: the object to compare to
   * @return true if they are equal, otherwise false
   */ 
  public boolean equals(Object o){
    if(o instanceof Number)
      return ((Number)o).getNumber() == this.getNumber();
    return false;
  }
}