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
	ArrayList<Bar> bars = new ArrayList<>();
	Calendar now;
	int dayOfWeek;
	int hourOfDay;
	/**
	 * Constructor
	 */
	public BarFinder(Calendar now) {
		this.now = now;
		this.dayOfWeek = now.DAY_OF_WEEK;
		this.hourOfDay = now.HOUR_OF_DAY;
		FileFetcher ff = new FileFetcher(dayOfWeek);
		FileReader fr = ff.getFile();
		Parser p = new Parser(fr.getBars());
		bars = p.getBars();	
	}
	/**
	 * This method find bars that are currently on happy hour
	 * @return ArrayList of bars satisfying current 
	 */
	public ArrayList<Bar> find() {
		ArrayList<Bar> currentHHBars = new ArrayList<>();
		Date currentTime = now.getTime();
		for (Bar bar : bars) {
			ArrayList<Date> startTimes = convertTimeString(bar.getStartTime());
			ArrayList<Date> endTimes = convertTimeString(bar.getEndTime());
			for (int i = 0; i < startTimes.size(); i++) {
				// If current time is after a start time, check if it's before it's corresponding end time
				if (currentTime.after(startTimes.get(i)) && currentTime.before(endTimes.get(i))) {
					currentHHBars.add(bar);
				}
			}
		}
		return currentHHBars;
	}
	/**
	 * This helper method obtains user input time, and link with generic happy hour
	 * @return an ArrayList containing Date objects
	 */
	private ArrayList<Date> convertTimeString(ArrayList<String> times) {
		ArrayList<Date> result = new ArrayList<Date>();
		
		
		for (String time : times) {
			
			// time in format "4:00 pm"
			String month = Integer.toString(now.MONTH);
			String day = Integer.toString(now.DAY_OF_MONTH);
			String year = Integer.toString(now.YEAR);
			String hour = time.substring(0, time.length() - 3);
			
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
	            result.add(d);
	        }
	        catch (ParseException ex)
	        {
	            System.out.println("Exception " + ex);
	        }
		}
		return result;
	}
}
