package Logica;

/**
 * Modela la excepción ante una operación inválida.
 * @author Trinidad Crozes.
 *
 */
@SuppressWarnings("serial")
public class InvalidOperationException extends Exception {
	
	protected String message;
	
	/**
	 * Inicializa la excepción indicando el origen del error.
	 * @param error Especifica información adicional acerca de la excepción.
	 */
	public InvalidOperationException(String error) {
		message = error;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
