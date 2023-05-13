/**
 * The Player class details the contents of a player which include their name,
 * their number of hits, and their number of errors.
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 * 		Recitation#04
 *
 */
public class Player {
	String name; // Name of the player
	int numHits; // Number of Hits
	int numErrors; // Number of Errors

	/**
	 * Returns an instance of Player
	 */
	public Player() {
	}
	
	/**
	 * Returns the name of the Player
	 * 
	 * @return
	 *    Returns the name of the Player as a String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the Player's number of hits
	 * 
	 * @return
	 *    Returns the number of hits as an Integer
	 */
	public int getNumHits() {
		return numHits;
	}

	/**
	 * Returns the Player's number of errors
	 * 
	 * @return
	 *    Returns the number of errors as an Integer
	 */
	public int getNumErrors() {
		return numErrors;
	}
	
	/**
	 * Sets a new name for the Player
	 * 
	 * @param name
	 *    The new name 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets a new number of hits for the player
	 * 
	 * @param numHits
	 *    The new number of hits 
	 *    
	 * @precondition 
	 *    numHits must be equal to a value greater than or equal to 0
	 *    
	 * @postcondition
	 *    The number of hits has been updated to the value of numHits 
	 * 
	 * @throws IllegalArgumentException
	 *    Indicates that numHits has a value less than 0
	 */
	public void setNumHits(int numHits) throws IllegalArgumentException {
		if (numHits < 0) {
			throw new IllegalArgumentException("Hits must be at least 0");
		} 
		else {
			this.numHits = numHits;
		}
	}
	
	/**
	 * Sets a new number of errors for the player
	 * 
	 * @param numErrors
	 *    The new number of errors
	 * 
	 * @precondition
	 *    numErrors must be equal to a value greater than or equal to 0
	 *    
	 * @postcondition
	 *    The number of errors has been updated to the value of numErrors
	 * 
	 * @throws IllegalArgument Exception
	 *    Indicates that numErrors has a value less than 0
	 */
	public void setNumErrors(int numErrors) {
		if (numErrors < 0) {
			throw new IllegalArgumentException("Errors must be at least 0");
		}
		this.numErrors = numErrors;
	}
	
	/**
	 * Returns a string containing the details of a player
	 * 
	 * @return
	 *    Returns the name, numHIts, and numErrors of a player in a String
	 */
	public String toString() {
		return name + " - " + numHits + " hits, " + numErrors + " errors";
	}

	/**
	 * Indicates whether the details of a player are equal to another player
	 * 
	 * @param player
	 *    Another Player that is to be compared with the current Player
	 *    
	 * @return
	 *    True if the name, numHits, and numErrors are equal, false if the 
	 *    other player is null or has a different name, numHits, or numErrors
	 */
	public boolean equals(Player player) {
		if (player == null) {
			return false;
		}
		return (player.getName().equals(name) && player.getNumErrors() == numErrors && player.getNumHits() == numHits);
	}
	
	/**
	 * Clones the current player with the same name, numHits, and numErrors
	 * 
	 * @return
	 *    Returns the cloned player as an Object
	 */
	public Object clone() {
		Player clone = new Player();
		clone.setName(name);
		clone.setNumHits(numHits);
		clone.setNumErrors(numErrors);
		return (Object) clone;
	}
}
