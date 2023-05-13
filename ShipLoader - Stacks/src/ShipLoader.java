/**
 * The ShipLoader class contains an instance of the CargoShip class where a 
 * menu can be accessed to interact with the Ship where Cargo can be created,
 * added, and moved. The ShipLoader also contains another stack of Cargo 
 * named dock which loads and unloads Cargo onto the stacks on the ship.
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 * 		Recitation#04
 */

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class ShipLoader {
	/**
	 * The main method that creates a Dock and a CargoShip with parameters 
	 * input by the user. Then, it displays a menu that is used to interact
	 * with the two Objects.
	 * 
	 * @param args
	 *    The input that the user enters according to each menu option
	 *    
	 * @throws EmptyStackException
	 *    No objects with the stack can be accessed since it is empty
	 */
	public static void main(String[] args) throws EmptyStackException {
		Scanner stdin = new Scanner(System.in);
		boolean active = true;
		boolean param = true;
		System.out.println("Welcome to ShipLoader!");
		System.out.println();
		while (param) {
			try {
				System.out.println("Cargo Ship Parameters");
				System.out.println("--------------------------------------"
						+ "------------");
				System.out.print("Number of Stacks: ");
				int numStacks = stdin.nextInt();
				System.out.print("Maximum height of stacks: ");
				int maxHeight = stdin.nextInt();
				stdin.nextLine();
				System.out.print("Maximum total cargo weight: ");
				double maxWeight = stdin.nextDouble();
				stdin.nextLine();
				
				CargoShip ship = new CargoShip(numStacks, maxHeight, 
						maxWeight);
				Stack<Cargo> dock = new Stack<Cargo>();
				
				System.out.println();
				System.out.println("Cargo ship created.");
				System.out.println("Pulling ship into dock...");
				System.out.println("Cargo ship ready to be loaded.");
				
				while (active) {
					try {
						menu();
						String option = stdin.nextLine();
						
						if (option.toLowerCase().equals("c")) {
							boolean cargoMade = false;
							Cargo cargo;
							System.out.print("Enter the name of the cargo: ");
							String name = stdin.nextLine();
							System.out.print("Enter the weight of the cargo: ");
							double weight = stdin.nextDouble();
							stdin.nextLine();
							System.out.print("Enter the container strength "
									+ "(F/M/S): ");
							switch (stdin.nextLine().toUpperCase()) {
								case "F":
									cargo = new Cargo(name, weight, 
											CargoStrength.FRAGILE);
									if (strengthCheck(cargo, dock) == false) {
										System.out.println();
										System.out.println("Operation failed! "
												+ "Cargo at top of stack "
												+ "cannot support weight.");
										break;
									}
									else {
										dock.push(cargo);
										cargoMade = true;
										break;
									}
								case "M":
									cargo = new Cargo(name, weight, 
											CargoStrength.MODERATE);
									if (strengthCheck(cargo, dock) == false) {
										System.out.println();
										System.out.println("Operation failed! "
												+ "Cargo at top of stack cannot"
												+ " support weight.");
										break;
									}
									else {
										dock.push(cargo);
										cargoMade = true;
										break;
									}
								case "S":
									cargo = new Cargo(name, weight, 
											CargoStrength.STURDY);
									if (strengthCheck(cargo, dock) == false) {
										System.out.println();
										System.out.println("Operation failed!"
												+ " Cargo at top of stack "
												+ "cannot support weight.");
										break;
									}
									else {
										dock.push(cargo);
										cargoMade = true;
										break;
									}
								default: 
									System.out.println("Invalid Option");
							}
							if (cargoMade) {
								System.out.println();
								System.out.println("Cargo '" + name + "' "
										+ "pushed onto the dock.");
								System.out.println();
								System.out.print(ship.shipLine());
								System.out.println(dockLine(dock));
								System.out.println();
								System.out.println("Total Weight = " 
								+ Math.round(ship.shipWeight()) + " / " 
										+ Math.round(maxWeight));
							}
						}
						
						else if (option.toLowerCase().equals("l")) {
							System.out.print("Select the load destination "
									+ "stack index: ");
							int index = stdin.nextInt();
							stdin.nextLine();
							System.out.println();
							if (maxHeight == ship.stackHeight(index)) {
								System.out.println();
								System.out.println("Operation failed! Cargo "
										+ "stack is at maximum height.");
							}
							else if (maxWeight < ship.shipWeight() 
									+ dock.peek().getWeight()) {
								System.out.println();
								System.out.println("Operation failed! Cargo "
										+ "would put ship overweight.");
							}
							else {
								System.out.println("Cargo '" 
							+ dock.peek().getName() + "' moved from dock "
									+ "to stack " + index + ".");
								ship.pushCargo(dock.pop(), index);
								System.out.println();
								System.out.print(ship.shipLine());
								System.out.println(dockLine(dock));
								System.out.println();
								System.out.println("Total Weight = " 
								+ Math.round(ship.shipWeight()) + " / " 
										+ Math.round(maxWeight));
							}
						}
						
						else if (option.toLowerCase().equals("u")) {
							System.out.print("Select the unload source stack "
									+ "index: ");
							int index = stdin.nextInt();
							stdin.nextLine();
							if (strengthCheck
									(ship.peekStack(index), dock) == false) {
								System.out.println();
								System.out.println("Operation failed! Cargo "
										+ "at top of stack cannot support "
										+ "weight.");
							}
							else {
								System.out.println();
								dock.push(ship.popCargo(index));
								System.out.println("Cargo '" + dock.peek() 
								+ "' moved from stack " + index + " to dock.");
								System.out.println();
								System.out.print(ship.shipLine());
								System.out.println(dockLine(dock));
								System.out.println();
								System.out.println("Total Weight = " 
								+ Math.round(ship.shipWeight()) + " / " 
										+ Math.round(maxWeight));
								
							}
						}
						
						else if (option.toLowerCase().equals("m")) {
							System.out.print("Source stack index: ");
							int source = stdin.nextInt();
							stdin.nextLine();
							System.out.print("Destination stack index: ");
							int destination = stdin.nextInt();
							stdin.nextLine();
							if (strengthCheck(ship.peekStack(source), 
									ship.getStack(destination)) == false) {
								System.out.println();
								System.out.println("Operation failed! Cargo "
										+ "at top of stack cannot support "
										+ "weight.");
							}
							else if 
							(maxHeight == ship.stackHeight(destination)) {
								System.out.println();
								System.out.println("Operation failed! Cargo "
										+ "stack is at maximum height");
							}
							else {
								System.out.println();
								System.out.println("Cargo moved from stack " 
								+ source + " to stack " + destination + ".");
								System.out.println("Cargo '" + 
								ship.peekStack(source).getName() 
								+ "' moved from stack " + source + " to stack "
								+ destination + ".");
								ship.pushCargo(ship.popCargo(source), destination);
								System.out.println();
								System.out.print(ship.shipLine());
								System.out.println(dockLine(dock));
								System.out.println();
								System.out.println("Total Weight = " 
								+ Math.round(ship.shipWeight()) + " / " 
										+ Math.round(maxWeight));
							}
						}
						
						else if (option.toLowerCase().equals("k")) {
							while (dock.empty() == false) {
								dock.pop();
							}
							System.out.println();
							System.out.println("Dock cleared.");
							System.out.println();
							System.out.print(ship.shipLine());
							System.out.println(dockLine(dock));
							System.out.println();
							System.out.println("Total Weight = " 
							+ Math.round(ship.shipWeight()) + " / " 
									+ Math.round(maxWeight));
						}
						
						else if (option.toLowerCase().equals("p")) {
							System.out.println();
							System.out.print(ship.shipLine());
							System.out.println(dockLine(dock));
							System.out.println();
							System.out.println("Total Weight = " 
							+ Math.round(ship.shipWeight()) + " / " 
									+ Math.round(maxWeight));
						}
						
						else if (option.toLowerCase().equals("s")) {
							System.out.print("Enter the name of the cargo: ");
							String input = stdin.nextLine();
							ship.findAndPrint(input);
						}
						
						else if (option.toLowerCase().equals("q")) {
							System.out.println();
							System.out.println("Program terminating "
									+ "normally...");
							active = false;
							param = false;
							stdin.close();
						}
						
						else {
							System.out.println("Invalid input");
						}
					}
					catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
					catch (ShipOverweightException e) {
						System.out.println(e.getMessage());
					}
					catch (EmptyStackException e) {
						System.out.println(e.getMessage());
					}
					catch (FullStackException e){
						System.out.println(e.getMessage());
					}
					catch (CargoStrengthException e) {
						dock.push(ship.popCargo(e.index));
						System.out.println(e.getMessage());
					}
					catch (InputMismatchException e) {
						System.out.println("Please input the right format "
								+ "for the option.");
						stdin.nextLine();
					}
				}
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			catch (InputMismatchException e) {
				System.out.println();
				System.out.println("Please input the right format for the "
						+ "option.");
				stdin.nextLine();
				System.out.println();
			}
		}		
	}
	
	/**
	 * THe Menu options that display each time an action is made with each 
	 * letter that corresponds to it
	 */
	public static void menu() {
		System.out.println();
		System.out.println("Please select an option");
		System.out.println("C) Create new cargo");
		System.out.println("L) Load cargo from dock");
		System.out.println("U) Unload cargo from ship");
		System.out.println("M) Move cargo between stacks");
		System.out.println("K) Clear dock");
		System.out.println("P) Print ship stacks");
		System.out.println("S) Search for cargo");
		System.out.println("Q) Quit");
		System.out.println();
		System.out.print("Select a menu option: ");
	}
	
	/**
	 * 
	 * @param cargo
	 * @param stack
	 * @return
	 */
	public static boolean strengthCheck(Cargo cargo, Stack<Cargo> stack) {
		if (stack.isEmpty()) {
			return true;
		}
		switch (stack.peek().getStrength()) {
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
	 * Returns the String of each cargo on the Dock with a letter respective 
	 * to its CargoStrength
	 * 
	 * @param cargoStack
	 *     The indicated Cargo Stack
	 *     
	 * @return
	 *    A line of letters respective to each cargo's cargoStrength as a
	 *    String
	 */
	public static String dockLine(Stack<Cargo> cargoStack) {
		String output = "Dock: ";
		int count = 0;
		Stack<Cargo> temp = new Stack<Cargo>();
		while (cargoStack.empty() == false) {
			temp.push(cargoStack.pop());
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
			cargoStack.push(temp.pop());
		}
		return output;
	}
}
