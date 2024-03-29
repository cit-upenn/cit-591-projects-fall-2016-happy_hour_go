/**
 * 
 */
package cit591hw6.search;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import cit591hw6.dataPrep.FileReader;

/**
 * This class reads in clean .csv file while contains all bars with Happy Hour on a given weekday
 * And build a data structure that contains all the bars
 * @author JillGao
 *
 */
public class BarData {
	private ArrayList<Bar> bars;
	private FileFetcher ff;
	
	/**
	 * Constructor
	 * @param fr
	 * @throws FileNotFoundException 
	 */
	public BarData (FileFetcher ff) throws FileNotFoundException {
		this.ff = ff;
		bars = getBarData();
	}
	/**
	 * This class builds BarData from FileReader reading clean .csv file
	 * @param fr
	 * @return
	 * @throws FileNotFoundException 
	 */
	private ArrayList<Bar> getBarData() throws FileNotFoundException {
		ArrayList<Bar> bars = new ArrayList<>();
		FileReader fr = ff.getFile();
		fr.readFile();
		ArrayList<String> stringBars = fr.getLines();
		
		for (String stringBar : stringBars) {
			String[] infoPieces = stringBar.split("\t");
//			System.out.println(infoPieces.length);
			String name = infoPieces[0];
			String lat = infoPieces[1];
			String lon = infoPieces[2];
			String address = infoPieces[3];
//			String phone = infoPieces[4];
			String startTime = infoPieces[4];
			String endTime = infoPieces[5];
			String description = infoPieces[6];
			
			Bar bar = new Bar(name, lat, lon, address, startTime, endTime, description);
			bars.add(bar);
		}
		return bars;
	}
	/**
	 * This method returns an ArrayList of bars construct from clean data
	 * @return
	 */
	public ArrayList<Bar> getBars() {
		return bars;
	}
}
