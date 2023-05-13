/**
 * The NeoViewer class allows a user to view datasets obtained from the NASA 
 * NeoW API. This class should contain a main() method which creates a 
 * database and prompts the user to add a page to the database, sort the 
 * current database, and display the database.
 * 
 * @author Bryan Wong
 * 		ID#115226034
 *		Recitation#04
 */
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NeoViewer {
	
	/**
	 */
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		boolean active = true;
		NeoDatabase data = new NeoDatabase();
		int pageLoader;
		String queryURL;
		Comparator<NearEarthObject> comp;
		
		System.out.println("Welcome to NEO Viewer!");
		
		while (active) {
			try {
				menu();
				
				System.out.print("Please select an option: ");
				String option = stdin.nextLine();	

				if (option.toUpperCase().equals("A")) {
					System.out.print("Enter a page to load: ");
					pageLoader = stdin.nextInt();
					stdin.nextLine();
					queryURL = data.buildQueryURL(pageLoader);
					data.addAll(queryURL);
					System.out.println("Page loaded successfully!");
					System.out.println();
					
				}
				
				else if (option.toUpperCase().equals("S")) {
					sortMenu();
					
					System.out.print("Select a menu option: ");
					String choice = stdin.nextLine();
					
					if (choice.toUpperCase().equals("R")) {
                        comp = new ReferenceIDComparator();

                        data.sort(comp);

                        System.out.println();
                        System.out.println("Table sorted by Reference ID.");
                        stdin.nextLine();
					}
					
					else if (choice.toUpperCase().equals("D")) {
                        comp = new DiameterComparator();

                        data.sort(comp);

                        System.out.println();
                        System.out.println("Table sorted by Diameter.");
                        stdin.nextLine();
					}
					
					else if (choice.toUpperCase().equals("A")) {
                        comp= new ApproachDateComparator();

                        data.sort(comp);

                        System.out.println();
                        System.out.println("Table sorted by Approach Date.");
                        stdin.nextLine();
					}
					
					else if (choice.toUpperCase().equals("M")) {
                        comp = new MissDistanceComparator();

                        data.sort(comp);

                        System.out.println("\nTable sorted by Missed "
                        		+ "Distance.");
                        stdin.nextLine();
					}
					
					else {
                        System.out.println("Please enter a valid input."); 
                        stdin.nextLine();
					}
				}
				
				else if (option.toUpperCase().equals("P")) {
					data.printTable();
                    System.out.println();
				}
				
				else if (option.toUpperCase().equals("Q")) {
					System.out.println("Program terminating normally...");
					active = false;
					stdin.close();
				}
				
			}
			catch (IllegalArgumentException e) {
				e.getMessage();
			}
            catch(InputMismatchException e){
                System.out.println("Please input a valid option.");
                stdin.nextLine();
            }
		}
	}
	
	public static void menu() {
		System.out.println();
		System.out.println("A) Add a page to the database");
		System.out.println("S) Sort the database");
		System.out.println("P) Print the database as a table.");
		System.out.println("Q) Quit");
		System.out.println();
	}

	public static void sortMenu() {
		System.out.println();
		System.out.println("R) Sort by referenceID");
		System.out.println("D) Sort by diameter");
		System.out.println("A) Sort by approach date");
		System.out.println("M) Sort by miss distance");
		System.out.println();
	}
}
