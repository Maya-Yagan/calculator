/*
 * This class represnts a variable "x"
 * @Author: Maya Yagan
 */
public class Variable implements Calculations{
  /*
   * This method will return the variable in the String form as x
   * @return the variable as x
   */ 
  public String toString(){
    return "x";
  }
  
  /*
   * This method will assure that the two compared objects are of the type Variable
   * @param o: the object to compare to
   * @return true if the compared objects are Variable, otherwise false
   */ 
  public boolean equals(Object o){
    return (o instanceof Variable);
  }
}