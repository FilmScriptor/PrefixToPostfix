/*
 * Author: Cuong Nguyen
 * Class: CMSC 350
 * Date: 20220120
 * Purpose: Prefix to Postfix class
 */



import java.io.StreamTokenizer;
import java.io.StringReader;
import java.io.IOException;
import java.util.*;

public class PreToPost {
	
	//Prefix to Postfix conversion
	public static String preConvert (String expression) throws IOException, SyntaxErrorException {
		if (!expression.equals("")) {
			//set the string as tokens
			List<String> expToArray = tokenExp(expression);
			//create new generic stack
			Stack<String> stack = new Stack<String>();			
			//reverse the list
			Collections.reverse(expToArray);
			
			//loop through each element to check for operands
			for (String token:expToArray) {
				if (!isOperator(token)) {
					stack.push(token + " ");
				}
				else {
					try {
						String operand1 = stack.pop();
						String operand2 = stack.pop();
						
						//return them back to temporary string
						String temp = operand1 + operand2 + token + " ";
						//push them back on to stack
						stack.push(temp);
					}
					catch (EmptyStackException ex) {
						throw new SyntaxErrorException("Stack is empty.\n"
								+ "Please check prefix expression.\n"
								+ "Example: * + A B - C D");
					}
				}
			}
			//return result from stack
			String result = stack.pop();
			//check if the stack is empty
			if (stack.empty()) {
				return result;
			}
			else {
				throw new SyntaxErrorException("Stack is not empty.\n"
						+ "Please check prefix expression.\n"
						+ "Example: * + A B - C D");
			}
		}
		//if no input then return message
		else { 
			throw new SyntaxErrorException("Please enter a prefix expression");
		}
	}
	

	
	//take string of an expression and turn it into tokens of int and string
	public static List<String> tokenExp(String expression) throws IOException {
		StreamTokenizer tokenExp = new StreamTokenizer(new StringReader(expression));
		
		//set operator '-' and '/' as normal char
		tokenExp.ordinaryChar('-');
		tokenExp.ordinaryChar('/');
		
		//create new array list to store tokens
		List<String> tokenList = new ArrayList<>();
		//stores each token until the end of stream
		while (tokenExp.nextToken() != StreamTokenizer.TT_EOF) {
			//check if token is number
			if (tokenExp.ttype == StreamTokenizer.TT_NUMBER) {
				tokenList.add(String.valueOf((int)tokenExp.nval));
			}
			//check if token is word
			else if (tokenExp.ttype == StreamTokenizer.TT_WORD) {
				tokenList.add(tokenExp.sval);
			}
			//check if token is one of the operators
			else {
				tokenList.add(String.valueOf((char)tokenExp.ttype));
			}
		}
		//return token in the list
		return tokenList;
	}
	
	
	
	//create a method to check each elements of an expression if they equal an operator
	public static boolean isOperator(String oper) {
		
		switch (oper.charAt(0)) {
		case '+':
		case '-':
		case '/':
		case '*':
		case '^':
			return true;
		}
		return false;
	}
}

