/*
 * This class represnts a function that has two operands and an operator (+, -, *, /)
 * @Author: Maya Yagan
 */ 
public class BinaryOP implements Calculations{
  /*
   * This field stores the operator
   */ 
  private Op operator;
  /*
   * This field stores the left operand
   */ 
  private Object leftOperand;
  /*
   * This field stores the right operand
   */ 
  private Object rightOperand;
  
  /*
   * Constructs a new BinaryOP object with the given operator and operands
   * @param operator: the given operator of the function
   * @param leftOperand: the given left operand of the function
   * @param rightOperand: the given right operand of the function
   */ 
  public BinaryOP(Op operator, Object leftOperand, Object rightOperand){
    this.operator = operator;
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
  }
  
  /*
   * This method returns the operator of the function
   * @return the operator
   */ 
  public Op getOperator(){
    return operator;
  }
  
  /*
   * This method returns the left operand of the function
   * @return the left operand
   */ 
  public Object getLeftOperand(){
    return leftOperand;
  }
  
  /*
   * This method returns the right operand of the function
   * @return the right operand
   */ 
  public Object getRightOperand(){
    return rightOperand;
  }
  
  /*
   * This method returns the function as a String
   * @return the function in the String form
   */ 
  public String toString(){
    if(getLeftOperand() instanceof BinaryOP && getRightOperand() instanceof BinaryOP)
      return "(" + getLeftOperand() + ")"+ " " + getOperator() + " " + "(" + getRightOperand() + ")";
    if(getLeftOperand() instanceof BinaryOP)
      return "(" + getLeftOperand() + ")"+ " " + getOperator() + " " + getRightOperand();
    if(getRightOperand() instanceof BinaryOP)
      return getLeftOperand() + " " + getOperator() + " " + "(" + getRightOperand() + ")";
    return getLeftOperand() + " " + getOperator() + " " + getRightOperand();
  }
  
  /*
   * This method compares two functions 
   * @param o: the object to be compared to
   * @return true if the compared objects have the same operators and operands, otherwise false
   */ 
  public boolean equals(Object o){
    if(o instanceof BinaryOP)
      return (((BinaryOP)o).getOperator() == this.getOperator()) && (((BinaryOP)o).getLeftOperand().equals(this.getLeftOperand())) && (((BinaryOP)o).getRightOperand().equals(this.getRightOperand()));
    return false;
  }
  
  /*
   * This enum represents the four main operations (+, -, *, /)
   */
  enum Op{
    /*
     * PLUS represents the plus sign
     * SUB represents the minus sign
     * MULT represents the multiplication sign
     * DIV represents the division sign
     */ 
    PLUS('+'), SUB('-'), MULT('*'), DIV('/');
    
    /*
     * This field stores the symbol for each operator
     */ 
    private char symbol;
    
    /*
     * Constructs an Op with the given symbol
     * @param symbol: the assigned symbol for each operator
     */ 
    private Op(char symbol){
      this.symbol = symbol;
    }
    
    /*
     * This method returns the symbol of an operator
     * @return the symbol of an operator
     */ 
    public char getSymbol(){
      return symbol;
    }
    
    /*
     * This method returns the symbol as a String
     * @return the symbol as a String
     */ 
    public String toString(){
      return getSymbol() + "";
    }
  }
}