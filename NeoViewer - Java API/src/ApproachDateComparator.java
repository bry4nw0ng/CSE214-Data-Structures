/**
 * The AppraochDateComparator implements the Comparator interface which allow the 
 * NearEarthObjects to be arranged in sorted order based on the value of these member 
 * variables
 * 
 * 
 * @author Bryan Wong
 * 		ID#115226034
 *		Recitation#04
 */
public class ApproachDateComparator implements java.util.Comparator<NearEarthObject> {
	
	/**
	 * 
	 */
	public int compare(NearEarthObject left, NearEarthObject right) {
		return left.getClosestApproachDate().compareTo(right.getClosestApproachDate());
	}
}
