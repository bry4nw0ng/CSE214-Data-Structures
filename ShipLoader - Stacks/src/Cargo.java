/**
 * The Cargo class details the contents of each cargo container which include
 * its name, weight, and strength.
 * 
 * @author Bryan Wong
 *		SBUID#115226034
 *		Recitation#04
 */
public class Cargo {
	private String name; // Name of the Cargo 
	private double weight; // Weight of the Cargo 
	private CargoStrength strength; // enum strength value of the Cargo
	
	/**
	 * Default constructor that returns an instance of the cargo container
	 * 
	 * @param initName
	 *    The name of the cargo item
	 *    
	 * @param initWeight
	 *    The weight of the cargo 
	 *    
	 * @param initStrength
	 *    The strength of the cargo which can either be FRAGILE, MODERATE
	 *    or STURDY
	 *    
	 * @precondition
	 *    initName is not null and initWeight is a value greater than 0
	 *    
	 * @postcondition
	 *    This object has been initialized to a Cargo object with the given 
	 *    name, weight, and strength.
	 *    
	 * @throws IllegalArgumentException
	 *    Indicates that initName is null or initWeight is less than or equal
	 *    to 0
	 */
	public Cargo(String initName, double initWeight, 
			CargoStrength initStrength) throws IllegalArgumentException {
		if (initName == null) {
			throw new IllegalArgumentException("The initial name is null");
		}
		if (initWeight <= 0) {
			throw new IllegalArgumentException("The initial weight is less "
					+ "than or equal to 0");
		}
		else {
			name = initName;
			weight = initWeight;
			strength = initStrength;
		}
	}
	
	/**
	 * Return the name of the cargo item
	 * 
	 * @return
	 *    Returns the name of the cargo as a String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the weight of the cargo
	 * 
	 * @return
	 *    Returns the weight of the cargo as a double
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * Returns the strength of the cargo
	 * 
	 * @return
	 *    Returns the strength of the cargo as a CargoStrength enum
	 */
	public CargoStrength getStrength() {
		return strength;
	}
}
