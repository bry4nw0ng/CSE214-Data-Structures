/**
 * The NeoDatabase class contains and manages the NearEarthObject records 
 * which have been downloaded from the online dataset.
 * @author Bryan Wong
 * 		ID#115226034
 *		Recitation#04
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import big.data.DataSource;

public class NeoDatabase {
	static final String API_KEY = 
			"RU83MhKjDKQuoAaovegz4UYHcbo9aJvefBBegDw8*";
	public static final String API_ROOT = 
			"https://api.nasa.gov/neo/rest/v1/neo/browse?";
	private final ArrayList<NearEarthObject> neoArrayList;
	
	public NeoDatabase() {
		neoArrayList = new ArrayList<>();
	}
	
	/**
	 * 
	 * @param pageNumber
	 * @return
	 * @throws IllegalArgumentException
	 */
	public String buildQueryURL(int pageNumber) 
			throws IllegalArgumentException {
		if (pageNumber > 715 || pageNumber < 0) {
			throw new IllegalArgumentException("Page number is not in valid "
					+ "range");
		}
		else {
			return API_ROOT + "page=" + pageNumber + "&api_key=" + API_KEY;
		}
	}
	
	/**
	 * 
	 * @param queryURL
	 * @throws IllegalArgumentException
	 */
	public void addAll(String queryURL) throws IllegalArgumentException {
		if (queryURL == null) {
			throw new IllegalArgumentException("QueryURL is null");
		}
		else {
			
			DataSource ds = DataSource.connectJSON(queryURL);
			ds.load();

			NearEarthObject[] neoArray = ds.fetchArray("NearEarthObject", 
					"near_earth_objects/neo_reference_id", 
					"near_earth_objects/name", 
					"near_earth_objects/absolute_magnitude_h", 
					"near_earth_objects/estimated_diameter/kilometers/estimated_diameter_min", 
					"near_earth_objects/estimated_diameter/kilometers/estimated_diameter_max", 
					"near_earth_objects/is_potentially_hazardous_asteroid", 
					"near_earth_objects/close_approach_data/epoch_date_close_approach", 
					"near_earth_objects/close_approach_data/miss_distance/kilometers", 
					"near_earth_objects/close_approach_data/orbiting_body");

			Collections.addAll(neoArrayList, neoArray);
		}
	}
	
	/**
	 * 
	 * @param comp
	 * @throws IllegalArgumentException
	 */
	public void sort(Comparator<NearEarthObject> comp) 
			throws IllegalArgumentException {
		if (comp == null) {
			throw new IllegalArgumentException("comp is null");
		}
		else {
			neoArrayList.sort(comp);
		}
	}
	
	/**
	 * 
	 */
	public void printTable() {
		String booleanTruthValue;
		
		System.out.println("  ID   |           Name            | Mag. | "
				+ "Diameter | Danger | Close Date | Miss Dist | Orbits");
		System.out.println("==================================================="
				+ "=============================================");

		for (NearEarthObject neo: neoArrayList) {
            if (neo.isDangerous()) {
                booleanTruthValue = "True";
            }
            else{ 
                booleanTruthValue = "False";
            }


            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            String simpleDateString = 
            		dateFormat.format(neo.getClosestApproachDate());

            System.out.printf("%-1s%-8d%-1s%-34s" + "%-1s%-8.2f%-1s" + 
            		"%-14.10f%-1s%-8s%-1s%-14s" + 
            		"%-1s%-18.6f%-1s%-9s", "", neo.getReferenceID(), "", 
            		neo.getName(), "", neo.getAbsoluteMagnitude(), "",
                    neo.getAverageDiameter(), "", booleanTruthValue, "", 
                    simpleDateString, "", neo.getMissDistance(), "", 
                    neo.getOrbitingBody());
            System.out.println();
        }
		
	}
}
