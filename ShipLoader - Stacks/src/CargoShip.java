/**
 * The CargoShip class represents a container ship that holds stacks of 
 * containers. The ship stores multiple instances of stacks of Cargo within 
 * an ArrayList. The CargoShip also includes methods that allow it to push
 * and pop Cargo within each stack along with a findAndPrint method that 
 * returns a list where that cargo item is found on the ship.
 * 
 * @author Bryan Wong
 *    SBUID#115226034
 *    Recitation#04
 */

import java.util.Stack;
import java.util.ArrayList;

public class CargoShip {
	private ArrayList<Stack<Cargo>> stacks; // ArrayList of containers
	private int maxHeight; // Maximum amount of Cargo in each stack
	private double maxWeight; // Maximum weight the ship can hold
	
	/**
	 * Returns an instance of the CargoShip with nothing on it
	 * 
	 * @param numStacks
	 *    The number of stacks the ship can hold
	 *    
	 * @param initMaxHeight
	 *    THe maximum height of any stack on this ship
	 *    
	 * @param initMaxWeight
	 *    The maximum weight for all cargo on the ship
	 *    
	 * @precondition
	 *    numStacks, initMaxHeight, and initMaxWeight are all values larger
	 *    than 0
	 *    
	 * @postcondition
	 *    This object has been initialized to a CargoShip with the indicated
	 *    number of stacks, maxHeight, and maxWeight.
	 *    
	 * @throws IllegalArgumentException
	 *    Either of the parameters are not within the appropriate bounds
	 */
	public CargoShip(int numStacks, int initMaxHeight, double initMaxWeight) {
		if (numStacks <= 0 || initMaxHeight <= 0 || initMaxWeight <= 0) {
			throw new IllegalArgumentException("Parameters are not within "
					+ "the appropriate bounds");
		} else {
			stacks = new ArrayList<Stack<Cargo>>(numStacks);
			maxHeight = initMaxHeight;
			maxWeight = initMaxWeight;
		    for (int i = 0; i < numStacks; i++) {
		    	this.stacks.add(new Stack<Cargo>());
		    }
		}
	}
	/**
	 * Pushes a cargo container to the indicated stack on the cargo ship
	 * 
	 * @param cargo
	 *    The container to place on the stack
	 *    
	 * @param stack
	 *    The index of the stack on the ship to push cargo onto
	 * 
	 * @precondition
	 *    The CargoShip and cargo is initialized and not null. 
	 *    The value of stack is at least 1 and at most stacks.length
	 *    The size of the stack at the desired index is less than maxHeight
	 *    The total weight of all Cargo on the ship and cargo.getWeight is 
	 *    less than maxWeight
	 * 
	 * @postcondition
	 *    The cargo has been successfully pushed to the given stack, or the 
	 *    appropriate exception has been thrown, in which case the state of 
	 *    the cargo ship should remain unchanged.
	 *    
	 * @throws IllegalArgumentException
	 *    If cargo is null or stack is not in the appropriate bounds
	 *    
	 * @throws FullStackException
	 *    If the stack being pushed is at the max height
	 *    
	 * @throws ShipOverweightException
	 *    If cargo would make the ship exceed maxWeight and sink
	 *    
	 * @throws CargoStrengthException
	 *    If the cargo would be stacked on top of a weaker cargo
	 */
	public void pushCargo(Cargo cargo, int stack)
			throws FullStackException, ShipOverweightException,
			CargoStrengthException {
		if (cargo == null || stack < 1 || stack > stacks.size()) {
			throw new IllegalArgumentException("Cargo is null or stack is "
					+ "not in the appropriate bounds.");
		}
		if (stackHeight(stack) == maxHeight) {
			throw new FullStackException("Operation failed! Cargo stack is "
					+ "at maximum height");
		}
		if (stackWeight(stack) + cargo.getWeight() > maxWeight) {
			throw new ShipOverweightException("Operation failed! Cargo would "
					+ "put ship overweight.");
		}
		if (strengthCheck(cargo, stack) == false) {
			stacks.get(stack-1).push(cargo);
			throw new CargoStrengthException("Operation failed! Cargo at top "
					+ "of stack cannot support weight.", stack);
		}
		else {
			stacks.get(stack-1).push(cargo);
		}
	}

	/**
	 * Pops a cargo from one of the specified stack
	 * 
	 * @param stack
	 *    The index of the stack to remove the cargo from
	 * 
	 * @precondition
	 *    The CargoShip is initialized and not null
	 *    The value of stack is at least 1 and at most stacks.length
	 *    
	 * @postcondition
	 *    The cargo has been successfully been popped from the stack, and 
	 *    returned, or the appropriate exception has been thrown, in which 
	 *    case the state of the cargo ship should remain unchanged.
	 *    
	 * @return
	 *    The cargo that is being popped
	 * 
	 * @throws IllegalArgumentException
	 *    If stack is not in the appropriate bounds
	 *    
	 * @throws EmptyStackException
	 *    If the stack being popped is empty
	 */
	public Cargo popCargo(int stack) throws EmptyStackException {
		if (stack < 1 || stack > stacks.size()) {
			throw new IllegalArgumentException("Stack is not in the "
					+ "appropriate bounds.");
		}
		if (stacks.get(stack-1).empty()) {
			throw new EmptyStackException("Stack being popped is empty");
		}
		else {
			return stacks.get(stack-1).pop();
		}
	}
	
	/**
	 * Finds and prints a formatted table of all the cargo on the ship with 
	 * a given name. If the item could not be found in the stacks, notify the 
	 * user accordingly.
	 * 
	 * @param name
	 *    The name of the cargo that is being found and printed
	 *    
	 * @precondition
	 *    The CargoShip is initialized and not null
	 *    
	 * @postcondition
	 *    The details of the cargo with the given name have been found and 
	 *    printed, or the user has been notified that no such cargo has been
	 *    found.
	 *    The state of the cargo ship should remain unchanged
	 */
	public void findAndPrint(String name) {
		boolean check = false;
		String output = "";
		output += "Cargo " + name + " found at the following locations: ";
		output += "\n";
		output += "\n";
		output += String.format("%-8s%-8s%-9s%-10s", " Stack", " Depth", 
				" Weight", " Strength");
		output += "\n";
		output += "=======+=======+========+==========";
		output += "\n";

		int count = 0;
		double totalWeight= 0;
		for (int i = 0; i < stacks.size(); i++) {
			if (stacks.get(i).empty() == true) {
				continue;
			}
			else {
				int depth = -1;
				Stack<Cargo> temp = new Stack<Cargo>();
				while (stacks.get(i).empty() == false) {
					temp.push(stacks.get(i).pop());
					depth++;
				}
				while (temp.empty() == false) {
					if (temp.peek().getName().equals(name)) {
						check = true;
						int stackNum = i +1;
						output+= String.format("%-7s%-8s%-9s%-11s", "   " 
						+ stackNum, "|   " + depth, "|  " 
						+ Math.round(temp.peek().getWeight()) , "|  " 
						+ toStringStrength(temp.peek().getStrength()));
						output+= "\n";
						count++;
						totalWeight += temp.peek().getWeight();
					}
					stacks.get(i).push(temp.pop());
					depth--;
				}		
			}	
		}
		if (check) {
			output+= "\n";
			output+= "Total Count: " + count;
			output+= "\n";
			output+=  "Total Weight: " + totalWeight;
			System.out.println(output);
		}
		else {
			System.out.println("Cargo '" + name + "' could not be found "
					+ "on the ship.");

		}
		
	}

	/**
	 * Returns the height of a stack 
	 * 
	 * @param stack
	 *    The index of the stacks ArrayList to access
	 * 
	 * @return
	 *    The total height of the indicated stack as an Integer
	 */
	public int stackHeight(int stack) {
		int heightCount = 0;
		Stack<Cargo> temp = new Stack<Cargo>();
		for (int i = 0; i < maxHeight; i++) {
			if (stacks.get(stack - 1).empty()) {
				break;
			}
			temp.push(stacks.get(stack - 1).pop());
			heightCount++;
		}
		while (temp.empty() == false) {
			stacks.get(stack-1).push(temp.pop());
		}
		return heightCount;
	}
	
	/**
	 * Returns the weight of a stack
	 * 
	 * @param stack
	 *    The index of the stacks ArrayList to access
	 *    
	 * @return
	 *    The total weight of the indicated stack as a Double
	 */
	public double stackWeight(int stack) {
		double totalWeight = 0;
		Stack<Cargo> temp = new Stack<Cargo>();
		while (stacks.get(stack-1).empty() == false) {
			totalWeight += stacks.get(stack-1).peek().getWeight();
			temp.push(stacks.get(stack-1).pop());
		}
		while (temp.isEmpty() == false) {
			stacks.get(stack-1).push(temp.pop());
		}
		return totalWeight;
	}
	
	/**
	 * Returns the total weight of the entire ship
	 * 
	 * @return
	 *    The total weight of the ship as a double
	 */
	public double shipWeight() {
		double totalWeight = 0;
		for (int i = 1; i <= stacks.size(); i++) {
			totalWeight += stackWeight(i);
		}
		return totalWeight;
	}
	
	/**
	 * Checks whether or not the strength of the stack can handle the 
	 * strength of a certain cargo
	 * 
	 * @param cargo
	 *    The cargo being compared to the stack
	 *    
	 * @param stack
	 *    The stack being compared to the cargo
	 *    
	 * @return
	 *    True or false depending on whether or not the stack can handle
	 *    the cargo.
	 */
	public boolean strengthCheck(Cargo cargo, int stack) {
		if (stacks.get(stack-1).isEmpty()) {
			return true;
		}
		switch (stacks.get(stack-1).peek().getStrength()) {
			case STURDY:
				return true;
			case MODERATE:
				if (cargo.getStrength() != CargoStrength.STURDY) {
					return true;
				}
			case FRAGILE:
				if (cargo.getStrength() == CargoStrength.FRAGILE) {
					return true;
				}
			default: 
				return false;
		}
	}
	
	/**
	 * Returns the appropriate String version of each enum
	 * 
	 * @param input
	 *    The CargoStrength enum that needs to be turned into a String
	 *    
	 * @return
	 *    Returns the respective CargoStrength enum as a String
	 */
	public String toStringStrength(CargoStrength input) {
		String strength = "";
		switch (input) {
			case STURDY:
				strength += "STURDY";
				break;
			case MODERATE:
				strength += "MODERATE";
				break;
			case FRAGILE:
				strength += "FRAGILE";
				break;
		}
		return strength;
	}
	
	/**
	 * Returns the String of each stack with a letter respective to its 
	 * CargoStrength
	 * 
	 * @return
	 *    A line of letters respective to each cargo's cargoStrength as a
	 *    String
	 */
	public String shipLine() {
		String output = "";
		for (int i = 1; i <= stacks.size(); i ++) {
			int count = 0; 
			output += "Stack " + i + ": ";
			Stack<Cargo> temp = new Stack<Cargo>();
			while (stacks.get(i-1).empty() == false) {
				temp.push(stacks.get(i-1).pop());
			}
			while(temp.empty() == false) {
				if (count > 0) {
					output += ", ";
				}
				switch (temp.peek().getStrength()) {
					case FRAGILE:
						output += "F";
						count++;
						break;
					case MODERATE: 
						output += "M";
						count++;
						break;
					case STURDY:
						output += "S";
						count++;
						break;
				}
				stacks.get(i-1).push(temp.pop());
			}
			output += "\n";
		}
		return output;
	}
	
	/**
	 * Returns the Cargo on top of the indicated Stack
	 * 
	 * @param index
	 *    The index of the stack 
	 *    
	 * @return
	 *    The Cargo on top of the indicated Stack
	 */
	public Cargo peekStack(int index) {
		return stacks.get(index-1).peek();
	}
	
	/**
	 * Returns the Stack of Cargo within indicated index in the stacks 
	 * ArrayList
	 * 
	 * @param index
	 *    The index of the ArrayList
	 * 
	 * @return
	 *    The indicated Stack of Cargo within the ArrayList
	 */
	public Stack<Cargo> getStack(int index) {
		return stacks.get(index-1);
	}
}
