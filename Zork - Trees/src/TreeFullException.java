/**
 * The TreeFullException extends the Exception class and returns an error when
 * the exception is met.
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 * 		Recitation#04
 */
public class TreeFullException extends Exception {
	
	/**
	 * Sends a TreeFullException error with the respective error message
	 * 
	 * @param errorMessage
	 *    The initialized message error message
	 */
	public TreeFullException(String errorMessage) {
		super(errorMessage);
	}
}
