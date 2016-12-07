package mapMaker;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import search.Bar;

/**
 * This class will organize the bar's result searched from Yelp API
 * 
 * @author Jiahui
 *
 */
public class YelpResult {
	private String result;

	private String rating;
	private String rating_img_url;
	private String rating_img_url_small;
	private String url;
	private String categories;
	private String display_phone;
	private String display_address;

	public YelpResult(String result) {
		this.result = result;
		rating = null;
		rating_img_url= null;
		rating_img_url_small= null;
		url= null;
		categories= null;
		display_phone= null;
		display_address= null;
		parse(this.result);
	}

	private void parse(String result) {
		Pattern ratingPattern = Pattern.compile("\"rating\": (\\d\\.\\d), ");
		Pattern rating_img_urlPattern = Pattern.compile("\"rating_img_url\": \"(.*.png)\", \"review_count\"");
		Pattern rating_img_url_smallPattern = Pattern.compile("\"rating_img_url_small\": \"(.*.png)\", \"url\"");
		Pattern urlPattern = Pattern.compile("\"url\": \"(.*)\", \"categories\"");
		Pattern categoriesPattern = Pattern.compile("\"categories\":\\[(.*)\\], \"menu_date_updated\"");
		Pattern display_phonePattern = Pattern.compile("\"display_phone\": \"(.*)\", \"rating_img_url_large\"");
		Pattern display_addressPattern = Pattern.compile("\"display_address\": \\[(\\w{*})\\], \"geo_accuracy\"");

		Matcher matcher = ratingPattern.matcher(result);
		if (matcher.find()) {
			rating = matcher.group(1).trim();
		}
		
		matcher = rating_img_urlPattern.matcher(result);
		if (matcher.find()) {
			rating_img_url = matcher.group(1).trim();
		}
		
		matcher = rating_img_url_smallPattern.matcher(result);
		if (matcher.find()) {
			rating_img_url_small = matcher.group(1).trim();
		}
		
		matcher = urlPattern.matcher(result);
		if (matcher.find()) {
			url = matcher.group(1).trim();
		}
		
		matcher = categoriesPattern.matcher(result);
		if (matcher.find()) {
			categories = matcher.group(1).trim();
		}
		matcher = display_phonePattern.matcher(result);
		if (matcher.find()) {
			display_phone = matcher.group(1).trim();
		}
		matcher = display_addressPattern.matcher(result);
		if (matcher.find()) {
			display_address = matcher.group(1).trim();
		}
		
//		System.out.println(rating);
//		System.out.println(rating_img_url);
//		System.out.println(rating_img_url_small);
//		System.out.println(url);
//		System.out.println(categories);
//		System.out.println(display_phone);
//		System.out.println(display_address);

	}

	public String getResult() {
		return result;
	}

	public String getRating() {
		return rating;
	}

	public String getRating_img_url() {
		return rating_img_url;
	}

	public String getRating_img_url_small() {
		return rating_img_url_small;
	}

	public String getUrl() {
		return url;
	}

	public String getCategories() {
		return categories;
	}

	public String getDisplay_phone() {
		return display_phone;
	}

	public String getDisplay_address() {
		return display_address;
	}

}
