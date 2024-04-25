/*
 * This interface will handle the calculations of the methods value and derivative for: Variable, Number, BinaryOP, Polynomial and Log
 * @Author: Maya Yagan
 */ 
public interface Calculations{
  /*
   * This method will retrieve an Object which represents one of each of the five types (Variable, Number, BinaryOP, Polynomial and Log) so that this interface can handle each one of them correctly
   * @return the Object that our calculations will be performed on
   */ 
  public default Object getExpression(){
    return this;
  }
  
  /*
   * This method calculates the value of a function at a given point. If no input was given and the function has a variable then an Exception will be thrown
   * @param ... input: the given point to calculate the value at
   * @return the value of the function at the given point
   */
  public default double value(double... input){
    //This variable stores the function that we are currently working on
    Object expression = getExpression();
    if(input.length == 1){
      if(expression instanceof BinaryOP){
        //This variable stores the evaluated left side of the function
        double left = valueHelper(((BinaryOP)expression).getLeftOperand(), input[0]);
        //This variable stores the evaluated right side of the function
        double right = valueHelper(((BinaryOP)expression).getRightOperand(), input[0]);
        switch (((BinaryOP)expression).getOperator()){
          case PLUS:
            return left + right;
          case SUB:
            return left - right;
          case MULT:
            return left * right;
          case DIV:
            return left / right;
        }
      }
      if(expression instanceof Variable)
        return input[0];
      if(expression instanceof Number)
        return ((Number)expression).getNumber();
      if(expression instanceof Polynomial)
        return valueHelper((Polynomial)expression, input[0]);
      if(expression instanceof Log)
        return valueHelper((Log)expression, input[0]);
    }
    if(input.length == 0){
      //Side note: I chose this number (4.9E-320), which is very very unlikely to be expected as an input, to be able to handle the case of no input provided but expected (to throw an Exception). I know this is a very stupid implementation, but I couldn't come up with anything else. I'm sorry :(
      //This variable stores a value that will unlikely be expected as an input. This will be used to handle the case of expected input but none is provided
      double redFlag = 4.9E-320;
      if(expression instanceof BinaryOP){
        if(((BinaryOP)expression).getLeftOperand() instanceof Variable || ((BinaryOP)expression).getRightOperand() instanceof Variable)
          throw new UnsupportedOperationException("Input was expected but none was provided");
        //This variable stores the evaluated left side of the function
        double left = valueHelper(((BinaryOP)expression).getLeftOperand(), redFlag);
        //This variable stores the evaluated right side of the function
        double right = valueHelper(((BinaryOP)expression).getRightOperand(), redFlag);
        switch (((BinaryOP)expression).getOperator()){
          case PLUS:
            return left + right;
          case SUB:
            return left - right;
          case MULT:
            return left * right;
          case DIV:
            return left / right;
        }
      } 
      if(expression instanceof Variable)
        throw new UnsupportedOperationException("Input was expected but none was provided");
      if(expression instanceof Number)
        return ((Number)expression).getNumber();
      if(expression instanceof Polynomial)
        return valueHelper((Polynomial)expression, redFlag); 
      if(expression instanceof Log)
        return valueHelper((Log)expression, redFlag);
    }
    throw new IllegalArgumentException("Cannot have more than one input");
  }
  
  /*
   * This method helps the method value to evaluate the operands and return the appropriate result for each one of the five types: Variable, Number, BinaryOP, Polynomial and Log
   * @param operand: the operand to be evaluated
   * @param input: the value that we want to calculate the method value() at
   * @return the result after evaluation for the method value
   */
  public default double valueHelper(Object operand, double input){
    //Please read the side note above
    //This variable stores a value that will unlikely be expected as an input. This will be used to handle the case of expected input but none is provided
      double redFlag = 4.9E-320;
    if(operand instanceof BinaryOP)
      return ((BinaryOP)operand).value(input);
    if(operand instanceof Variable)
      return input;
    if(operand instanceof Number)
      return ((Number)operand).getNumber();
    if(operand instanceof Polynomial){
      if(((Polynomial)operand).getOperand() instanceof Variable && input == redFlag)
        throw new UnsupportedOperationException("Input was expected but none was provided");
      return Math.pow(valueHelper(((Polynomial)operand).getOperand(), input), ((Polynomial)operand).getPower());
    }
    if(operand instanceof Log){
      if(((Log)operand).getOperand() instanceof Variable && input == redFlag)
        throw new UnsupportedOperationException("Input was expected but none was provided");
      return Math.log(valueHelper(((Log)operand).getOperand(), input));
    }
    return 0;
  }
  
  /*
   * This method calculates the derivative of any of the five types: Variable, Number, BinaryOP, Polynomial and Log
   * @return a function which is the calculated derivative
   */ 
  public default Object derivative(){
    //This variable stores the function that we are currently working on
    Object expression = getExpression();
    if(expression instanceof BinaryOP){
      //This variable stores the evaluated left side of the function
      Object left = derivativeHelper(((BinaryOP)expression).getLeftOperand());
      //This variable stores the evaluated right side of the function
      Object right = derivativeHelper(((BinaryOP)expression).getRightOperand());
      switch(((BinaryOP)expression).getOperator()){
        case PLUS:
          return new BinaryOP(BinaryOP.Op.PLUS, left, right);
        case SUB:
          return new BinaryOP(BinaryOP.Op.SUB, left, right);
        case MULT:
          return new BinaryOP(BinaryOP.Op.PLUS, new BinaryOP(BinaryOP.Op.MULT, left, ((BinaryOP)expression).getRightOperand()), new BinaryOP(BinaryOP.Op.MULT, ((BinaryOP)expression).getLeftOperand(), right));
        case DIV:
          //This variable stores the numerator of the derivative of the function
          BinaryOP numerator = new BinaryOP(BinaryOP.Op.SUB, new BinaryOP(BinaryOP.Op.MULT, left, ((BinaryOP)expression).getRightOperand()), new BinaryOP(BinaryOP.Op.MULT, ((BinaryOP)expression).getLeftOperand(), right));
          //This variable stores the denominator of the derivative of the function
          Polynomial denominator = new Polynomial(((BinaryOP)expression).getRightOperand(), 2);
          return new BinaryOP(BinaryOP.Op.DIV, numerator, denominator);
      }
    }
    if(expression instanceof Variable)
      return new Number(1);
    if(expression instanceof Number)
      return new Number(0);
    if(expression instanceof Polynomial){
      if(((Polynomial)expression).getOperand() instanceof Variable){
        //This temporary varible stores the newly calculated derivative of Polynomial
         Polynomial temp = new Polynomial(new Variable(), ((Polynomial)expression).getPower() - 1);
         return new BinaryOP(BinaryOP.Op.MULT, ((Polynomial)expression).getPower(), temp);
      }
      if(((Polynomial)expression).getOperand() instanceof Number)
        return new Number(0);
      //This variable stores the derivative of the operand
      Object differentiatedOperand = derivativeHelper(((Polynomial)expression).getOperand());
      //This variable stores teh polynomial after decreasing its power
      Polynomial newPolynomial = new Polynomial(((Polynomial)expression).getOperand(), ((Polynomial)expression).getPower() - 1);
      return new BinaryOP(BinaryOP.Op.MULT, ((Polynomial)expression).getPower(), new BinaryOP(BinaryOP.Op.MULT, newPolynomial, differentiatedOperand));
    }
    if(expression instanceof Log){
      //This variable stores the derivative of the operand
      Object differentiatedOperand = derivativeHelper(((Log)expression).getOperand());
      //This variabel stores teh numerator of the function (for the derivative)
      Object numerator = differentiatedOperand;
      //This variabel stores teh denominator of the function (for the derivative)
      Object denominator = ((Log)expression).getOperand();
      return new BinaryOP(BinaryOP.Op.DIV, numerator, denominator);
    }
    return null;
  }
  
  /*
   * This method helps the method derivative to calculate the derviative of a guiven function
   * @param function: the function that we are currently working on
   * @return the derivative of the functtion
   */ 
  public default Object derivativeHelper(Object function){
      return ((Calculations)function).derivative();
  }
}