import cit591hw6.search.Bar;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * This class tests the Bar Class
 * @author JillGao
 *
 */
public class BarTest {
	private Bar bar;
	
	private String name;
	private String lat;
	private String lon;
	private String address;
	private String startTime;
	private String endTime;
	private String description;
	
	@Before
	public void setUpBar() {
		name = "Standard Tap";
		lat = "39.96414";
		lon = "-75.1404699";
		address = "901 N 2nd Street";
		startTime = "4:00 pm";
		endTime = "6:00 pm";
		description = "1/2 off weekly featured cocktail (rotating), 1/2 off weekly featured brewery beers (rotating), "
				+ "discount happy hour snacks";
		
		bar = new Bar(name, lat, lon, address, startTime, endTime, description);
	}
	
	@Test
	public void testGetBarName() {
		
		String barName = bar.getName();
		
		assertEquals("Bar name is", "Standard Tap", barName);
	}
	
	@Test
	public void testGetBarLat() {
		String barLat = bar.getLat();
		String barLon = bar.getLon();
		
		assertEquals("Bar Lat is", "39.96414", barLat);
		assertEquals("Bar Lon is", "-75.1404699", barLon);
	}
	
	@Test
	public void testGetAddr() {
		String address = bar.getAddress();
		
		assertEquals("Bar address is", "901 N 2nd Street", address);
	}
	
	@Test
	public void testGetTime() {
		String startTime = bar.getStartTime();
		String endTime = bar.getEndTime();
		
		assertEquals("Happy Hour startTime is: ", "4:00 pm", startTime);
		assertEquals("Happy Hour endTime is: ", "6:00 pm", endTime);
	}
	
	@Test
	public void testDescription() {
		String description = bar.getDescription();
		
		assertEquals("Happy Hour description is: ", "1/2 off weekly featured cocktail (rotating), 1/2 off weekly featured brewery beers (rotating), "
				+ "discount happy hour snacks", description);
	}
	
}
