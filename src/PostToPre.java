/*
 * Author: Cuong Nguyen
 * Class: CMSC350
 * Date: 20220120
 * Purpose: Postfix to Prefix class
 */


import java.io.StreamTokenizer;
import java.io.StringReader;
import java.io.IOException;
import java.util.*;


public class PostToPre {
	
	//Postfix to Prefix conversion
	public static String postConvert (String expression) throws IOException, SyntaxErrorException {
		if (!expression.equals("")) {
			//set the string as tokens
			List<String> expToArray = tokenExp(expression);
			//create new generic stack
			Stack<String> stack = new Stack<String>();
			
			//loop through each element in array to check for operands
			for (String token:expToArray) {
				if (!isOperator(token)) {
					stack.push(token + " ");
				}
				else {
					try {
						//in following order, operand 2 is first from stack
						String operand2 = stack.pop();
						String operand1 = stack.pop();
						
						//return them back to temporary string
						String temp = token +" "+ operand1 + operand2;
						//push them back on to stack
						stack.push(temp);
					}
					catch (EmptyStackException e) {
						throw new SyntaxErrorException("Stack is empty.\n"
								+ "Please check postfix expression.\n"
								+ "Example: A B + C D - *");
					}
				}
			}
			//return result from stack
			String result = stack.pop();
			
			//check if the stack is empty
			if(stack.empty()) {
				return result;
			}
			else {
				throw new SyntaxErrorException("Stack is not empty.\n"
						+ "Please check postfix expression.\n"
						+ "Example: A B + C D - *");
			}
		}
		//if no input then return message
		else {
			throw new SyntaxErrorException("Please enter a postfix expression");
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
