package search;

/**
 * Represent a bar. More precisely, a happy hour item. 
 * @author Han Zhu, Jill Gao
 *
 */
public class Bar {
	private String name;
	private String lat;
	private String lon;
	private String address;
	private String startTime;
	private String endTime;
	private String description;
	
	public Bar(String name, String lat, String lon, String address, String startTime, String endTime, String description) {
		this.name = name;
		this.lat = lat;
		this.lon = lon;
		this.address = address;
//		this.phone = phone;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
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
	
	public String getStartTime() {
		return startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}

	public String getDescription() {
		return description;
	}
}
