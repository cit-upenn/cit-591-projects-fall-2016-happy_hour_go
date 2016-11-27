package data;

import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.service.geocoding.GeocodingServiceCallback;

public class Geocoder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GeocodingService gs = new GeocodingService();
		gs.geocode("136 Chestnut Street", gs.callback);
	}
}
