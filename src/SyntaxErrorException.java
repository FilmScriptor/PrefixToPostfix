/*
 * Author: Cuong Nguyen
 * Class: CMSC350
 * Date: 20220120
 * Purpose: customize Exception handler class
 */


public class SyntaxErrorException extends Exception{
	public SyntaxErrorException() {
		super();
	}
	public SyntaxErrorException(String message) {
		super(message);
	}

}
