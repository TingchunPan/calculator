// Tingchun Pan
import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {

	    private float currentValue;
	    private float memoryValue;
	    private ArrayList<Float> historyValues = new ArrayList<>();

	    
	    public Calculator(){}


	   
	    public float evaluate(String expression){
	        
	        String[] expressions = expression.split(" ");

	        if (expressions.length < 3 && expression!="m" && expression!="h" && expression!="c"&& expression!="mr"){
	        	return Float.MIN_VALUE;
	            } else  
	            	if (expressions.length == 3) {

	        		if (checkOperand1(expressions[0]) && checkOperator(expressions[1]) && checkOperand2(expressions[2], expressions[1])) {
	        			float operand1 = Float.parseFloat(expressions[0]);
	        			String operator = expressions[1];
	        			float operand2 = Float.parseFloat(expressions[2]);

	        			currentValue = calculate(operand1, operator, operand2);
	        		} else {
	        			currentValue = Float.MIN_VALUE;
	        		}

	        	} else if (expressions.length == 2){

	        		if (checkOperator(expressions[0]) && checkOperand2(expressions[1], expressions[0])) {
	        			float operand1 = getMemoryValue();
	        			String operator = expressions[0];
	        			float operand2 = Float.parseFloat(expressions[1]);

	        			currentValue = calculate(operand1, operator, operand2);
	        		} else {
	        			currentValue = Float.MIN_VALUE;
	        		}

	        	} else if (expressions.length == 7 && expression.contains("(") && expression.contains(")")) {

	        		if (expressions[0].startsWith("(") && expressions[2].endsWith(")")
	        				&& expressions[4].startsWith("(") && expressions[6].endsWith(")")) {


	        			if (checkOperand1(expressions[0].substring(1))
	        					&& checkOperator(expressions[1])
	        					&& checkOperand2(expressions[2].substring(0, expressions[2].length() - 1), expressions[1])
	        					&& checkOperator(expressions[3])
	        					&& checkOperand1(expressions[4].substring(1))
	        					&& checkOperator(expressions[5])
	        					&& checkOperand2(expressions[6].substring(0, expressions[2].length() - 1), expressions[5])
	        					) {


	        				float oper1 = Float.parseFloat(expressions[0].substring(1));
	        				String op1 = expressions[1];
	        				float oper2 = Float.parseFloat(expressions[2].substring(0, expressions[2].length() - 1));
	        				float bracket1 = calculate(oper1, op1, oper2);


	        				float oper3 = Float.parseFloat(expressions[4].substring(1));
	        				String op3 = expressions[5];
	        				float oper4 = Float.parseFloat(expressions[6].substring(0, expressions[6].length() - 1));
	        				float bracket2 = calculate(oper3, op3, oper4);


	        				String op2 = expressions[3];
	        				if (checkOperand2(String.valueOf(bracket2), op2)) {
	        					currentValue = calculate(bracket1, op2, bracket2);
	        				} else {
	        					currentValue = Float.MIN_VALUE;
	        				}
	        			} else {
	        				currentValue = Float.MIN_VALUE;
	        			}
	        		} else {
	        			currentValue = Float.MIN_VALUE;
	        		}


	        	} else if (!expression.contains("(") && !expression.contains(")") && expressions.length % 2 == 1){

	        		String newExpression = expression;
	        		String[] newExpressions;
	        		while (newExpression.split(" ").length > 1){
	        			newExpressions= newExpression.split(" ");
	        			if (newExpression.contains("*") || newExpression.contains("/")){
	        				for(int idx = 1 ; idx < newExpressions.length -1 ; idx+=2){
	        					if (newExpressions[idx].equals("*") || newExpressions[idx].equals("/")){
	        						String nowExpression = String.join(" " , newExpressions[idx-1], newExpressions[idx], newExpressions[idx+1]);
	        						float nowValue = evaluate(nowExpression);
	        						newExpression = newExpression.replaceFirst(nowExpression.replaceAll(" \\* ", " \\\\\\* "), String.valueOf(nowValue));
	        						break;
	        					}
	        				}
	        			} else if (newExpression.contains("+") || newExpression.contains("-")){
	        				String nowExpression = String.join(" " , newExpressions[0], newExpressions[1], newExpressions[2]);
	        				float nowValue = evaluate(nowExpression);
	        				newExpression = newExpression.replaceFirst(nowExpression.replaceAll(" \\+ ", " \\\\\\+ "), String.valueOf(nowValue));

	        			} else {
	        				newExpression = String.valueOf(Float.MIN_VALUE);
	        			}
	        		}

	        		currentValue = Float.parseFloat(newExpression);

	        	} else {
	        		currentValue = Float.MIN_VALUE;
	        	}

	        historyValues.add(currentValue);
	        return getCurrentValue();
	    }

	    private float calculate(float operand1, String operator, float operand2) {
	        switch (operator) {
	            case "+":
	                return operand1 + operand2;
	            case "-":
	                return operand1 - operand2;
	            case "*":
	                return operand1 * operand2;
	            case "/":
	                return operand1 / operand2;
	            default:
	                
	                return Float.MIN_VALUE;
	        }
	    }

	    private boolean checkOperand1(String operand){
	        try {
	            Float.parseFloat(operand);
	        } catch (Exception e){
	            return false;
	        }
	        return true;
	    }

	    private boolean checkOperator(String operator){
	        return operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/");
	    }

	    private boolean checkOperand2 (String operand, String exOperator){
	        float value;
	        try {
	            value = Float.parseFloat(operand);
	        } catch (Exception e){
	            return false;
	        }

	        return !exOperator.equals("/") || value != 0;
	    }

	   
	    public float getCurrentValue(){
	        return currentValue;
	    }

	    public float getMemoryValue() {
	    
	        return memoryValue;
	    }

	    public void setMemoryValue(float memval){
	        memoryValue = memval;
	    }

	    public void clearMemory() {
	        memoryValue = 0;
	       
	    }

	    
	    public float getHistoryValue(int index){
	         return historyValues.get(index);
	    }

	    public String getHistoryValues(){
	        StringBuffer historyValuesStr = new StringBuffer();
	        for (float historyValue : historyValues){
	            historyValuesStr.append(historyValue).append(" ");
	        }
	        return String.valueOf(historyValuesStr);
	    }
	    
	    public static void main(String[] args) {
	       
	    	 Calculator calculator = new Calculator();
	    	  Scanner in = new Scanner(System.in);
	          System.out.println("Input express or 'q' to quit.");
	          String input = "";
	          boolean isFirst = true;
	          while (!input.equals("q")){
	              if (!isFirst){
	                  if (input.equals("m")){
	                     
	                      calculator.setMemoryValue(calculator.getCurrentValue());
	                  } else if (input.equals("mr")) {
	                      
	                  } else if (input.equals("c")) {
	                      
	                      calculator.clearMemory();
	                  } else if (input.equals("h")){
	                      
	                      System.out.println(calculator.getHistoryValues());
	                  } else {
	                      System.out.println(calculator.evaluate(input));
	                  }

	              } else {
	                  isFirst = false;
	              }
	              input = in.nextLine();
	          }
	      
	    }
}

	  
