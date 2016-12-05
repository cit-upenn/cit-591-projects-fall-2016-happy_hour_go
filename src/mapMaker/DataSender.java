package mapMaker;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import util.FileReader;

/**
 * get the data of bar's coordinate and happy hour information and send it to mapTester to display.
 * @author Jiahui
 *
 */
public class DataSender {
	ArrayList<Double> addrLon = new ArrayList<Double>();
	ArrayList<Double> addrLat = new ArrayList<Double>();
	ArrayList<String> display = new ArrayList<String>();
	
	FileReader fr = new FileReader("./data/clean/monday.csv");
	
	public DataSender(){
		try {
			fr.readFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(String line: fr.getLines()){
			String[] data = line.split("\t");
			Double lon = Double.parseDouble(data[1]);
			Double lat = Double.parseDouble(data[2]);
//			StringBuilder message = new StringBuilder();
		     String message = data[0];
			
			addrLon.add(lon);
			addrLat.add(lat);
			display.add(message);
			
			System.out.println(message);
		}
	}

	public ArrayList<Double> getAddrLon() {
		return addrLon;
	}

	public ArrayList<Double> getAddrLat() {
		return addrLat;
	}

	public ArrayList<String> getDisplay() {
		return display;
	}
}
