package dataPrep;

import java.util.ArrayList;

/**
 * Represent a bar. Properties are public for easy accessing. 
 * @author Han Zhu
 *
 */
public class Bar {
	public String name;
	public String address;
	public String phone;
	public ArrayList<String> startTime;
	public ArrayList<String> endTime;
	public ArrayList<String> description;
	
	public Bar(String name, String address, String phone, ArrayList<String> startTime, ArrayList<String> endTime, ArrayList<String> description) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
	}
}
