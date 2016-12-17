import static org.junit.Assert.*;

import org.junit.Before;
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
	
	private String result;
	
	@Before
	public void setup() {
		result = YelpAPI.search1("Abe Fisher");
	}
	
	@Test
	public void testResultNotNull() {
//		String result = YelpAPI.search1("Abe Fisher");
		YelpResult yelpResult = new YelpResult(result);
		assertNotNull("Result get from Yelp should not be null", yelpResult.getResult());
	}
	
	@Test
	public void testParse(){
//		String result = YelpAPI.search1("Abe Fisher");
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

	}
	
	public void testRating(){
//		String result = YelpAPI.search1("Abe Fisher");
		YelpResult yelpResult = new YelpResult(result);
		
		rating = yelpResult.getRating();
		assertEquals("rating should equals to 4.5", 4.5, rating);
	}
	
	public void testUrl(){
//		String result = YelpAPI.search1("Abe Fisher");
		YelpResult yelpResult = new YelpResult(result);
		
		url = yelpResult.getUrl();
		assertEquals("Url should equals to https://www.yelp.com/biz/abe-fisher-philadelphia", "https://www.yelp.com/biz/abe-fisher-philadelphia", url);
	}
	
	public void testMobile_url(){
//		String result = YelpAPI.search1("Abe Fisher");
		YelpResult yelpResult = new YelpResult(result);
		
		mobile_url = yelpResult.getMobile_url();
		assertEquals("mobile_url should equals to https://m.yelp.com/biz/abe-fisher-philadelphia?adjust_creative=E6f0gZIoWNEHQ9lpzlqIEw\u0026utm_campaign=yelp_api\u0026utm_medium=api_v2_business\u0026utm_source=E6f0gZIoWNEHQ9lpzlqIEw", "https://m.yelp.com/biz/abe-fisher-philadelphia?adjust_creative=E6f0gZIoWNEHQ9lpzlqIEw\u0026utm_campaign=yelp_api\u0026utm_medium=api_v2_business\u0026utm_source=E6f0gZIoWNEHQ9lpzlqIEw", mobile_url);
	}
	public void testRating_img_url(){
//		String result = YelpAPI.search1("Abe Fisher");
		YelpResult yelpResult = new YelpResult(result);
		
		rating_img_url = yelpResult.getRating_img_url();
		assertEquals("rating_img_url should equals to https://s3-media2.fl.yelpcdn.com/assets/2/www/img/99493c12711e/ico/stars/v1/stars_4_half.png", "https://s3-media2.fl.yelpcdn.com/assets/2/www/img/99493c12711e/ico/stars/v1/stars_4_half.png", rating_img_url);
	}
	public void testRating_img_url_small(){
//		String result = YelpAPI.search1("Abe Fisher");
		YelpResult yelpResult = new YelpResult(result);
		
		rating_img_url_small = yelpResult.getRating();
		assertEquals("rating_img_url_smal should equals to https://s3-media2.fl.yelpcdn.com/assets/2/www/img/a5221e66bc70/ico/stars/v1/stars_small_4_half.png", "https://s3-media2.fl.yelpcdn.com/assets/2/www/img/a5221e66bc70/ico/stars/v1/stars_small_4_half.png", rating_img_url_small);
	}
	public void testDisplay_phone(){
//		String result = YelpAPI.search1("Abe Fisher");
		YelpResult yelpResult = new YelpResult(result);
		
		display_phone = yelpResult.getRating();
		assertEquals("display_phone should equals to +1-215-867-0088", "+1-215-867-0088", display_phone);
	}
	public void testDisplay_address(){
//		String result = YelpAPI.search1("Abe Fisher");
		YelpResult yelpResult = new YelpResult(result);
		
		display_address = yelpResult.getRating();
		assertEquals("display_address should equals to \"1623 Sansom St\", \"Rittenhouse Square\", \"Philadelphia, PA 19103\"", "\"1623 Sansom St\", \"Rittenhouse Square\", \"Philadelphia, PA 19103\"", display_address);
	}
	

}
