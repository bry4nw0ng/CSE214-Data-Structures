/**
 * The Intersection class represents a crossing of two or more roads at a stop
 * light in our simulation. The class consists of an array of TwoWayRoad 
 * objects representing the crossing roads, as well as a countdown timer and a
 * light index. Intersection contains the following private member variables: 
 * roads (TwoWayRoad[]), lightIndex (int), and countdownTimer (int). The 
 * Intersection class also features the following public methods: timeStep(),
 *  and void enqueueVehicle(int roadIndex, int wayIndex, int laneIndex), 
 *  as well as a display() method which prints the intersection to the 
 *  terminal.
 *  
 *  @author Bryan Wong
 * 		SBUID#115226034
 * 		Recitation#04
 */
import java.util.Arrays;
import java.util.List;

public class Intersection {
	private TwoWayRoad[] roads; // Array of roads which cross at intersection
	private int lightIndex; // indicates which road has the active light
	private int countdownTimer; // Tracks remaining time for lightIndex road
	
	private static final int MAX_ROADS = 4;
	
	/**
	 * Default constructor which initializes the roads array
	 * 
	 * @param initRoads
	 *    Array of roads to be used by this intersection
	 *    
	 * @precondition
	 *    initRoads is not null
	 *    Length of initRoads is less than equal to MAX_ROADS
	 *    All indices of initRoads are not null
	 *    
	 * @throws IllegalArgumentException
	 *    if initRoads is null
	 *    if any index of initRoads is null
	 *    initRoads.length > MAX_ROADS
	 */
	public Intersection(TwoWayRoad[] initRoads) throws IllegalArgumentException {
        if (initRoads == null || initRoads.length > MAX_ROADS) {
            throw new IllegalArgumentException("roads array is null or is greater than max roads");
        }
        for (int i = 0; i < initRoads.length; i++) {
            if (initRoads[i] == null) {
                throw new IllegalArgumentException("road null at index " + i);
            }
        }
        
        roads = initRoads;
        lightIndex = 0;
        countdownTimer = roads[lightIndex].getGreenTime();
	}
	
	/**
	 * Returns the number of roads at the intersection
	 * 
	 * @return
	 *    Returns the number of roads at the intersection as an Integer
	 */
	public int getNumRoads() {
		return roads.length;
	}
	
	/**
	 * Returns the light index at the intersection
	 * 
	 * @return
	 *    Returns the light index at the intersection as an Integer
	 */
	public int getLightIndex() {
		return lightIndex;
	}
	
	/**
	 * Returns the countdownTimer at the intersection
	 * 
	 * @return
	 *    Returns the countdownTimer at the intersection as an Integer
	 */
	public int getCountdownTimer() {
		return countdownTimer;
	}
	
	/**
	 * Returns the current Light Value at one of the roads in the intersection
	 * 
	 * @return
	 *    Returns the current light value at one of the roads in the 
	 *    intersection as a Light Value enum.
	 */
	public LightValue getCurrentLightValue() {
		return roads[lightIndex].getLightValue();
	}
	
	/**
	 * Returns the name of the road that has the current green light
	 * 
	 * @return
	 *    Returns the name of the road as a String
	 */
	public String getGreenLight() {
		return roads[lightIndex].getName();
	}
	
	/**
	 * Returns the instance of the road in the intersection
	 * 
	 * @param road
	 *    The road index
	 *    
	 * @return
	 *    Returns the certain instance of a road at the indicated index
	 */
	public TwoWayRoad getRoad(int road) {
		return roads[road];
	}
	
	public int getVehicleCount() {
		int vehicleCount = 0;
		for (int i = 0; i < getNumRoads(); i++) {
			vehicleCount += roads[i].getVehicleCount();
		}
		return vehicleCount;
	}
	
	public Vehicle[] timeStep() {
		for (int i = 0; i < roads.length; i++) {
			if (i != lightIndex) {
				roads[i].setRedLight();;
			}
		}
		TwoWayRoad current = roads[lightIndex];
		
		Vehicle[] passedVehicles = current.proceed(countdownTimer);
		
		countdownTimer--;
		if (countdownTimer == 0) {
			lightIndex++;
			if (lightIndex == roads.length) {
				lightIndex = 0;
			}
			countdownTimer = roads[lightIndex].getGreenTime();
		}
		
		return passedVehicles;
	}
	
	public void enqueueVehicle(int roadIndex, int wayIndex, int laneIndex, Vehicle vehicle) throws IllegalArgumentException {
		if (vehicle == null) {
			throw new IllegalArgumentException("Vehicle is null");
		}
		if (roadIndex < 0 || roadIndex >= roads.length || wayIndex < 0 || wayIndex >= 2 || laneIndex < 0 || laneIndex >= 3) {
			throw new IllegalArgumentException("invalid parameters");
		}
		else {
			roads[roadIndex].enqueueVehicle(wayIndex, laneIndex, vehicle);
		}
	}
	
	public void display() {
		for (int i = 0; i < getNumRoads(); i++) {
			List<String> idList = Arrays.asList(roads[i].display());
			List<String> lightList = Arrays.asList(roads[i].displayLightValue());
			System.out.println(roads[i].getName() + ":");
			System.out.println(String.format("%30s%-15s%-31s", "FORWARD", "               ", "BACKWARD"));
			System.out.println("==============================               ===============================");
			System.out.println(String.format("%30s%-15s%-31s", idList.get(0), lightList.get(0), idList.get(5)));
			System.out.println("------------------------------               -------------------------------");
			System.out.println(String.format("%30s%-15s%-31s", idList.get(1), lightList.get(1), idList.get(4)));
			System.out.println("------------------------------               -------------------------------");
			System.out.println(String.format("%30s%-15s%-31s", idList.get(2), lightList.get(2), idList.get(3)));
			System.out.println();
		}
	}
	
	public boolean isEmpty() {
		for (int i = 0; i < roads.length; i++) {
			for (int j = 0; j < roads[i].NUM_WAYS; j++) {
				for (int k = 0; k < roads[i].NUM_LANES; k++) {
					if (roads[i].isLaneEmpty(j, k) == false) {
						return false;
					}
				}
			}
		}
		return true;
	}
}

