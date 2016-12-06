package mapMaker;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import search.Bar;
import util.FileReader;

/**
 * get the data of bar's coordinate and happy hour information and send it to mapTester to display.
 * @author Jiahui, JillGao
 *
 */
public class DataSender {
	private ArrayList<Double> addrLon = new ArrayList<Double>();
	private ArrayList<Double> addrLat = new ArrayList<Double>();
	private ArrayList<String> names = new ArrayList<String>();
	private ArrayList<String> Description = new ArrayList<String>();
	
//	FileReader fr = new FileReader("./data/clean/monday.csv");
		
	public DataSender(ArrayList<Bar> hhResult){		
		for (Bar b : hhResult) {
			double lon = Double.parseDouble(b.getLon());
			double lat = Double.parseDouble(b.getLat());
			String name = b.getName();

			addrLon.add(lon);
			addrLat.add(lat);
			names.add(name);
			
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
}
