/**
 * The EndOfListException extends the Exception class and returns an error when
 * the the exception is met.
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 *		Recitation#04
 */
public class EndOfListException extends Exception {

	/**
	 * Sends a EndOfListException error with the respective error message
	 * 
	 * @param errorMessage
	 *    The initialized message error message
	 */
	public EndOfListException(String errorMessage) {
		super(errorMessage);
	}

}