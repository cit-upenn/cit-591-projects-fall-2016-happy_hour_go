package data;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Take in an ArrayList of String after FileReader has read a file. Parse the input and store the output as an ArrayList of Bar. 
 * @author Han Zhu
 *
 */
public class Parser {
	private ArrayList<Bar> bars;
	
	public Parser(ArrayList<String> input) {
		bars = new ArrayList<Bar>();
		parseHTML(input);
	}
	
	/**
	 * Iterate through the input ArrayList<String>, match 4 patterns with the string, and store the matches in a Bar object.
	 * @param barsHTML the original html file splitted by "barItem".
	 */
	private void parseHTML(ArrayList<String> barsHTML) {
		Pattern name = Pattern.compile("<h2>.*?> ([&\\w '-\\.]+).*</h2>");
		Pattern address = Pattern.compile("</h2>(.*?)  .*<br /");
		Pattern time = Pattern.compile("(\\d+:\\d+ [ap]m)");
		Pattern desc = Pattern.compile("</span>:(.*?)</div>");
		int count = 0;
		
		for (String item : barsHTML) {
			Matcher m = name.matcher(item);
			String barName = "";
			if (m.find()) {
				barName = m.group(1).trim();
//				System.out.println(m.group(1).trim());
//				count++;
			}

			m = address.matcher(item);
			String barAddr = "";
			if (m.find()) {
				barAddr = m.group(1).trim();
//				System.out.println(barAddr);
				count++;
			}
			
			m = time.matcher(item);
			int number = 0;
			ArrayList<String> startTime = new ArrayList<String>();
			ArrayList<String> endTime = new ArrayList<String>();
			while (m.find()) {
//				the 1st, 3rd, 5th... time is always startTime. the 2nd, 4th, 6th... time is always endTime.
				if (number % 2 == 0) {
					String hhStart = m.group(1).trim();
					startTime.add(hhStart);
				} else {
					String hhEnd = m.group(1).trim();
					endTime.add(hhEnd);
				}
				number++;
			}
//			System.out.println(startTime);
//			System.out.println(endTime);
			
			m = desc.matcher(item);
			ArrayList<String> description = new ArrayList<String>();
			while (m.find()) {
				String hhDesc = m.group(1).trim();
				description.add(hhDesc);
			}
			
			Bar bar = new Bar(barName, barAddr, startTime, endTime, description);
//			System.out.println(bar.name + "," + bar.address + "," + bar.startTime + "," + bar.endTime + "," + bar.description);
			bars.add(bar);
		}
		System.out.println(count);
	}

	public ArrayList<Bar> getBars() {
		return bars;
	}
}
