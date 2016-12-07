package dataPrep;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yelp.YelpAPI;

/**
 * This class uses Yelp API to query business information. The geocodes of the businesses are written to a file.
 * @author Han Zhu
 *
 */
public class Geocoder {
	/**
	 * Read in unique-bars.csv for bar names. Use bar names to query Yelp API for their geocodes. Write the results to the same file. 
	 */
	public ArrayList<String> geocode(String barName) {		
		ArrayList<String> geocodes = new ArrayList<>();
		
		Pattern lat = Pattern.compile("\"latitude\": (.*?),");
		Pattern lon = Pattern.compile("\"longitude\": (.*?)}");
			
		try {
			String result = YelpAPI.search1(barName);
			
			Matcher latM = lat.matcher(result);
			Matcher lonM = lon.matcher(result);
				
			if (latM.find()) {
				geocodes.add(latM.group(1));
			} else {
				geocodes.add("no lat found!");
			}
			if (lonM.find()) {
				geocodes.add(lonM.group(1));
			} else {
				geocodes.add("no lon found!");
			}
		} catch (IndexOutOfBoundsException ioobe) {
			geocodes.add(barName);
			geocodes.add("no business found!");
		}
		return geocodes;
	}
}
