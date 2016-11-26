package MapMaker;

import java.util.ArrayList;


public class DataSender {
	ArrayList<Double> addrLon = new ArrayList<Double>();
	ArrayList<Double> addrLat = new ArrayList<Double>();
	ArrayList<String> display = new ArrayList<String>();
	
	FileReaderTester fr = new FileReaderTester("philly.map");
	
	public DataSender(){
		for(String line: fr.getLines()){
			String[] data = line.split(" ");
			Double lon = Double.parseDouble(data[0]);
			Double lat = Double.parseDouble(data[1]);
			String message = data[2];
			
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
