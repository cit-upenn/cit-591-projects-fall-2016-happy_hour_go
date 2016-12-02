package dataPrep;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.service.geocoding.GeocodingServiceCallback;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.FileReader;

/**
 * This class uses Google Geocoding services to get the latitude and longitude of an address. A map must be initialized before querying the geocoding
 * API. Additionally, the API provided by GmapsFX can only query 10 addresses on average during one runtime.
 * @author Han Zhu
 *
 */
public class Geocoder extends Application implements MapComponentInitializedListener, GeocodingServiceCallback{
	private GoogleMapView mapView;
	private GoogleMap map;
	private Stage stage;

	@Override
	public void start(Stage Stage) throws Exception {
		this.stage = Stage;
		mapView = new GoogleMapView();
		mapView.addMapInializedListener(this);

		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp);
		bp.setCenter(mapView);

		Stage.setScene(scene);
		Stage.setTitle("Happy Hour Go!");
		Stage.show();
	}

	/**
	 * Initialize a random map.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	/**
	 * After the map is initialized, query the geocoding API.
	 */
	@Override
	public void mapInitialized() {
		// TODO Auto-generated method stub
		LatLong center = new LatLong(39.9434835, -75.1753488);
		
		MapOptions options = new MapOptions();
		options.center(center)
		       .mapType(MapTypeIdEnum.ROADMAP)
		       //maybe set false
		       .mapTypeControl(true)
		       .overviewMapControl(false)
		       .panControl(true)
		       .rotateControl(false)
		       .scaleControl(false)
		       .streetViewControl(false)
		       .zoom(12)
		       .zoomControl(true);

        map = mapView.createMap(options);
        
//      Create a GeocodingService object.
        GeocodingService gs = new GeocodingService();
		try {
			FileReader fr = new FileReader("unique-bars.csv");
			fr.readFile();
			PrintWriter out = new PrintWriter(new FileWriter("geocodes.csv", true));
			
////		Use the addresses from unique-bars.csv to get the geocodes. Make 10 queries each time. Change the indexes and re-run the program.
//			for (int i = 310; i < 311; i++) {
//				String bar = fr.getLines().get(i);
//				String[] barFields = bar.split("\t");
//				out.println(barFields[0]);
			
////			This method will eventually call geocodedResultReceived() below.
//				gs.geocode(barFields[1] + ", Philadelphia, PA", this);
//			}
			
//			After the coordinates are printed, print all bar names after the last line, and use Sublime's awesome multi-cursor to match each bar with
//			its geocodes.
			for (int i = 0; i < fr.getLines().size(); i++) {
				String bar = fr.getLines().get(i);
				String[] fields = bar.split("\t");
				out.println(fields[0]);
			}
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method manipulates the result returned from Google's API. In this case, it will print to the file the latitude and longitude separated
	 * by tab.
	 */
	@Override
	public void geocodedResultsReceived(GeocodingResult[] results, GeocoderStatus status) {
		// TODO Auto-generated method stub
		try {
			PrintWriter out = new PrintWriter(new FileWriter("geocodes.csv", true));
			
			if (status.equals(GeocoderStatus.OK)) {
	            for (GeocodingResult e : results) {
//	            	System.out.println(e.getVariableName());
	            	System.out.println("GEOCODE: " + e.getFormattedAddress() + "\n" + e.toString());
	                
	                String[] resultPieces = e.toString().split("lat: |lng: |Location Type");
	                out.println(resultPieces[1].trim() + '\t' + resultPieces[2].trim());
	            }
	        }
			out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
