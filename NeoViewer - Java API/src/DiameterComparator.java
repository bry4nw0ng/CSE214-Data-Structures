/**
 * The DiameterComparator implements the Comparator interface which allow the 
 * NearEarthObjects to be arranged in sorted order based on the value of these member 
 * variables
 * 
 * @author Bryan Wong
 * 		ID#115226034
 *		Recitation#04
 */
public class DiameterComparator implements java.util.Comparator<NearEarthObject> {

	/**
	 * 
	 */
	public int compare(NearEarthObject left, NearEarthObject right) {
		if (left.getAverageDiameter() == right.getAverageDiameter()) {
			return 0;
		}
		else if (left.getAverageDiameter() > right.getAverageDiameter()) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
}
