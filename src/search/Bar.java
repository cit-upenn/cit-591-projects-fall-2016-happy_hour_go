package search;

import java.util.ArrayList;

/**
 * Represent a bar. Properties are public for easy accessing. 
 * @author Han Zhu, Jill Gao
 *
 */
public class Bar {
	public String name;
	public String lat;
	public String lon;
	public String address;
	public ArrayList<String> startTime;
	public ArrayList<String> endTime;
	public ArrayList<String> description;
	
	public String startTimeString;
	public String endTimeString;
	public String descriptionString;

	
	public Bar(String name, String lat, String lon, String address, ArrayList<String> startTime, ArrayList<String> endTime, 
			ArrayList<String> description) {
		this.name = name;
		this.lat = lat;
		this.lon = lon;
		this.address = address;
//		this.phone = phone;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
	}
	
	public Bar(String name, String address, ArrayList<String> startTime, ArrayList<String> endTime, ArrayList<String> description) {
		this.name = name;
		this.address = address;
//		this.phone = phone;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
	}
	
	public Bar(String name, String lat, String lon, String address, String startTimeString, String endTimeString, String description) {
		this.name = name;
		this.lat = lat;
		this.lon = lon;
		this.address = address;
//		this.phone = phone;
		this.startTimeString = startTimeString;
		this.endTimeString = endTimeString;
		this.descriptionString = description;
	}

	public String getName() {
		return name;
	}

	public String getLat() {
		return lat;
	}

	public String getLon() {
		return lon;
	}

	public String getAddress() {
		return address;
	}

//	public String getPhone() {
//		return phone;
//	}

	public ArrayList<String> getStartTime() {
		return startTime;
	}

	public ArrayList<String> getEndTime() {
		return endTime;
	}

	public ArrayList<String> getDescription() {
		return description;
	}
	
	public String getStartTimeString() {
		return startTimeString;
	}
	
	public String getEndTimeString() {
		return endTimeString;
	}

	public String getDescriptionString() {
		return descriptionString;
	}
}
