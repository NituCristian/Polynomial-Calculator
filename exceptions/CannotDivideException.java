package exceptions;

public class CannotDivideException extends Exception{
	
	public CannotDivideException()
	{
		
	}
	
	/**
	 * 
	 * @param msg mesaj ce va fi afisat impreuna cu exceptia
	 */
	public CannotDivideException(String msg)
	{
		super(msg);
	}

}
