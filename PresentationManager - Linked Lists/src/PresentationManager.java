/**
 * The Presentation manager contains a SlideList where a menu can be accessed
 * to interact with each method within the SlideList class
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 * 		Recitation#04
 */
import java.util.Scanner;
import java.util.InputMismatchException;

public class PresentationManager {
	
	/**
	 * The main method that creates a slide show in the form of a SlideList
	 * then displays a menu to interact with each Slide 
	 *  
	 * @param args
	 *    the input that the user enters according to each menu option
	 */
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		boolean active = true;
		SlideList presentation = new SlideList();
		
		System.out.println("Welcome to PresentationManager!");
		
		while (active == true) {
			try {		
				menu();
				String option = stdin.nextLine();
				
				if (option.equals("F") || option.equals("f")) {
					presentation.cursorFoward();
					System.out.println("The cursor moved forward to slide \"" 
					+ presentation.getCursorSlide().getTitle() + "\".");
				}
				
				else if (option.equals("B") || option.equals("b")) {
					presentation.cursorBackward();
					System.out.println("The cursor moved backward to slide \""
					+ presentation.getCursorSlide().getTitle() + "\".");

				}
				
				else if (option.equals("D") || option.equals("d")) {
					if (presentation.size() == 0) {
						System.out.println("Empty slideshow");
					}
					else {
						System.out.println("==============================" 
								+ "================");
						System.out.println(presentation.getCursorSlide()
								.getTitle());
						System.out.println("==============================" 
								+ "================");
						for (int i = 1; i <= presentation.getCursorSlide()
								.getNumBullets(); i++) {
							System.out.println(i + ". " + 
						presentation.getCursorSlide().getBullet(i));
						}
						System.out.println("==============================" 
								+ "================");
					}
				}
				
				else if (option.equals("E") || option.equals("e")) {
					if (presentation.size() == 0) {
						System.out.println("Empty slideshow");
					}
					else {
						System.out.print("Edit title, duration, or bullets? "
								+ "(t/d/b) ");
						String input = stdin.nextLine();
						if (input.equals("t")) {
							System.out.print("Set a new title: ");
							presentation.getCursorSlide()
							.setTitle(stdin.nextLine());
						}
						else if (input.equals("d")) {
							System.out.print("Set a new duration: ");
							presentation.getCursorSlide()
							.setDuration(stdin.nextDouble());
							stdin.nextLine();
						}
						else if (input.equals("b")) {
							System.out.print("Bullet index: ");
							int index = stdin.nextInt();
							stdin.nextLine();
							System.out.print("Delete or edit? (d/e) ");
							String change = stdin.nextLine();
							if (change.equals("d")) {
								presentation.getCursorSlide()
								.setBullet(null, index);
								System.out.println();
								System.out.println("Bullet " + index 
										+ " has been deleted");
							}
							else if (change.equals("e")) {
								System.out.print("Bullet " + index + ": ");
								presentation.getCursorSlide()
								.setBullet(stdin.nextLine(), index);
							}
							else {
								System.out.println("Invalid option");
							}
						}
						else {
							System.out.println("Invalid option");
						}
					}
				}
				
				else if (option.equals("P") || option.equals("p")) {
					System.out.println("Slideshow summary: ");
					System.out.println("==============================" 
							+ "================");
					System.out.println(String.format("%-11s%-14s%-11s%-10s", 
							"  Slide", "Title", "Duration", "Bullets"));
					System.out.println("----------------------------------" 
							+ "------------");
					System.out.print(presentation.toString());
					System.out.println("==============================" 
							+ "================");
					System.out.println("Total: " + presentation.size() 
							+ " slide(s), " 
							+ String.format("%.1f", presentation.duration()) 
							+ " minute(s), " + presentation.numBullets() 
							+ " bullet(s)");
					System.out.println("==============================" 
							+ "================");
				}
				
				else if (option.equals("A") || option.equals("a")) {
					Slide append = new Slide();
					System.out.print("Enter the slide title: ");
					append.setTitle(stdin.nextLine());
					System.out.print("Enter the slide duration: ");
					append.setDuration(stdin.nextDouble());
					stdin.nextLine();
					int count = 1;
					boolean add = true;
					while (add == true) {
						System.out.print("Bullet " + count + ": ");
						append.setBullet(stdin.nextLine(), count);
						if (count == 5) {
							System.out.println("No more bullets allowed. " 
									+ "Slide is full.");
							add = false;
							break;
						}
						System.out.print("Add another bullet point? (y/n) ");
						String input = stdin.nextLine();
						if (input.equals("y")) {
							count++;
						}
						else if (input.equals("n")) {
							add = false;
						}
						else {
							System.out.println("Invalid Option.");
							break;
						}
					}
					if ( add == false) {
						presentation.appendToTail(append);
						System.out.println();
						System.out.println("Slide \"" + append.getTitle() 
						+ "\" added to presentation.");	
					}
				}
				
				else if (option.equals("I") || option.equals("i")) {
					Slide insert = new Slide();
					System.out.print("Enter the slide title: ");
					insert.setTitle(stdin.nextLine());
					System.out.print("Enter the slide duration: ");
					insert.setDuration(stdin.nextDouble());
					stdin.nextLine();
					int count = 1;
					boolean add = true;
					while (add == true) {
						System.out.print("Bullet " + count + ": ");
						insert.setBullet(stdin.nextLine(), count);
						if (count == 5) {
							System.out.println("No more bullets allowed. "
									+ "Slide is full.");
							add = false;
							break;
						}
						System.out.print("Add another bullet point? (y/n) ");
						String input = stdin.nextLine();
						if (input.equals("y")) {
							count++;
						}
						else if (input.equals("n")) {
							add = false;
						}
						else {
							System.out.println("Invalid Option.");
							break;
						}
					}
					if (add == false) {
						presentation.insertBeforeCursor(insert);
						System.out.println();
						System.out.println("Slide \"" + insert.getTitle() 
						+ "\" added to presentation.");
					}
				}
				
				else if (option.equals("R") || option.equals("r")) {
					if (presentation.size() == 0) {
						System.out.println("Empty slideshow");
					}
					else {
						System.out.println("Slide \"" 
					+ presentation.removeCursor().getTitle() 
					+ "\" has been removed.");
					}
				}
				
				else if (option.equals("H") || option.equals("h")) {
					if (presentation.size() == 0) {
						System.out.println("Empty slideshow");
					}
					else {
						presentation.resetCursorToHead();
						System.out.println("Cursor has been reset to head.");
					}
				}
				
				else if (option.equals("Q") || option.equals("q")) {
					System.out.println("Program terminating normally...");
					System.out.println();
					active = false;
					stdin.close();
				}
				else {
					System.out.println("Invalid option");
				}
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			catch (EndOfListException e) {
				System.out.println(e.getMessage());
			}
			catch (InputMismatchException e) {
				System.out.println("Please input the right format for " 
				  + " the option.");
			}
		}
	}
	
	/**
	 * The menu options that display each time an action is made with each
	 * letter than corresponds to it.
	 */
	public static void menu() {
		System.out.println();
		System.out.println("Please select an option");
		System.out.println("F) Move cursor forward");
		System.out.println("B) Move cursor backward");
		System.out.println("D) Display cursor slide");
		System.out.println("E) Edit cursor slide");
		System.out.println("P) Print presentation summary");
		System.out.println("A) Append new slide to tail");
		System.out.println("I) Insert new slide before cursor");
		System.out.println("R) Remove slide at cursor");
		System.out.println("H) Reset cursor to head");
		System.out.println("Q) Quit");
		System.out.println();
		System.out.print("Select a menu option: ");
	}

}
