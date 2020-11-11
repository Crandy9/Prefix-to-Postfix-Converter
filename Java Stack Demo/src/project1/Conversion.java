/*
 * File: Conversion.java
 * Author: Linden Crandall
 * Purpose: This program demonstrates conversions of prefix expressions
 * 			to postfix expressions and vice-versa through a GUI 
 */
package project1;

import java.util.*;

public class Conversion {

	/**********
	 * prefix to postfix
	 * 
	 * @return
	 ***************/

	public static String preToPost(String expression) throws SyntaxErrorException {

		try {

			// prefix to postfix requires two stacks
			Stack<String> reversalStack = new Stack<String>();
			Stack<String> operandStack = new Stack<String>();
			// String that will hold the final converted expression
			String conversion = "";
			// String that will hold the tokens
			String store = "";
			// String that will hold each part of the converted expression
			String result = "";

			// this int is used to make sure that an operator was entered by the user
			int operatorChecker = 0;

			// expression pre processor
			if (expression.length() != 0) {

				// check expression
				for (int i = 0; i < expression.length(); i++) {
					// if token is operator
					// isOperator == false means token is operator
					if (isOperator(String.valueOf(expression.charAt(i)))) {
						operatorChecker++;
						store += " " + expression.charAt(i) + " ";
					} else {
						store += " " + expression.charAt(i) + " ";

					}
				}
			}

			// making sure an operator was present in the expression
			if (operatorChecker <= 0) {
				throw new SyntaxErrorException();
			}

			// tokenize results
			StringTokenizer st = new StringTokenizer(store);

			// while there are more tokens
			while (st.hasMoreTokens()) {
				reversalStack.push(st.nextToken());

			}

			store = "";

			// while the reversal stack is not empty
			while (reversalStack.isEmpty() == false) {
				// pop the next token from the reversal stack
				store = reversalStack.pop();

				// else if it is an operator pop two operands off of the operand stack

				if (isOperator(store) == true) {
					//keeping track of operators
					++operatorChecker;
					if (operandStack.isEmpty()) {
						throw new EmptyStackException();

					} else {
						String operand1 = operandStack.peek();
						operandStack.pop();
						String operand2 = operandStack.peek();
						operandStack.pop();
						// create a string with the two operands followed by the operator
						result = operand1 + operand2 + store;

						// push that string onto the operand stack
						operandStack.push(result);

						// if it is an operand push it onto the operand stack
					}
				} else {
					operandStack.push(store);
				}
			}
			// return postfix expression
			while (!operandStack.isEmpty()) {
				conversion += operandStack.pop();
			}
			return conversion;

		} catch (Exception e) {
			throw new SyntaxErrorException();
		}
	}

	/********
	 * check if popped token from reversal stack is an operator
	 *************/
	private static boolean isOperator(String poppedToken) {
		Boolean returnValue = false;

		switch (poppedToken) {
		case "+":
		case "-":
		case "*":
		case "/":
			returnValue = true;
		}

		return returnValue;
	}

	/********** postfix to prefix ***************/
	public static String postToPre(String expression) throws SyntaxErrorException {

		try {
			// prefix to postfix requires one stack
			Stack<String> operandStack1 = new Stack<String>();

			// stores the expression
			String store = "";
			String result = "";
			// int will be used to verify that operator has been entered by user
			int operatorChecker = 0;

			// expression pre processor
			if (expression.length() != 0) {
				for (int i = 0; i < expression.length(); i++) {
					// if token is operator
					// isOperator == false means token is operator
					if (isOperator(String.valueOf(expression.charAt(i)))) {
						operatorChecker++;
						store += " " + expression.charAt(i) + " ";
					} else {
						store += " " + expression.charAt(i) + " ";

					}
				}
			}

			// making sure an operator was present in the expression
			if (operatorChecker <= 0) {
				throw new SyntaxErrorException();
			}

			// tokenize results
			StringTokenizer st = new StringTokenizer(store);

			// while there are more tokens
			while (st.hasMoreTokens()) {
				String s = st.nextToken();

				// else if it is an operator pop two operands off of the operand stack
				if (isOperator(s) == true) {

					if (operandStack1.isEmpty()) {
						throw new EmptyStackException();

					} else {

						String operand1 = operandStack1.pop();

						String operand2 = operandStack1.pop();
						// create a string with the the operator followed by two operands
						result = s + operand2 + operand1;

						// push that string onto the operand stack
						operandStack1.push(result);
						// if it is an operand push it onto the operand stack
					}
				} else if (isOperator(s) == false) {
					operandStack1.push(s);
				}
			}

			// return postfix expression
			return operandStack1.toString().replace(",", "").replace("[", "").replace("]", "").trim();

		} catch (Exception e) {
			throw new SyntaxErrorException();
		}
	}
}
