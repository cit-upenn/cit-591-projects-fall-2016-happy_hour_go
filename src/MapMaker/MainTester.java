package MapMaker;

import java.io.*;

import MapMaker.DataSender;
import gmapsfx.GoogleMapView;
import gmapsfx.MapComponentInitializedListener;
import gmapsfx.javascript.JavascriptArray;
import gmapsfx.javascript.object.GoogleMap;
import gmapsfx.javascript.object.LatLong;
import gmapsfx.javascript.object.MVCArray;
import gmapsfx.javascript.object.MapOptions;
import gmapsfx.javascript.object.MapTypeIdEnum;

import gmapsfx.javascript.object.Marker;
import gmapsfx.javascript.object.MarkerOptions;
import gmapsfx.javascript.object.Animation;

import gmapsfx.javascript.object.Polyline;
import gmapsfx.javascript.object.PolylineOptions;

import gmapsfx.javascript.event.*;

import gmapsfx.javascript.object.InfoWindow;
import gmapsfx.javascript.object.InfoWindowOptions;

import netscape.javascript.JSObject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainTester extends Application implements MapComponentInitializedListener {

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
		mapView.addMapInitializedListener(this);

		/* set overall layout */
		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp);
		bp.setCenter(mapView);

		Stage.setScene(scene);
		Stage.setTitle("GoogleMapTs");
		Stage.show();
	}

	/**
	 * initialize the map
	 */
    public void mapInitialized() {
    	
    
    	LatLong center = new LatLong(ds.addrLon.get(0),ds.addrLat.get(0));//1
    	
    	MapOptions options = new MapOptions();
		options.center(center)
		       .mapMarker(false)
		       .mapType(MapTypeIdEnum.ROADMAP)
		       //maybe set false
		       .mapTypeControl(true)
		       .overviewMapControl(false)
		       .panControl(true)
		       .rotateControl(false)
		       .scaleControl(false)
		       .streetViewControl(false)
		       .zoom(18)
		       .zoomControl(true);

        map = mapView.createMap(options);
    
    
    
      //Add a marker to the map
      
        for (int i = 0; i<ds.addrLat.size(); i++){
        	
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position( new LatLong( ds.getAddrLon().get(i),ds.getAddrLat().get(i)))
                    .visible(Boolean.TRUE)
                    .title("My Marker")
                    .animation(Animation.DROP);
      
        Marker marker = new Marker( markerOptions );
        map.addMarker(marker);

      //Add a Info to the map
        
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();

        infoWindowOptions.content(ds.getDisplay().get(i) );

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, marker);
        
        }
        

    }

	public static void main(String[] args) {
		launch(args);
	}
}