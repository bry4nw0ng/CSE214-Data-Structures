/**
 * The NearEarthObject class represents a record in the database of asteroids 
 * currently tracked by NASA. It should be noted that this class will always 
 * be constructed by the BigData library, and serves as a data container for 
 * the information that is already hosted by the NeoW API. Please refer to the
 * BigData section below for more information on how the library will use 
 * this constructor to extract NearEarthObjects out of the JSON document 
 * returned by the NASA NeoW API.
 * 
 * 
 * @author Bryan Wong
 * 		ID#115226034
 *		Recitation#04
 */
import java.util.Date;

public class NearEarthObject {
	private int referenceID;
	private String name;
	private double absoluteMagnitude;
	private double averageDiameter;
	private boolean isDangerous;
	private Date closestApproachDate;
	private double missDistance;
	private String orbitingBody;
	
	/**
	 * 
	 * @param referenceID
	 * @param name
	 * @param absoluteMagnitude
	 * @param minDiameter
	 * @param maxDiameter
	 * @param isDangerous
	 * @param closestDateTimestamp
	 * @param missDistance
	 * @param orbitingBody
	 */
	public NearEarthObject(int referenceID, String name, 
			double absoluteMagnitude, double minDiameter, double maxDiameter, 
			boolean isDangerous, long closestDateTimestamp, 
			double missDistance, String orbitingBody) {
		this.referenceID = referenceID;
		this.name = name;
		this.absoluteMagnitude = absoluteMagnitude;
		averageDiameter = (minDiameter + maxDiameter)/2;
		this.isDangerous = isDangerous;
		closestApproachDate = new Date(closestDateTimestamp);
		this.missDistance = missDistance;
		this.orbitingBody = orbitingBody;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getReferenceID() {
		return referenceID;
	}
	
	/**
	 * 
	 * @param referenceID
	 */
	public void setReferenceID(int referenceID) {
		this.referenceID = referenceID;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public double getAbsoluteMagnitude() {
		return absoluteMagnitude;
	}

	/**
	 * 
	 * @param absoluteMagnitude
	 */
	public void setAbsoluteMagnitude(double absoluteMagnitude) {
		this.absoluteMagnitude = absoluteMagnitude;
	}

	/**
	 * 
	 * @return
	 */
	public double getAverageDiameter() {
		return averageDiameter;
	}

	/**
	 * 
	 * @param averageDiameter
	 */
	public void setAverageDiameter(double averageDiameter) {
		this.averageDiameter = averageDiameter;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isDangerous() {
		return isDangerous;
	}

	/**
	 * 
	 * @param isDangerous
	 */
	public void setDangerous(boolean isDangerous) {
		this.isDangerous = isDangerous;
	}

	/**
	 * 
	 * @return
	 */
	public Date getClosestApproachDate() {
		return closestApproachDate;
	}

	/**
	 * 
	 * @param closestApproachDate
	 */
	public void setClosestApproachDate(Date closestApproachDate) {
		this.closestApproachDate = closestApproachDate;
	}

	/**
	 * 
	 * @return
	 */
	public double getMissDistance() {
		return missDistance;
	}

	/**
	 * 
	 * @param missDistance
	 */
	public void setMissDistance(double missDistance) {
		this.missDistance = missDistance;
	}

	/**
	 * 
	 * @return
	 */
	public String getOrbitingBody() {
		return orbitingBody;
	}

	/**
	 * 
	 * @param orbitingBody
	 */
	public void setOrbitingBody(String orbitingBody) {
		this.orbitingBody = orbitingBody;
	}
}
