/**
 * The Team class contains an array of up to 40 players which can also keep 
 * track of the current amount of players within it. A Team can be cloned 
 * and compared to another team. Players can be added to and removed
 * from the Team class. Certain players can be retrieved along with a 
 * Team's leader in errors(lowest) or hits(highest).The entire team with 
 * each of its player's details can also be displayed in a chart in order 
 * of each player's position.
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 *		Recitation#04
 */
public class Team {
	public static final int MAX_PLAYERS = 40;

	Player[] players; // An array holding the Players on a Team
	int playerCount = 0; // The number of active Players on the Team
	
	/**
	 * Returns an instance of Team with no Player objects and a maximum 
	 * amount of 40 Players
	 */
	public Team() {
		players = new Player[MAX_PLAYERS];
	}

	/**
	 * Clones the current Team with with each of its Players
	 * 
	 * @return
	 *    Returns the cloned Team as an Object
	 */
	public Object clone() {
		Team clone = new Team();
		for (int i = 0; i < size(); i++) {
			if (players[i] != null) {
				try {
					clone.addPlayer((Player) players[i].clone(), i + 1);
				} 
				catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				} 
				catch (FullTeamException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		return (Object) clone;
	}
	
	/**
	 * Indicates whether the details of a Team are equal to another Team
	 * 
	 * @param obj
	 *    Another Team that is to be compared with the current Team
	 *    
	 * @return
	 *    True, if obj is a Team object with the same Players in the same 
	 *    order as the current Team. False, if obj is null, is not a Team 
	 *    object, or has a different layout of Players
	 */
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Team) {
			Team test = (Team) obj;
			for (int i = 0; i < size(); i++) {
				if (this.players[i].equals(test.players[i]) == false) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Indicates the number of Players currently on the Team
	 * 
	 * @precondition
	 *    The Team object has been instantiated
	 *    
	 * @return
	 *    Returns the number of Players in the Team
	 */
	public int size() {
		return playerCount;
	}

	/**
	 * Adds a Player to the Team at an indicated position
	 * 
	 * @param p
	 *    The new Player Object being added to the Team
	 *    
	 * @param position
	 *    The indicated position that the Player is being added at
	 *    
	 * @precondition
	 *    The Team object has been instantiated. The position must be a value 
	 *    at least 1 and at most one more than the current amount of players 
	 *    on the team (playerCount). The number of players in the Team must be
	 *     less than MAX_PLAYERS.
	 * 
	 * @postcondition
	 *    The Player is stored at the indicated position on the Team, leaving 
	 *    the Players that were at the same value or greater shifted down one 
	 *    position. 
	 * 
	 * @throws IllegalArgumentException
	 *    Indicates that the position is not within a valid range
	 * @throws FullTeamException 
	 * 	  Team is full, so that no more Player Objects can be stored
	 */
	public void addPlayer(Player p, int position) 
	  throws IllegalArgumentException, FullTeamException {
		if (size() < MAX_PLAYERS) {
			if (position <= size() + 1 && position >= 1) {
				if (players[position - 1] == null) {
					players[position - 1] = p;
				} else {
					for (int i = size() - 1; i >= position - 1; i--) {
						players[i + 1] = players[i];
					}
					players[position - 1] = p;
				}
				playerCount++;
			} 
			else {
				throw new IllegalArgumentException("Invalid position for "
				  + " adding the new player.");
			}
		} 
		else {
			throw new FullTeamException("Team is full");
		}
	}
	
	/**
	 * Removes a player from the Team at an indicated position
	 * 
	 * @param position
	 *    The indicated position that the Player is being removed from
	 *    
	 * @precondition
	 *    The Team has been instantiated. The position the player is being 
	 *    removed from at least 1 and at most the current amount of players
	 *    on the team(playerCount).
	 * 
	 * @postcondition
	 *    The Player is removed at the indicated position on the Team, leaving 
	 *    the Players that were at the same value or greater shifted up one 
	 *    position. 
	 *    
	 * @throws IllegalArgumentException
	 *    Indicates that the position is not within a valid range
	 */
	public void removePlayer(int position) throws IllegalArgumentException {
		if (position <= size() || position >= 1) {
			if (players[position - 1] == null) {
				throw new IllegalArgumentException("No player at position " 
				  + position + " to remove");
			} else {
				for (int i = position - 1; i < size(); i++) {
					if (i == size() - 1) {
						players[i] = null;
					} else {
						players[i] = players[i + 1];
					}
				}
				players[size() - 1] = null;
				playerCount--;
			}
		} else {
			throw new IllegalArgumentException("Invalid position");
		}
	}

	/**
	 * Returns a reference to the player on the Team at an indicated position
	 * 
	 * @param position
	 *    The indicated position of the Player that is being retrieved from
	 *    
	 * @precondition
	 *    This Team has been instantiated. A valid position would be the range
	 *    of the amount of players on the Team.
	 *    
	 * @return
	 *    The player at the given position
	 *    
	 * @throws IllegalArgumentException
	 *    Indicates that the position given is not within the valid range
	 */
	public Player getPlayer(int position) throws IllegalArgumentException {
		if (position > size() || position < 1) {
			throw new IllegalArgumentException("Not Within Valid Range");
		} else {
			return players[position - 1];
		}
	}
	
	/**
	 * Returns the Player on the Team with the best value for the indicated 
	 * statistic being the highest value of "hits" or the lowest value of 
	 * "errors"
	 * 
	 * @param stat
	 *    the indicated statistic of which can be "hits" or "errors"
	 *    
	 * @precondition
	 *    The Team must be instantiated
	 *    
	 * @return
	 *    THe Player with the best indicated statistic
	 *    
	 * @throws IllegalArgumentException
	 *    the indicated statistic was neither "hits" nor "errors"
	 */
	public Player getLeader(String stat) throws IllegalArgumentException {
		if (stat.equals("hits") == true) {
			int high = 0;
			for (int i = 0; i < size(); i++) {
				if (players[high].getNumHits() < players[i].getNumHits()) {
					high = i;
				}
			}
			return players[high];
		}
		if (stat.equals("errors") == true) {
			int low = 0;
			for (int i = 0; i < size(); i++) {
				if (players[low].getNumErrors() > players[i].getNumErrors()) {
					low = i;
				}
			}
			return players[low];
		} else {
			return null;
		}
	}

	/**
	 * Prints a formatted table of each Player on the team in their own line
	 * in order of their position along with their name and statistics.
	 * 
	 * @precondition
	 *    The team must be instantiated
	 *    
	 * @postconditions
	 *    A formatted table with each player on their own line with their
	 *    name and respective statistics
	 */
	public void printAllPlayers() {
		System.out.println(toString());
	}

	/**
	 * Returns a string representation of the formatted table of each Player 
	 * on the team in their own line in order of their position along with 
	 * their name and statistics.
	 * 
	 * @returns
	 *    The String interpretation of the Team Object
	 */
	public String toString() {
		System.out.println();
		String output = "";
		output += String.format("%-10s%-19s%-11s%-7s", "Player#", "Name", 
		  "Hits", "Errors");
		output += "\n";
		output += "------------------------------------------------";
		output += "\n";
		for (int i = 0; i < size(); i++) {
			output += String.format("%-10s%-19s%-11s%-7s", i + 1, 
			  players[i].getName(), players[i].getNumHits(), 
			  players[i].getNumErrors());
			output += "\n";
		}
		return output;
	}

}
