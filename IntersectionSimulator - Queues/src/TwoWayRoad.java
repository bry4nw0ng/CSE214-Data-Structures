/**
 * The TwoWayRoad class represents one of the roads in the simulation's 
 * intersection. Each road is bi-directional each with three lanes: 
 * left, middle, and right. Each lane is modeled by a VehicleQueue which 
 * is stored within a 2D array where the first index models the direction
 * and the second index models the lane. The class has an isEmpty() method
 * that checks if the Road is empty or not. It can also add Vehicle Objects 
 * using enqueue(). The proceed method returns the Vehicle objects that are
 * passed through. . 
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 * 		Recitation#04
 */

import java.util.ArrayList;
import java.util.Stack;

public class TwoWayRoad {
	public final int FORWARD_WAY = 0;
	public final int BACKWARD_WAY = 1;
	public final int NUM_WAYS = 2;
	
	public final int LEFT_LANE = 0;
	public final int MIDDLE_LANE = 1;
	public final int RIGHT_LANE = 2;
	public final int NUM_LANES = 3;
	
	private String name; // Name of the road
	private int greenTime; // maximum total number of steps road can be active
	private int leftSignalGreenTime; // number of steps road remain LEFT_SIGNAL
	
	private VehicleQueue lanes[][];
	private LightValue lightValue;
	
	/**
	 * Default constructor that returns an instance of the TwoWayRoad class
	 * Initializes the array and all of its member objects as well as the 
	 * leftSignalGreenTime to 1.0/NUM_LANES * initGreenTime
	 * 
	 * @param initName
	 *    The name of the road
	 *    
	 * @param initGreenTime
	 *    The amount of time that the light will be active for this particular
	 *    road. This is the total of the time the light should display green 
	 *    for cars going forward/turning right, as well as for cars going left
	 *    
	 * @precondition
	 *    initGreenTime > 0	
	 *    
	 * @postcondition
	 *    the road is initialized with all lanes initialized to empty queues, 
	 *    and all instance variables initialized 
	 *    
	 * @throws IllegalArgumentException
	 *    initGreenTime <= 0 or initName == null
	 */
	public TwoWayRoad(String initName, int initGreenTime) 
			throws IllegalArgumentException {
		if (initGreenTime <= 0 || initName == null) {
			throw new IllegalArgumentException("initGreenTime is not greater "
					+ "than 0 or initName is null");
		}
		else {
			name = initName;
			greenTime = initGreenTime;
			leftSignalGreenTime = (int) Math.floor(1.0/NUM_LANES 
					* initGreenTime);
			lanes = new VehicleQueue[NUM_WAYS][NUM_LANES];
			for (int i = 0; i < lanes.length; i++) {
				for (int j = 0; j < lanes[i].length; j++) {
					lanes[i][j] = new VehicleQueue();
				}
			}
		}
	}
	
	/**
	 * Returns the name of the TwoWayRoad
	 * 
	 * @return
	 *    Returns the name of the road as a String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the Green Time
	 * 
	 * @return
	 *    Returns the Green Time of the road as an integer
	 */
	public int getGreenTime() {
		return greenTime;
	}
	
	/**
	 * Returns the Light Value of the road
	 * 
	 * @return
	 *    Returns the light value of the road as a LightValue enum
	 */
	public LightValue getLightValue() {
		return lightValue;
	}
	
	/**
	 * Executes the passage of time in the simulation. The timerVal 
	 * represents the current value of a countdown timer counting down 
	 * total green time steps. The light should be in state GREEN any time 
	 * the timerval is greater than leftSignalGreenTime. When timerVal is 
	 * less than or equal to leftSignalGreenTime, the light should change to 
	 * LEFT_SIGNAL. After the execution of timerVal == 1, or if there are no 
	 * vehicles left the light should change state to RED.
	 * 
	 * @param timerVal
	 *    The current timer value, determines the state of the light
	 *    
	 * @precondition
	 *    The TwoWayRoad object should be instantiated
	 *    
	 * @return
	 *    An array of Vehicles that has been dequeued during this time step
	 *    
	 * @postcondition
	 *    Any vehicles that should have been dequeued during this time step
	 *    should be dequeued and placed in the return array
	 *    
	 * @throws IllegalArgumentException
	 *    timerval <= 0
	 */
	public Vehicle[] proceed(int timerVal) throws IllegalArgumentException {
		if (timerVal <= 0) {
			throw new IllegalArgumentException("Timer val is not greater "
					+ "than 0");
		}
		else {
			ArrayList<Vehicle> dequeuedVehicles = new ArrayList<Vehicle>();

			if (timerVal > leftSignalGreenTime) {
				lightValue = LightValue.GREEN;
			}
			
			else if (timerVal <= leftSignalGreenTime){
				lightValue = LightValue.LEFT_SIGNAL;
			}
			
			if (lightValue == LightValue.GREEN) {
				if (lanes[FORWARD_WAY][MIDDLE_LANE].peek() != null) {
					dequeuedVehicles.add
					(lanes[FORWARD_WAY][MIDDLE_LANE].dequeue());
				}
				if (lanes[FORWARD_WAY][RIGHT_LANE].peek() != null) {
					dequeuedVehicles.add
					(lanes[FORWARD_WAY][RIGHT_LANE].dequeue());		
				}
				if (lanes[BACKWARD_WAY][MIDDLE_LANE].peek() != null) {
					dequeuedVehicles.add
					(lanes[BACKWARD_WAY][MIDDLE_LANE].dequeue());
				}
				if (lanes[BACKWARD_WAY][RIGHT_LANE].peek() != null) {
					dequeuedVehicles.add
					(lanes[BACKWARD_WAY][RIGHT_LANE].dequeue());
				}
			}
			
			else if (lightValue == LightValue.LEFT_SIGNAL) {
				if (lanes[FORWARD_WAY][LEFT_LANE].peek() != null) {
					dequeuedVehicles.add
					(lanes[FORWARD_WAY][LEFT_LANE].dequeue());
				}
				if (lanes[BACKWARD_WAY][LEFT_LANE].peek() != null) {
					dequeuedVehicles.add
					(lanes[BACKWARD_WAY][LEFT_LANE].dequeue());
				}
			}
			
			else if (timerVal == 1 || noVehiclesLeft()) {
				lightValue = LightValue.RED;
			}
			
			return dequeuedVehicles.toArray
					(new Vehicle[dequeuedVehicles.size()]);
		}
	}
	
	/**
	 * Enqueues a vehicle into a specificied lane
	 * 
	 * @param wayIndex
	 *    The direction the car is going in
	 *    
	 * @param laneIndex
	 *    The lane the car arrives in
	 *    
	 * @param vehicle
	 *    The vehicle to enqueue, must not be null
	 *    
	 * @precondition
	 *    The TwoWayRoad should be instantiated 
	 *    
	 * @postcondition
	 *    Given that the vehicle specified was not null, and the position 
	 *    given was not invalid (and no exception was thrown), the vehicle 
	 *    should be added to the end of the proper queue.
	 *    
	 * @throws IllegalArgumentException
	 *    If wayIndex &gt 1 || wayIndex &lt 0 || laneIndex &lt 0 || 
	 *    laneIndex &gt 2 or vehicle==null
	 */
	public void enqueueVehicle(int wayIndex, int laneIndex, Vehicle vehicle)
			throws IllegalArgumentException {
		if (wayIndex > 1 || wayIndex < 0 || laneIndex < 0 || laneIndex > 2 
				|| vehicle == null) {
			throw new IllegalArgumentException("illegal vehicle enqueue "
					+ "parameter");
		}
		else {
			lanes[wayIndex][laneIndex].enqueue(vehicle);
		}
	}
	
	/**
	 * Checks if a specified lane is empty
	 * 
	 * @param wayIndex
	 *    The direction of the lane
	 *    
	 * @param laneIndex
	 *    The index of the lane to check
	 *    
	 * @return
	 *    true if the lane is empty, else false
	 *    
	 * @postcondition
	 *    The TwoWayRoad object should remain unchanged
	 * @throws IllegalArgumentException
	 */
	public boolean isLaneEmpty(int wayIndex, int laneIndex) 
			throws IllegalArgumentException {
		if (wayIndex > 1 || wayIndex < 0 || laneIndex < 0 || laneIndex > 2) {
			throw new IllegalArgumentException("cannot check if lane is empty "
					+ "due to illegal parameters");
		}
		else {
			return lanes[wayIndex][laneIndex].isEmpty();
		}
	}
	
	/**
	 * Returns the number of vehicles in the road
	 * 
	 * @return
	 *    Returns the number of vehicles in the road as an Integer
	 */
	public int getVehicleCount() {
		int vehicleCount =0;
		for (int i = 0; i < NUM_WAYS; i++) {
			for (int j = 0; j < NUM_LANES; j++) {
				vehicleCount += lanes[i][j].size();
			}
		}
		return vehicleCount;
	}
	
	/**
	 * Returns true or false if the road is entirely empty
	 * 
	 * @return
	 *    Returns true if the road is empty, else false
	 */
	public boolean noVehiclesLeft() {
		for (int i = 0; i < lanes.length; i++) {
			for (int j = 0; j < lanes[i].length; j++) {
				if (lanes[i][j].isEmpty() == false) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Displays the serial IDs of the vehicles on the road in order
	 * depending on the direction of the road.
	 * 
	 * @return
	 *    A String array containing the the list of vehicle Serial ID on 
	 *    the road
	 */
	public String[] display() {
		ArrayList<String> output = new ArrayList<String>();
		
		for (int i = 0; i < lanes[FORWARD_WAY].length; i++) {
			if (lanes[FORWARD_WAY][i] == null) {
				output.add(" ");
			}
			else {
				String input = "";
				Stack<Vehicle> flip = new Stack<Vehicle>();
				Stack<Vehicle> flop = new Stack<Vehicle>();
				
				while(lanes[FORWARD_WAY][i].isEmpty() == false) {
					flip.push(lanes[FORWARD_WAY][i].dequeue());
				}
				while(flip.isEmpty() == false) {
					input += "[" + flip.peek().getSerialID() + "]";
					flop.push(flip.pop());
				}
				while(flop.isEmpty() == false) {
					lanes[FORWARD_WAY][i].enqueue(flop.pop());
				}
				output.add(input);
			}
		}
		
		for (int i = 0; i < lanes[BACKWARD_WAY].length; i++) {
			if (lanes[BACKWARD_WAY][i] == null) {
				output.add(" ");
			}
			else {
				String input = "";
				VehicleQueue temp = new VehicleQueue();
				
				while(lanes[BACKWARD_WAY][i].isEmpty() == false) {
					input += "[" + lanes[BACKWARD_WAY][i].peek().getSerialID()
							+ "]";
					temp.enqueue(lanes[BACKWARD_WAY][i].dequeue());
				}

				while(temp.isEmpty() == false) {
					lanes[BACKWARD_WAY][i].enqueue(temp.dequeue());
				}
				
				output.add(input);
			}
		}
		
		return output.toArray(new String[output.size()]);
	}
	
	/**
	 * Displays the current state of the light depending on the LightValue
	 * 
	 * @return
	 *    Returns a string array of the current state of the light on each 
	 *    lane depending on the LightValue
	 */
	public String[] displayLightValue() {
		ArrayList<String> output = new ArrayList<String>();
		switch (getLightValue()) {
			case GREEN:
				output.add(" [L] x     [R] ");
				output.add(" [M]       [M] ");
				output.add(" [R]     x [L] ");
				break;
			case LEFT_SIGNAL:
				output.add(" [L]     x [R] ");
				output.add(" [M] x   x [M] ");
				output.add(" [R] x     [L] ");
				break;
			case RED:
				output.add(" [L] x   x [R] ");
				output.add(" [M] x   x [M] ");
				output.add(" [R] x   x [L] ");
				break;
		}
		
		return output.toArray(new String[output.size()]);
	}
	
	/**
	 * Sets the current state of the LightValue to RED
	 */
	public void setRedLight() {
		lightValue = LightValue.RED;
	}
}
