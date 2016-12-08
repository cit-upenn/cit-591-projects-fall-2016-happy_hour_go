package mapMaker;

import java.util.ArrayList;

import search.Bar;

/**
 * get the data of bar's coordinate and happy hour information and send it to mapTester to display.
 * @author Jiahui, JillGao
 *
 */
public class DataSender {
	private ArrayList<Double> addrLon = new ArrayList<Double>();
	private ArrayList<Double> addrLat = new ArrayList<Double>();
	private ArrayList<String> names = new ArrayList<String>();
	private ArrayList<String> startTime = new ArrayList<>();
	private ArrayList<String> endTime = new ArrayList<>();
	private ArrayList<String> description = new ArrayList<String>();
	
//	FileReader fr = new FileReader("./data/clean/monday.csv");
		
	/**
	 * Constructor: iterates an arraylist of bars, get each bar's fields and store in instance variables.
	 * @param hhResult the search result of current happy hours.
	 */
	public DataSender(ArrayList<Bar> hhResult){		
		for (Bar b : hhResult) {
			double lon = Double.parseDouble(b.getLon());
			double lat = Double.parseDouble(b.getLat());
			String name = b.getName();

			addrLon.add(lon);
			addrLat.add(lat);
			names.add(name);
			
			String start = b.getStartTime();
			String end = b.getEndTime();
			startTime.add(start);
			endTime.add(end);
			
			String desc = b.getDescription();
			description.add(desc);
			
//			System.out.println(message);
		}
	}

	public ArrayList<Double> getAddrLon() {
		return addrLon;
	}

	public ArrayList<Double> getAddrLat() {
		return addrLat;
	}

	public ArrayList<String> getName() {
		return names;
	}

	public ArrayList<String> getStartTime() {
		return startTime;
	}

	public ArrayList<String> getEndTime() {
		return endTime;
	}

	public ArrayList<String> getDescription() {
		return description;
	}
}
