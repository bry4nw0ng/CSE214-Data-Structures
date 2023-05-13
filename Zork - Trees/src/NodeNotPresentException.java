/**
 * The NodeNotPresentException extends the exception class and returns an 
 * erro when the exception is met
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 *		Recitation#04
 */
public class NodeNotPresentException extends Exception {
	
	/**
	 * Sends a NodeNotPresentException error with the respective error
	 * message
	 * 
	 * @param errorMessage
	 * 		the initialized message error message
	 */
	public NodeNotPresentException(String errorMessage) {
		super(errorMessage);
	}
}
