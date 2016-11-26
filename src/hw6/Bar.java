package hw6;

import java.util.ArrayList;

/**
 * Represent a bar. Properties are public for easy accessing. 
 * @author Han Zhu
 *
 */
public class Bar {
	public String name;
	public String address;
	public ArrayList<String> startTime;
	public ArrayList<String> endTime;
	public ArrayList<String> description;
	
	public Bar(String name, String address, ArrayList<String> startTime, ArrayList<String> endTime, ArrayList<String> description) {
		this.name = name;
//		this.neighborhood = neighborhood;
//		We don't need neighborhood data, right?
		this.address = address;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
	}
}