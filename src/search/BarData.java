/**
 * 
 */
package search;

import java.util.ArrayList;

import util.FileReader;

/**
 * This class reads in clean .csv file while contains all bars with Happy Hour on a given weekday
 * And build a data structure that contains all the bars
 * @author JillGao
 *
 */
public class BarData {
	private ArrayList<Bar> bars;
	FileReader fr;
	/**
	 * Constructor
	 * @param fr
	 */
	public BarData (FileReader fr) {
		this.fr = fr;
		bars = getBarData(fr);
	}
	/**
	 * This class builds BarData from FileReader reading clean .csv file
	 * @param fr
	 * @return
	 */
	private ArrayList<Bar> getBarData(FileReader fr) {
		ArrayList<Bar> bars = new ArrayList<>();
		
		
		return bars;
	}
}
