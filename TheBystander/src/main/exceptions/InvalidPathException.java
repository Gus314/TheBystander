package main.exceptions;

public class InvalidPathException extends Exception
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPathException(String message)
    {
    	super(message);
    }
}
