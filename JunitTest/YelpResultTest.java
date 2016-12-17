import static org.junit.Assert.*;

import org.junit.Test;


import cit591hw6.mapMaker.YelpResult;
import yelp.YelpAPI;

public class YelpResultTest {

	
	private String rating;
	private String mobile_url;
	private String rating_img_url;
	private String rating_img_url_small;
	private String url;
	private String display_phone;
	private String display_address;

	@Test
	public void testResultNotNull() {
		String result = YelpAPI.search1("Abe Fisher");
		YelpResult yelpResult = new YelpResult(result);
		assertNotNull("Result get from Yelp should not be null", yelpResult);
	}
	
	@Test
	public void testParse(){
		String result = YelpAPI.search1("Abe Fisher");
		YelpResult yelpResult = new YelpResult(result);
		
		rating = yelpResult.getRating();
		mobile_url = yelpResult.getMobile_url();
		rating_img_url = yelpResult.getRating_img_url();
		rating_img_url_small= yelpResult.getRating_img_url_small();
		url= yelpResult.getUrl();
		display_address = yelpResult.getDisplay_address();
		display_phone = yelpResult.getDisplay_phone();

		assertNotNull("rating should not be null", rating);
		assertNotNull("mobile_url should not be null", mobile_url);
		assertNotNull("rating_img_url should not be null", rating_img_url);
		assertNotNull("rating_img_url_small should not be null", rating_img_url_small);
		assertNotNull("url should not be null", url);
		assertNotNull("display_address should not be null", display_address);
		assertNotNull("display_phone should not be null", display_phone);
		
		
		System.out.println(rating);
		System.out.println(mobile_url);
		System.out.println(rating_img_url);
		System.out.println(rating_img_url_small);
		System.out.println(display_phone);
		System.out.println(display_address);
		System.out.println(url);
	}
	
	public void testRating(){
		String result = YelpAPI.search1("Abe Fisher");
		YelpResult yelpResult = new YelpResult(result);
		
		rating = yelpResult.getRating();
		assertEquals("rating should equals to 4.5", 4.5, rating);
	}
	
	public void testMobile_url(){
		String result = YelpAPI.search1("Abe Fisher");
		YelpResult yelpResult = new YelpResult(result);
		
		mobile_url = yelpResult.getMobile_url();
		assertEquals("rating should equals to ...", 4.5, mobile_url);
	}
	public void testRating_img_url(){
		String result = YelpAPI.search1("Abe Fisher");
		YelpResult yelpResult = new YelpResult(result);
		
		rating_img_url = yelpResult.getRating_img_url();
		assertEquals("rating_img_url should equals to ...", 4.5, rating_img_url);
	}
	public void testRating_img_url_small(){
		String result = YelpAPI.search1("Abe Fisher");
		YelpResult yelpResult = new YelpResult(result);
		
		rating_img_url_small = yelpResult.getRating();
		assertEquals("rating should equals to 4.5", 4.5, rating_img_url_small);
	}
	public void testDisplay_phone(){
		String result = YelpAPI.search1("Abe Fisher");
		YelpResult yelpResult = new YelpResult(result);
		
		display_phone = yelpResult.getRating();
		assertEquals("rating should equals to 4.5", 4.5, display_phone);
	}
	public void testDisplay_address(){
		String result = YelpAPI.search1("Abe Fisher");
		YelpResult yelpResult = new YelpResult(result);
		
		display_address = yelpResult.getRating();
		assertEquals("rating should equals to 4.5", 4.5, display_address);
	}
	

}
