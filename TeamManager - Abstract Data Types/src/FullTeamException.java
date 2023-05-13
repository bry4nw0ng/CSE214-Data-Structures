/**
 * The FullTeamException extends the Exception class and returns an error when
 * the the exception is met.
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 *		Recitation#04
 */
public class FullTeamException extends Exception {
	
	/**
	 * Sends a FullTeamException error with the respective error message
	 * 
	 * @param errorMessage
	 *    The initialized message error message
	 */
	public FullTeamException(String errorMessage) {
		super(errorMessage);
	}

}
