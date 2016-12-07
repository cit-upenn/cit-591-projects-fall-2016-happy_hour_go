package dataPrep;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.FileReader;
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
	public static void geocode() {
		try {
			FileReader fr = new FileReader("data/clean/unique-bars.csv");
			fr.readFile();
			System.out.println(fr.getLines().size());
			
			Pattern lat = Pattern.compile("\"latitude\": (.*?),");
			Pattern lon = Pattern.compile("\"longitude\": (.*?)}");
			
			YelpAPI.search2("Hawthorne's Biercafe");
			
			PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream("data/clean/unique-bars.csv", true)));
			
			for (int i = 0; i < fr.getLines().size(); i++) {
				String bar = fr.getLines().get(i);
				String[] barFields = bar.split("\t");
				
				try {
					String result = YelpAPI.search1(barFields[2]);
					
					Matcher latM = lat.matcher(result);
					Matcher lonM = lon.matcher(result);
					
					StringBuilder sb = new StringBuilder();
					
					if (latM.find()) {
						sb.append(latM.group(1));
					} else {
						sb.append("no lat found!");
					}
					if (lonM.find()) {
						sb.append('\t' + lonM.group(1));
					} else {
						sb.append("\tno lon found!");
					}
					out.println(sb.toString());
				} catch (IndexOutOfBoundsException ioobe) {
					out.println(barFields[2] + "\tno business found!");
				}
			}
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		geocode();
	}
}
