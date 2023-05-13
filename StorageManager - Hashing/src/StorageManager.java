/**
 * The StorageManager class allows the user to interact with the storage 
 * database by listing the storage boxes occupied, allowing the user to add 
 * or remove storage boxes, searching for a box by id, and listing all the 
 * boxes for a user. In addition, the class should provide the functionality 
 * to load a saved (serialized) StorageTable or create a new one if a saved 
 * table does not exist. On startup, the StorageManager should check to see 
 * if the file storage.obj exists in the current directory. If it does, then 
 * the file should be loaded and deserialized into a StorageTable. If the file 
 * does not exist, an empty StorageTable object should be created and used 
 * instead. In either case, the user should be allowed to fully interact with 
 * the storage table, inserting, removing, selecting, and reading entries. 
 * When the user enters 'Q' to quit the program, the storage table should be 
 * serialized to the file storage.obj. That way, the next time the program is 
 * run, the storages will remain in the database and allow different users to 
 * manipulate the storage records. If you would like to 'reset' the storage 
 * table, use the "X" command to delete the file, if it exists, when the 
 * program quits.
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 * 		Recitation#04
 */

import java.io.*;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StorageManager {
	
	private static StorageTable storage; // the hash table that stores Strings
	
	/**
	 * The main method that loads in the hash map in the form of a Storage 
	 * table with each Storage
	 *  
	 * @param args
	 *    the input that the user enters according to each menu option
	 */
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		boolean active = true;
		boolean valid = false;
		while (valid == false) {
			try {
				FileInputStream file = new FileInputStream("storage.obj");
				ObjectInputStream inStream = new ObjectInputStream(file);
				storage = (StorageTable) inStream.readObject();
				valid = true;
				inStream.close();
			}
			catch (IOException e){
				storage = new StorageTable();
				valid = true;
			} 
			catch (ClassNotFoundException e) {
				System.out.println("Class not found.");
			}
		}
		
		System.out.println("Hello, and welcome to Rocky Stream Storage "
				+ "Manager.");
		
		while (active == true) {
			try {	
				menu();
	
				System.out.print("Please select an option: ");
				String option = stdin.nextLine();	
	
				if (option.toUpperCase().equals("P")) {
					if (storage.isEmpty()) {
						System.out.println("Table is empty.");
					}
					else {
						Collection<Storage> collection = storage.values();
						System.out.println(String.format("%-13s%-32s%-19s", 
								"Box#", " Contents", "Owner"));
						System.out.println("----------------------------------"
								+ "------------------------------");;
						for (Storage s : collection) {
							System.out.println(String.format("%-13s%-32s%-19s",
									s.getId(), s.getContents(), 
									s.getClient()));
						}
					}
				}
				
				else if (option.toUpperCase().equals("A")) {
					System.out.print("Please enter id: ");
					int id = stdin.nextInt();
					stdin.nextLine();
					System.out.print("Please enter client: ");
					String client = stdin.nextLine();
					System.out.print("Please enter contents: ");
					String contents = stdin.nextLine();
					Storage tempStorage = new Storage(id, client, contents);
					storage.putStorage(id, tempStorage);
					System.out.println();
					System.out.println("Storage " + id + " set!");
				}
				
				else if (option.toUpperCase().equals("R")) {
					System.out.print("Please enter an ID: ");
					int id = stdin.nextInt();
					stdin.nextLine();
					if (storage.remove(id) == null) {
						System.out.println("There is no storage associated "
								+ "with the given id");
					}
					else {
						System.out.println("Box " + id + " is now removed.");
					}
				}
				
				else if (option.toUpperCase().equals("C")) {
					System.out.print("Please enter the name of the client: ");
					String client = stdin.nextLine();
					boolean test = false;
					Collection<Storage> collection = storage.values();
					for (Storage s: collection) {
						if (s.getClient().equals(client) == true) {
							test = true;
							break;
						}
					}
					if (test == false) {
						System.out.println("There are no storages with the "
								+ "client name, " + client);
					}
					else if (test == true) {
						System.out.println(String.format("%-13s%-32s%-19s", 
								"Box#", " Contents", "Owner"));
						System.out.println("----------------------------------"
								+ "------------------------------");
						for (Storage s: collection) {
							if (s.getClient().equals(client) == true) {
								System.out.println(
										String.format("%-13s%-32s%-19s", 
												s.getId(), s.getContents(), 
												s.getClient()));
							}
						}
					}
				}
				
				else if (option.toUpperCase().equals("F")) {
					System.out.print("Please enter ID: ");
					int id = stdin.nextInt();
					stdin.nextLine();
					Storage info = storage.get(id);
					if (info == null) {
						System.out.println("No storage at index " + id);
					}
					else {
						System.out.println("Box " + id);
						System.out.println("Contents: " + info.getContents());
						System.out.println("Owner: " + info.getClient());
					}
				}
				
				else if (option.toUpperCase().equals("Q")) {
					FileOutputStream file = 
							new FileOutputStream("storage.obj");
					ObjectOutputStream outStream = 
							new ObjectOutputStream(file);
					outStream.writeObject(storage);
					
					System.out.println("Storage Manager is quitting, current "
							+ "storage is saved for next session.");
					active = false;
					outStream.close();
					stdin.close();
				}
				
				else if (option.toUpperCase().equals("X")) {
					storage.clear();
					FileOutputStream file = 
							new FileOutputStream("storage.obj");
					ObjectOutputStream outStream = 
							new ObjectOutputStream(file);
					outStream.writeObject(storage);
					System.out.println("Storage Manager is quitting, all data "
							+ "is being erased.");
					
					active = false;
					outStream.close();
					stdin.close();
				}
				
				else {
					System.out.println("Enter a valid option.");
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Not a valid input.");
			}
			catch (IllegalArgumentException e) {
				e.getMessage();
			} catch (IOException e) {
				System.out.println("IO Exception caught");
			}
		}

	}
	
	/**
	 * The menu options that display each time an action is made with each
	 * letter than corresponds to it.
	 */
	public static void menu() {
		System.out.println();
		System.out.println("P - Print all storage boxes");
		System.out.println("A - Insert into storage box");
		System.out.println("R - Remove contents from a storage box");
		System.out.println("C - Select all boxes owned by a particular "
				+ "client");
		System.out.println("F - Find a box by ID and display its owner and "
				+ "contents");
		System.out.println("Q - Quit and save workspace");
		System.out.println("X - Quit and delete workspace");
		System.out.println();
	}
}
