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
//		Date currentTime = now.getTime();
		Date currentTime = convertTimeString(bars.get(2).getStartTimeString());  //for test
		System.out.println(currentTime);
		for (Bar bar : bars) {
			Date startTime = convertTimeString(bar.getStartTimeString());
			Date endTime = convertTimeString(bar.getEndTimeString());

			if (currentTime.after(startTime) && currentTime.before(endTime)) {
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
		String month = Integer.toString(now.get(Calendar.MONTH) + 1);
		int dayVal = now.get(Calendar.DAY_OF_MONTH);
		String day = Integer.toString(dayVal);
		String year = Integer.toString(now.get(Calendar.YEAR));

		// check am/pm for correct 24h conversion
		int len = timeString.length();
		String hour = timeString;
		if (hour.equals("12:00 am")) {
			dayVal = dayVal == 7? 1 : dayVal + 1;
			day = Integer.toString(dayVal);
			hour = "00:00";
		} else if (hour.charAt(len - 2) == 'a') {
			hour = timeString.substring(0, len - 3);
		} else {
			hour = converTo24h(hour);
		}
		
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
		try {
            Date d = df.parse(dateString);
            timeDate = d;
//          System.out.println(timeDate);
        } catch (ParseException ex) {
            System.out.println("Exception " + ex);
        }
		
		return timeDate;
	}
	
	/**
	 * This method convert the format of pm time string i.e. "4:00 pm" into 24h time string i.e. "16:00"
	 * @param hour
	 * @return String
	 */
	private String converTo24h(String hour) {
		String hour24 = "";
		String[] h = hour.split(":");
		int hourInt = Integer.parseInt(h[0]);
		String hourPart = Integer.toString(hourInt + 12);
		
		StringBuilder sb = new StringBuilder();
		sb.append(hourPart);
		sb.append(":00");
		
		hour24 = sb.toString();
		return hour24;
	}
}
