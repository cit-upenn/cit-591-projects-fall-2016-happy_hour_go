package search;

import java.io.FileNotFoundException;
import java.util.Calendar;

public class SearchTester {

	public static void main(String[] args) throws FileNotFoundException {
		FileFetcher ff = new FileFetcher(1);
		BarData bd = new BarData(ff);
		Calendar now = Calendar.getInstance();
		
		BarFinder bf = new BarFinder(now, bd);
		System.out.println(bf.find());
	}

}
