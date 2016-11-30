package mapMaker;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MapTester extends Application implements MapComponentInitializedListener {

	private GoogleMapView mapView;
	private GoogleMap map;

	private DataSender ds;

	/* GUI components */
	private Stage stage;

	@Override
	public void start(Stage Stage) throws Exception {
		ds = new DataSender();
		this.stage = Stage;
		mapView = new GoogleMapView();
		mapView.addMapInializedListener(this);

		/* set overall layout */
		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp);
		bp.setCenter(mapView);

		Stage.setScene(scene);
		Stage.setTitle("Happy Hour Go!");
		Stage.show();
	}

	/**
	 * initialize the map
	 */
    public void mapInitialized() {
    	LatLong center = new LatLong(ds.addrLon.get(0),ds.addrLat.get(0));

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

      //Add a marker to the map

        for (int i = 0; i < ds.addrLat.size(); i++){
	        MarkerOptions markerOptions = new MarkerOptions();
	        markerOptions.position(new LatLong(ds.getAddrLon().get(i),ds.getAddrLat().get(i)))
	                    .visible(Boolean.TRUE)
	                    .title("My Marker")
	                    .animation(Animation.DROP);

	        Marker marker = new Marker( markerOptions );
	        map.addMarker(marker);

	      //Add a Info to the map

	        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();

	        infoWindowOptions.content(ds.getDisplay().get(i));

	        InfoWindow barInfoWindow = new InfoWindow(infoWindowOptions);
	        barInfoWindow.open(map, marker);
        }
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
