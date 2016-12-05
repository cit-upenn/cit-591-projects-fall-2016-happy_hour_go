package search;

import java.io.FileNotFoundException;
import java.util.Calendar;

public class SearchTester {

	public static void main(String[] args) throws FileNotFoundException {
		FileFetcher ff = new FileFetcher(2);
		BarData bd = new BarData(ff);
		Calendar now = Calendar.getInstance();
		
		BarFinder bf = new BarFinder(now, bd);
		int count = 0;
		
		for (Bar bar : bf.find()) {
			System.out.print(bar.name + ' ' + bar.address + ' ' + bar.lat + ' ' + bar.lon + ' ' + bar.startTimeString + ' ' + bar.endTimeString
					+ ' ' + bar.descriptionString + '\n');
			count++;
		}
		System.out.println(count);
	}

}
