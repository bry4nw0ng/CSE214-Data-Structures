/**
 * 
 * @author Bryan Wong
 *		SBUID#115226034
 *		Recitation#04
 */
public class FullStackException extends Exception {
	
	/**
	 * Sends a FullStackException error with the respective error message
	 * 
	 * @param errorMessage
	 *    The initialized message error message
	 */
	public FullStackException(String errorMessage) {
		super(errorMessage);
	}
	
}
