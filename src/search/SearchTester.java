package search;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;

import mapMaker.DataSender;

public class SearchTester {

	public static void main(String[] args) throws FileNotFoundException {
		FileFetcher ff = new FileFetcher(1);
		BarData bd = new BarData(ff);
		Calendar now = Calendar.getInstance();
		
		BarFinder bf = new BarFinder(now, bd);
		ArrayList<Bar> HHResult = bf.find();
//		System.out.println(HHResult);
		
		DataSender ds = new DataSender (HHResult);
	}

}
