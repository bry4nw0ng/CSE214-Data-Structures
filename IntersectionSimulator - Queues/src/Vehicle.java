/**
 * The Vehicle Class represents a car that passes through the intersection. 
 * Each instance must contain the serialId (the first car to arrive at the 
 * intersection is serialId 1, the second car to arrive is serialId 2, the 
 * n'th car to arrive will have serialId >n) and the time it arrived 
 * (stored as an int). The car must be initialized with a serialId and the 
 * time it arrived. The serial counter is static and stores the number of 
 * vehicles that have arrived at the intersection so far. It is the only 
 * variable that is modifiable. The Vehicle class itself is actually 
 * immutable. This means once it has been constructed, no data within the 
 * instance can be changed.
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 * 		Recitation#04
 */
public class Vehicle {
	private static int serialCounter = 0; // Serial Counter 
	private int serialID; // Assigned Serial Number
	private int timeArrived; // Time that Vehicle is initialized
	
	/**
	 * Default constructor that returns an instance of a Vehicle
	 * 
	 * @param initTimeArrived
	 *    Time that the vehicle at the intersection 
	 * 
	 * @precondition
	 *    initTimeArrived > 0
	 * 
	 * @throws IllegalArgumentException
	 *    inittimeArrived <= 0
	 */
	public Vehicle(int initTimeArrived) throws IllegalArgumentException {
		if (initTimeArrived <= 0) {
			throw new IllegalArgumentException("initTimeArrived is less than "
					+ "or equal to 0");
		}
		else {
			timeArrived = initTimeArrived;
			serialCounter++;
			serialID = serialCounter;
		}
	}
	
	/**
	 * Returns the Serial ID of the Vehicle
	 * 
	 * @return
	 *    Returns the ID of the vehicle as a integer
	 */
	public int getSerialID() {
		return serialID;
	}
	
	/**
	 * Returns the time that the Vehicle was initialized 
	 * 
	 * @return
	 *    Returns the time that the Vehicle was initialized as an Integer
	 */
	public int getTimeArrived() {
		return timeArrived;
	}
}
