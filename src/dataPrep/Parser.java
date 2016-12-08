package dataPrep;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Take in an ArrayList of String after FileReader has read a file. Parse the input and store the output as an ArrayList of strings. 
 * @author Han Zhu
 *
 */
public class Parser {
	private ArrayList<String> barHTMLItems;
	private ArrayList<String> happyHours;
	
	public Parser(String webpage) {
		barHTMLItems = new ArrayList<>();
		happyHours = new ArrayList<>();
		splitBars(webpage);
		parseHTML(barHTMLItems);
	}
	
	/**
	 * Split the webpage by "barItem". Ignore the first piece.
	 */
	public void splitBars(String webpage) {
		String[] splitted = webpage.split("barItem");
		
		for (int i = 1; i < splitted.length; i++) {
			barHTMLItems.add(splitted[i]);
		}
	}
	
	/**
	 * Iterate through the input ArrayList<String>, match 4 patterns with the string, and store the matches in a string delimited by tab.
	 * @param barsHTML the original html file splitted by "barItem".
	 */
	private void parseHTML(ArrayList<String> barsHTML) {
		Pattern name = Pattern.compile("<h2>.*?> ([&\\w '-\\.]+).*</h2>");
		Pattern address = Pattern.compile("</h2>(.*?)  .*<br /");
		Pattern time = Pattern.compile("(\\d+:\\d+ [ap]m)");
		Pattern desc = Pattern.compile("</span>:(.*?)</div>");
		
		for (String item : barsHTML) {
			Matcher m = name.matcher(item);
			String barName = "";
			if (m.find()) {
				barName = m.group(1).trim();
			}

			m = address.matcher(item);
			String barAddr = "";
			if (m.find()) {
				barAddr = m.group(1).trim();
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
			
			m = desc.matcher(item);
			ArrayList<String> descriptions = new ArrayList<String>();
			while (m.find()) {
				String hhDesc = m.group(1).trim();
				descriptions.add(hhDesc);
			}
			
			for (int i = 0; i < startTime.size(); i++) {
				StringBuilder sb = new StringBuilder();
				sb.append(barName + '\t' + barAddr + '\t' + startTime.get(i) + '\t' + endTime.get(i));
				
				if (descriptions.get(i).length() == 0) {
					sb.append("\tno description");
//					System.out.println(sb);
				} else {
					sb.append('\t' + descriptions.get(i));
				}

				happyHours.add(sb.toString());
			}
		}
	}

	public ArrayList<String> getHappyHours() {
		return happyHours;
	}
}
