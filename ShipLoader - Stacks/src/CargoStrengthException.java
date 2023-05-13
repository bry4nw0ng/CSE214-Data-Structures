/**
 * 
 * @author Bryan Wong
 *		SBUID#115226034
 *		Recitation#04
 */
public class CargoStrengthException extends Exception {
	
	/**
	 * Sends a CargoStrengthException error with the respective error message
	 * 
	 * @param errorMessage
	 *    The initialized message error message
	 */
	int index;
	public CargoStrengthException(String errorMessage, int stack) {
		super(errorMessage);
		index = stack;
	}


}
