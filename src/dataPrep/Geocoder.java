package dataPrep;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yelp.YelpAPI;

/**
 * This class uses Yelp API to query business information.
 * @author Han Zhu
 *
 */
public class Geocoder {
	
	/**
	 * Query Yelp API with restaurant name. The geocodes of the businesses are returned as an arraylist of string. 
	 * @param barName
	 * @return the coordinates of the restaurant.
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
