/**
 * 
 * @author Bryan Wong
 *		SBUID#115226034
 *		Recitation#04
 */
public class ShipOverweightException extends Exception {
	
	/**
	 * Sends a ShipOverweightException error with the respective error message
	 * 
	 * @param errorMessage
	 *    The initialized message error message
	 */
	public ShipOverweightException(String errorMessage) {
		super(errorMessage);
	}
}
