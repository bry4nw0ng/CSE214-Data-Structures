/**
 * The Slide class details the contents of a player which includes its title,
 *  number of bullets along with its details, and its duration. Each slide can
 *  contain up to 5 bullet points of information.
 * 
 * @author Bryan Wong
 *		SBUID#115226034
 *		Recitation#04
 */
public class Slide {
	public static final int MAX_BULLETS = 5;
	
	private String title; // Title of Slide
	private String[] bullets; // Array containing info of each bullet point
	private double duration; // Duration of Slide
	
	/**
	 * Default constructor that returns an instance of an empty slide
	 * 
	 * @postconditions
	 *    Object has been initialized to an empty Slide where the title 
	 *    and all the bullet points are null and the duration is 0.0.
	 */
	public Slide() {
		title = null;
		bullets = new String[MAX_BULLETS];
		duration = 0.0;
	}
	
	/**
	 * Returns the title of the Slide
	 * 
	 * @return
	 *    Returns the title of the Slide as a String
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets a new title of the Slide
	 * 
	 * @param newTitle
	 *    The new title
	 * 
	 * @precondition
	 *    The new inputted title cannot be null
	 * 
	 * @throws IllegalArgumentException
	 *    Indicates that the new title is null
	 */
	public void setTitle(String newTitle) throws IllegalArgumentException {
		if (newTitle == null) {
			throw new IllegalArgumentException("The new title is null.");
		}
		else {
			title = newTitle;
		}
	}
	
	/**
	 * Returns the duration of the Slide
	 * 
	 * @return
	 *    Returns the duration of the slide as a Double
	 */
	public double getDuration() {
		return duration;
	}
	
	/**
	 * Sets a new duration of the Slide
	 * 
	 * @param newDuration
	 *    THe new duration
	 *    
	 * @precondition
	 *    The new duration is greater than 0
	 *    
	 * @throws IllegalArgumentException
	 *    Indicates that the new duration is less than or equal to 0
	 */
	public void setDuration(double newDuration) 
			throws IllegalArgumentException {
		if (newDuration > 0) {
			duration = newDuration;
		}
		else {
			throw new IllegalArgumentException("Invalid duration.");
		}
	}
	
	/**
	 * Counts the number of bullet points in the Slide
	 * 
	 * @return
	 *    Returns the number of non-null bullet points in the bullets array as
	 *    an Integer
	 */
	public int getNumBullets() {
		int count = 0;
		for (int i = 0; i < MAX_BULLETS; i++) {
			if (bullets[i] != null) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Returns the information held at a given bullet point
	 * 
	 * @param i
	 *    The index to retrieve from the bullets array
	 *    
	 * @precondition
	 *    the i index value must be greater than equal to 1 and less then or
	 *    equal to the maximum 
	 *    
	 * @return
	 *    The bullet point at the given index in the array as a String
	 * 
	 * @throws IllegalArgumentException
	 *    Indicates that the given index is not within the valid range
	 */
	public String getBullet(int i) throws IllegalArgumentException {
		if (i < 0 || i > MAX_BULLETS) {
			throw new IllegalArgumentException("index is not in valid range");
		}
		else {
			return bullets[i-1];
		}
	}
	
	/**
	 * Sets a new value for bullet the bullet point at a certain index. If 
	 * the value of the bullet is null, it erases the bullet point at the 
	 * index and pushes the greater value bullet points back.
	 * 
	 * @param bullet
	 *    The String value that will be placed in the bullets array 
	 *    
	 * @param i
	 *    The Integer value that acts as the index for the bullets array
	 *    
	 * @throws IllegalArgumentException
	 *    Indicates taht the given index is not within the valid range
	 */
	public void setBullet(String bullet, int i) 
			throws IllegalArgumentException{
		if (i < 1 || i > MAX_BULLETS) {
			throw new IllegalArgumentException("Index is not in valid range");
		}
		else {
			if (bullet == null) {
				bullets[i-1] = null;
				for (int j = i-1; j < MAX_BULLETS-1; j++) {
					bullets[j] = bullets[j+1];
				}
				bullets[MAX_BULLETS-1] = null;
			}
			else {
				int track = 0;
				while (track <= i-1) {
					if (track == i-1) {
						bullets[track] = bullet;
						break;
					}
					if (bullets[track] == null) {
						bullets[track] = bullet;
						break;
					}
					track++;
				}
			}
		}
	}
	
	/**
	 * Returns a String containing the details of a Slide
	 * 
	 * @return
	 *    Return the title, number of bullets, and duration of the Slide
	 */
	public String toString() {
		return "Title: " + title + ", Duration: " + duration + ", Bullets: " 
				+ getNumBullets();
	}
}
	
