package excepciones;

public class PasswordException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PasswordException() 
	{
		
	}
	
	@Override
	public String getMessage() {
		
		return "Las claves no coinciden";
	}
}
