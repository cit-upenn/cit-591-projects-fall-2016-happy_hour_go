/**
 * 
 */
package search;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dataPrep.Parser;
import util.FileReader;

/**
 * This class based on user input time, output the bars that are currently on Happy Hour
 * @author JillGao
 *
 */
public class BarFinder {
	private ArrayList<Bar> bars;
	private Calendar now;
	/**
	 * Constructor
	 */
	public BarFinder(Calendar now, BarData bd) {
		this.now = now;
		this.bars = bd.getBars();
	}
	/**
	 * This method find bars that are currently on happy hour
	 * @return ArrayList of bars satisfying current 
	 */
	public ArrayList<Bar> find() {
		ArrayList<Bar> currentHHBars = new ArrayList<>();
		Date currentTime = now.getTime();
		for (Bar bar : bars) {
			Date startTime = convertTimeString(bar.getStartTimeString());
			Date endTime = convertTimeString(bar.getEndTimeString());
			
			if (currentTime.after(startTime) && currentTime.after(endTime)) {
				currentHHBars.add(bar);
			}
		}
		return currentHHBars;
	}
	/**
	 * This helper method obtains user input time, and link with generic happy hour
	 * @return an ArrayList containing Date objects
	 */
	private Date convertTimeString(String timeString) {
		Date timeDate = null;
		// time in format "4:00 pm"
		String month = Integer.toString(now.MONTH);
		String day = Integer.toString(now.DAY_OF_MONTH);
		String year = Integer.toString(now.YEAR);
		String hour = timeString.substring(0, timeString.length() - 2);
		
		// building the dateString in format "MM/dd/yyyy HH:mm"
		StringBuilder sb = new StringBuilder();
		sb.append(month);
		sb.append("/");
		sb.append(day);
		sb.append("/");
		sb.append(year);
		sb.append(' ');
		sb.append(hour);
		String dateString = sb.toString();
		
		// use DateFormat to create Date objects
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try
        {
            Date d = df.parse(dateString);
            timeDate = d;
        }
        catch (ParseException ex)
        {
            System.out.println("Exception " + ex);
        }
		
		return timeDate;
	}
}
