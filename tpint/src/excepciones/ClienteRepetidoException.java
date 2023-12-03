package excepciones;

public class ClienteRepetidoException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteRepetidoException() 
	{
		
	}
	
	@Override
	public String getMessage() {
		
		return "El cliente ya se encuentra registrado!";
	}
}
