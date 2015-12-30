package controller;

/**
 * This exception is thrown when the user's input cannot be parsed.
 * 
 * @author Intro2CS - IDC 2012
 *
 */
public class UserInputParseException extends Exception {
	public UserInputParseException(String msg) {
		super(msg);
	}

	public UserInputParseException(String msg, Throwable t) {
		super(msg, t);
	}

}
