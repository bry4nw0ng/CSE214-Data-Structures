/**
 * The Zork class should contain a main method along with two static utility 
 * functions, editGame(StoryTree tree) and playGame(StoryTree tree). These are 
 * the two functions a user can choose to run, which will allow the user to 
 * construct and traverse a StoryTree, respectively. When the program begins, 
 * it should use the static function StoryTree.readTree(filename) to build the 
 * tree from the file (if the file does not yet, the function should simply 
 * return a tree with a single root node.). After constructing the tree, the 
 * program should ask the user whether they wish to edit this tree or play a 
 * game using this tree.
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 * 		Recitation#04
 */

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.zip.DataFormatException;


public class Zork {
	static Scanner stdin = new Scanner(System.in);

	/**
	 * Requests the user to enter a file name and builds a tree from the 
	 * indicated file (if it exists, otherwise uses an empty tree) and asks 
	 * the user whether they would like to edit this tree (E), play a game 
	 * based on this tree (P), or exit the program (Q).
	 * 
	 * @param args
	 *    The input that the user enters according to each menu option
	 */
	public static void main(String[] args) {
		boolean fileCheck = false;
		boolean active = true;
		String file = null;
		StoryTree tree = null;
		
		System.out.println("Hello and Welcome to Zork!");
		try {
			while (fileCheck == false) {
				System.out.println();
				System.out.print("Please enter the file name: ");
				file = stdin.next();
				System.out.println();
				System.out.println("Loading game from file...");
				System.out.println();
			
				if (file == null || file.isEmpty() || !file.endsWith(".txt")) {
					
					System.out.println("File not found...");
				}
				else {
					tree = StoryTree.readTree(file);
					if (tree != null) {	
						System.out.println("File loaded!");
						fileCheck = true;
					}
				}
			}			
			
			while (active) {
				System.out.println();
				System.out.print("Would you like to edit (E), play (P) or "
						+ "quit (Q)? ");
				String option = stdin.next();
				System.out.println();
				
				if (option.toUpperCase().equals("E")) {
					editTree(tree);
				}
			
				else if (option.toUpperCase().equals("P")) {
					playTree(tree);
				}
				
				else if (option.toUpperCase().equals("Q")) {
					active = false;
					System.out.println();
					System.out.println("Game being saved to " + file + "...");
					System.out.println();
					StoryTree.saveTree(file, tree);
					System.out.println("Save successful!");
					System.out.println();
					System.out.println("Program terminating normally.");
					stdin.close();
				}
			}
		}
		catch (InputMismatchException e) {
			System.out.println("Please input the right format for the "
					+ "option.");
		}
		catch (IllegalArgumentException | FileNotFoundException e) {
			e.getMessage();
		}
		catch (DataFormatException e) {
			e.getMessage();
		}
		catch (TreeFullException e) {
			e.getMessage();
		}
		catch (NodeNotPresentException e) {
			e.getMessage();
		}
	}
	
	/**
	 * Provides a user interface allowing a user to edit the story followed 
	 * by the game represented by tree. This function should continue to 
	 * process user commands until the user enters Q to exit. The following 
	 * commands should be supported for editing the tree:
	 * 
	 * @param tree
	 *    inputted StoryTree
	 *    
	 * @throws TreeFullException
	 * @throws NodeNotPresentException
	 */
	public static void editTree(StoryTree tree) 
			throws TreeFullException, NodeNotPresentException {
		boolean edit = false;
		
		while (edit == false) {
			editMenu();
			
			String option = stdin.next();
			
			if (option.toUpperCase().equals("V")) {
				System.out.println();
				System.out.println("Position: " + tree.getCursorPosition());
				System.out.println("Option: " + tree.cursor.getOption());
				System.out.println("Message: " + tree.getCursorMessage());
				System.out.println();
			}
			else if (option.toUpperCase().equals("S")) {
				System.out.println();
				if (tree.getOptions()[0][0] == null) {
					System.out.println("Node has no children");
					System.out.println();
				}
				else {
					System.out.print("Please select a child: [");
					String[] options = new String[3];
					for (int i = 0; i < tree.getOptions().length; i++) {
						if (tree.getOptions()[i][0] == null) {
							continue;
						}
						else {
							System.out.print(i+1);
							int num = i+1;
							options[i] = Integer.toString(num);
						}
						if (i != tree.getOptions().length-1 
								&& tree.getOptions()[i+1][0] != null) {
							System.out.print(", ");
						}
					}
					System.out.print("] ");
					
					String input = stdin.next();
					boolean invalid = false;
					for (int i = 0; i < options.length; i++) {
						if (options[i] != null && options[i].equals(input)) {
							break;
						}
						else if (i == options.length -1) {
							invalid = true;
						}
					}
					if (invalid == true) {
						System.out.println("Error. No child " + input 
								+ " for the current node.");
					}
					else if (invalid == false) {
						switch (input) {
							case "1":
								tree.cursor = tree.cursor.getLeftChild();
								break;
							case "2": 
								tree.cursor = tree.cursor.getMiddleChild();
								break;
							case "3":
								tree.cursor = tree.cursor.getRightChild();
								break;
						}
					}
				}
			}
			else if (option.toUpperCase().equals("O")) {
				stdin.nextLine();
				System.out.print("Please enter a new option: ");
				String newOption = stdin.nextLine();
				tree.setCursorOption(newOption);
				System.out.println("");
				System.out.println("Option set.");
				System.out.println();
			}
			else if (option.toUpperCase().equals("M")) {
				stdin.nextLine();
				System.out.print("Please enter a new message: ");
				String newMessage = stdin.nextLine();
				tree.setCursorMessage(newMessage);
				System.out.println();
				System.out.println("Message set");
			}
			else if (option.toUpperCase().equals("A")) {
				if (tree.cursor.getLeftChild() != null 
						&& tree.cursor.getMiddleChild() != null 
						&& tree.cursor.getRightChild() != null) {
					System.out.println("Error, maximum children reached");
				}
				else {
					stdin.nextLine();
					System.out.print("Enter an option: ");
					String newOption = stdin.nextLine();
					System.out.print("Enter a message: ");
					String newMessage = stdin.nextLine();
					
					try {
						tree.addChild(newOption, newMessage);
						System.out.println();
					} 
					catch (IllegalArgumentException e) {
						throw new IllegalArgumentException("Illegal argument");
					} 
					catch (TreeFullException e) {
						throw new TreeFullException("Tree is full");
					}
				}
			}
			else if (option.toUpperCase().equals("D")) {
				System.out.println();
				if (tree.getOptions()[0][0] == null) {
					System.out.println("No child");
				}
				else {
					System.out.print("Please select a child: [");
					String[] options = new String[3];
					for (int i = 0; i < tree.getOptions().length; i++) {
						if (tree.getOptions()[i][0] == null) {
							continue;
						}
						else {
							System.out.print(i+1);
							int num = i+1;
							options[i] = Integer.toString(num);
						}
						if (i != tree.getOptions().length-1 
								&& tree.getOptions()[i+1][0] != null) {
							System.out.print(", ");
						}
					}
					System.out.print("] ");
					
					String input = stdin.next();
					boolean invalid = false;
					for (int i = 0; i < options.length; i++) {
						if (options[i] != null && options[i].equals(input)) {
							break;
						}
						else if (i == options.length -1) {
							invalid = true;
						}
					}
					if (invalid == true) {
						System.out.println("Error. No child " + input 
								+ " for the current node.");
					}
					else if (invalid == false) {
						tree.removeChild(input);
						System.out.println("Subtree deleted.");
						System.out.println();
					}
				}
			}
			else if (option.toUpperCase().equals("R")) {
				tree.resetCursor();
				if (tree.cursor.getLeftChild() != null) {
					tree.cursor = tree.cursor.getLeftChild();
				}
				System.out.println("Cursor moved to root");
				System.out.println();
			}
			else if (option.toUpperCase().equals("P")) {
				tree.returnToParent();
				System.out.println();
			}
			else if (option.toUpperCase().equals("Q")) {
				edit = true;
			}
		}

	}
	
	/**
	 * The Menu options that display each time an action is made with each 
	 * letter that corresponds to it
	 */
	public static void editMenu() {
		System.out.println("Zork Editor");
		System.out.println("V: View the cursor's position, option and "
				+ "message.");
		System.out.println("S: Select a child of this cursor (options are 1, "
				+ "2, and 3).");
		System.out.println("O: Set the option of the cursor.");
		System.out.println("M: Set the message of the cursor.");
		System.out.println("A: Add a child StoryNode to the cursor.");
		System.out.println("D: Delete one of the cursor's children and all "
				+ "its descendants.");
		System.out.println("R: Move the cursor to the root of the tree.");
		System.out.println("P: Return cursor node to parent node");
		System.out.println("Q: Quit editing and return to main menu.");
		System.out.print("Please select an option: ");
	}
	
	/**
	 * Provides a user interface allowing a player to play the game 
	 * represented by tree This method will allow a user to traverse the tree 
	 * by continually displaying messages and allowing the user to select 
	 * options until a leaf is reached.
	 * 
	 * @param tree
	 *    Inputted StoryTree
	 * 
	 * @throws NodeNotPresentException
	 */
	public static void playTree(StoryTree tree) 
			throws NodeNotPresentException {
		System.out.println();
		System.out.println("Let's Play!");
		System.out.println();
		boolean playTime = false;
		tree.resetCursor();
		tree.cursor = tree.cursor.getLeftChild();
		
		while (playTime == false) {
			System.out.println(tree.cursor.getMessage());
			
			if (tree.cursor.isWinningNode() || tree.cursor.isLosingNode()) {
				playTime = true;
				System.out.println("Thanks for playing.");
			}
			else {
				for (int i = 0; i < tree.getOptions().length; i++) {
					if (tree.getOptions()[i][1] == null) {
						continue;
					}
					else {
						System.out.println(i+1 + ") " 
					+ tree.getOptions()[i][1]);
					}
				}
				
				System.out.print("Please make a choice: ");
				int choice = stdin.nextInt();
				tree.selectChild(Integer.toString(choice));
				System.out.println();
			}	
		}
	}
}
