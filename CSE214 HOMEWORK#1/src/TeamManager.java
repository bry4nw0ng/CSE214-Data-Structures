/**
 * The Team Manager contains 5 distinct teams where a menu can be accessed
 * to interact with each method within the Team class.
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 *		Recitation#04
 */
import java.util.Scanner;
import java.util.InputMismatchException;

public class TeamManager {
	public static final int MAX_TEAMS = 5;

	/**
	 * The main method that creates five empty teams then displays a menu 
	 * to interact with each Team and its contents.
	 * 
	 * @param args
	 *    the input that the user enters according to each menu option
	 *    
	 * @throws FullTeamException
	 * 	  Team is full, so that no more Player Objects can be stored
	 */
	public static void main(String[] args) throws FullTeamException {
		Scanner stdin = new Scanner(System.in);
		boolean active = true;
		Team[] teams = new Team[5];
		for (int i = 0; i < teams.length; i++) {
			teams[i] = new Team();
		}
		Team select;
		System.out.println("Welcome to TeamManager!");
		System.out.println();
		System.out.println("Team 1 is currently selected.");
		select = teams[0];
		while (active == true) {
			try {
				menu();
				String option = stdin.nextLine();
				
				/**
				 * A) Add Player. <Name> <Hits> <Errors> <Position>
				 */
				if (option.equals("A") || option.equals("a")) {
					Player input = new Player();
					System.out.print("Enter the player's name: ");
					input.setName(stdin.nextLine());
					System.out.print("Enter the number of hits: ");
					input.setNumHits(stdin.nextInt());
					System.out.print("Enter the number of errors: ");
					input.setNumErrors(stdin.nextInt());
					System.out.print("Enter the position: ");
					select.addPlayer(input, stdin.nextInt());
					System.out.println("Player added: " + input.toString());
					stdin.nextLine();
				}
				
				/**
				 * G) Get Player stats. <Position>

				 */
				else if (option.equals("G") || option.equals("g")) {
					System.out.print("Enter the position: ");
					int position = stdin.nextInt();
					Player getPlayer = select.getPlayer(position);
					System.out.println(getPlayer.toString());
					stdin.nextLine();
				}

				/**
				 * L) Get leader in a stat. <Stat>
				 */
				else if (option.equals("L") || option.equals("l")) {
					System.out.print("Enter the stat: ");
					String stat = stdin.nextLine().toLowerCase();
					Player leader = select.getLeader(stat);
					if (leader == null) {
						System.out.println("No such statistics.");
						continue;
					}
					System.out.println("Leader in " + stat + ": " + leader);
				}
				
				/**
				 * R) Remove a player. <Position>
				 */
				else if (option.equals("R") || option.equals("r")) {
					System.out.print("Enter the position: ");
					int position = stdin.nextInt();
					if (select.players[position - 1] == null) {
						System.out.println("No player at position " + position
						  + " to remove");
						stdin.nextLine();
						continue;
					}
					String name = select.players[position - 1].getName();
					select.removePlayer(position);
					System.out.println("Player removed at position " + position);
					System.out.println(name + " has been removed from the team");
					stdin.nextLine();
				}
				
				/**
				 * P) Print all players. <Team>
				 */
				else if (option.equals("P") || option.equals("p")) {
					System.out.print("Select team index: ");
					teams[stdin.nextInt() - 1].printAllPlayers();
					stdin.nextLine();
				}

				/**
				 * S) Size of team. <Team>
				 */
				else if (option.equals("S") || option.equals("s")) {
					System.out.println("There are " + select.size() 
					  + " players(s) in the current Team.");
				}

				/**
				 * T) Select team <Index>
				 */
				else if (option.equals("T") || option.equals("t")) {
					System.out.print("Enter team index to select: ");
					int index = stdin.nextInt();
					if (index <= MAX_TEAMS && index >= 1) {
						select = teams[index - 1];
						System.out.println("Team " + index + " has " + 
						  "been selected.");
					} else {
						System.out.println("Invalid Index for team.");
					}
					stdin.nextLine();
				}

				/**
				 * C) Clone team <From> <To>
				 */
				else if (option.equals("C") || option.equals("c")) {
					System.out.println("Select team to clone from: ");
					int clonedFrom = stdin.nextInt();
					System.out.println("Select team to clone to: ");
					int clonedTo = stdin.nextInt();
					while (teams[clonedTo - 1].size() != 0) {
						teams[clonedTo - 1].removePlayer(1);
					}
					teams[clonedTo - 1] = (Team) teams[clonedFrom - 1].clone();
					System.out.println("Team " + clonedFrom + " has been "
					  + " copied to team " + clonedTo);
					stdin.nextLine();
				}

				/**
				 * E) Team equals <Team1> <Team2>
				 */
				else if (option.equals("E") || option.equals("e")) {
					System.out.print("Select first team index: ");
					int firstTeam = stdin.nextInt();
					System.out.print("Select second team index: ");
					int secondTeam = stdin.nextInt();
					if (teams[firstTeam - 1].equals(teams[secondTeam - 1])) {
						System.out.println("These teams are equal.");
					} else {
						System.out.println("These teams are not equal.");
					}
					stdin.nextLine();
				}

				/**
				 * U) Update stat. <Name> <Stat> <numHits>
				 */
				else if (option.equals("U") || option.equals("u")) {
					System.out.print("Enter the player to update: ");
					String name = stdin.nextLine();
					System.out.print("Enter stat to update: ");
					String statName = stdin.nextLine();
					System.out.print("Enter the new number of " 
					  + statName + ": ");
					int statNum = stdin.nextInt();
					for (int i = 0; i < select.size(); i++) {
						if (name.equals(select.players[i].getName())) {
							if (statName.equals("errors")) {
								select.players[i].setNumErrors(statNum);
								System.out.println("Updated " + name
								  + " errors");
								break;
							} else if (statName.equals("hits")) {
								select.players[i].setNumErrors(statNum);
								System.out.println("Updated " + name 
								  + " hits");
								break;
							} else {
								System.out.println("Not a valid stat.");
								break;
							}
						} else if (i == select.size() - 1) {
							System.out.println("Player not found.");
						}
					}
					stdin.nextLine();
				}

				/**
				 * Q) Quit.
				 */
				else if (option.equals("Q") || option.equals("q")) {
					System.out.println("Program terminating normally...");
					System.out.println();
					active = false;
					stdin.close();
				}

				else {
					System.out.println("Invalid input");
				}
			} 
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				stdin.nextLine();
			} 
			catch (InputMismatchException e) {
				System.out.println("Please input the right format for " 
				  + " the option.");
				stdin.nextLine();
			}
			catch (FullTeamException e) {
				System.out.println(e.getMessage());
				stdin.nextLine();
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
		System.out.println("A) Add Player");
		System.out.println("G) Get Player Stats");
		System.out.println("L) Get leader in a stat");
		System.out.println("R) Remove a player");
		System.out.println("P) Print all players");
		System.out.println("S) Size of Team");
		System.out.println("T) Select team");
		System.out.println("C) Clone team");
		System.out.println("E) Team equals");
		System.out.println("U) Update Stat");
		System.out.println("Q) Quit");
		System.out.println();
		System.out.print("Select a menu option: ");
	}
}
