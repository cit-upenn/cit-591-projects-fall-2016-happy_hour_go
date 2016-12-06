package search;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
/* Obsolete */
public class SearchTester {
	

	public static void main(String[] args) throws FileNotFoundException {
		Calendar now = Calendar.getInstance();
		FileFetcher ff = new FileFetcher(now.DAY_OF_WEEK);
		BarData bd = new BarData(ff);
		BarFinder bf = new BarFinder(now, bd);
		ArrayList<Bar> searchResult = bf.find();
		
		int count = 0;

		for (Bar bar : searchResult) {
			System.out.print(bar.name + ' ' + bar.address + ' ' + bar.lat + ' ' + bar.lon + ' ' + bar.startTimeString + ' ' + bar.endTimeString
					+ ' ' + bar.descriptionString + '\n');
			count++;
		}
		System.out.println(count);
	}

}
