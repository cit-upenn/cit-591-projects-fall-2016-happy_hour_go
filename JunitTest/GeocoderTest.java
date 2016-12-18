import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import cit591hw6.dataPrep.Geocoder;

public class GeocoderTest {
	@Test
	public void testGeocode() {
		Geocoder geo = new Geocoder();
		ArrayList<String> actual = geo.geocode("Abbaye");
		
		ArrayList<String> expected = new ArrayList<>();
		expected.add("39.962247651128");
		expected.add("-75.143003697265");
		
		assertEquals(expected, actual);
	}
}
