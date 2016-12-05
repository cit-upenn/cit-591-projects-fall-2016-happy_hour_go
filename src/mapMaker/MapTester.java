package mapMaker;

import java.util.Calendar;

//import javafx.scene.control.Button;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import netscape.javascript.JSObject;
import search.FileFetcher;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;

/**
 * This is the map tester, which will create the google map and display the bar's location and the information of happy hour
 * @author Jiahui, He Gao
 *
 */
public class MapTester extends Application implements MapComponentInitializedListener{

	private GoogleMapView mapView;
	private GoogleMap map;

	private DataSender ds;

	/* GUI components */
	private Stage stage;
	private Button goButton;

	@Override
	public void start(Stage Stage) throws Exception {
		ds = new DataSender();
		//this.stage = Stage;
		mapView = new GoogleMapView();
		mapView.addMapInializedListener(this);
		goButton = new Button("Happy Hour Go!");
		goButton.setMaxSize(130, 130);
		goButton.setStyle("-fx-background-color: linear-gradient(#ffd65b, #e68400), "
				+ "linear-gradient(#ffef84, #f2ba44), linear-gradient(#ffea6a, #efaa22),"
				+ "linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%);"
				+ " -fx-text-fill: #654b00;");
		
		VBox topBox = new VBox();
		topBox.setPadding(new Insets(20, 20, 0, 20));
		topBox.setPrefHeight(50);
		topBox.getChildren().add(goButton);

//		ToolBar tb = new ToolBar();
//		tb.getItems().add(goButton);
//		tb.opaqueInsetsProperty(); // set opaqueness ?
		
		/* set overall layout */
		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp);
		bp.setCenter(mapView);
		bp.setRight(topBox);
		
//		StackPane sp = new StackPane();
//		sp.getChildren().addAll(mapView, goButton);
//		sp.setAlignment(Pos.CENTER);
		
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
				.mapMaker(true)
		       .mapType(MapTypeIdEnum.ROADMAP)
		       .mapTypeControl(true)
		       .overviewMapControl(false)
		       .panControl(true)
		       .rotateControl(false)
		       .scaleControl(false)
		       .streetViewControl(false)
		       .zoom(12)
		       .zoomControl(true);

        map = mapView.createMap(options);
        goButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Bars now on Happy Hour...");
				// BarSearcher.search(); // call the search algorithm
				map.setZoom(15);
				putMarker();
				System.out.println("btn pressed");
			}
        });
    }
    
//	@Override
//	public void handle(ActionEvent event) {
//		if (event.getSource() == goButton) { 
//            System.out.println("Bars now on Happy Hour...");
//            putMarker();
//            //goButton.getOnSwipeLeft();
//            // BarSearcher.search(); // call the search algorithm
//		}
//	}
    
    private void putMarker() {
    	//Add all marker to the map
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
	        //barInfoWindow.open(map, marker);
			map.addUIEventHandler(marker, UIEventType.click, (JSObject obj) -> {
				barInfoWindow.open(map, marker);
			});
        }
	}

	public static void main(String[] args) {
		Calendar now = Calendar.getInstance();
		System.out.println(now.get(Calendar.DAY_OF_WEEK));
		System.out.println(now.get(Calendar.HOUR_OF_DAY));
		
		int dayOfWeek = Calendar.DAY_OF_WEEK;
		int hourOfDay = Calendar.HOUR_OF_DAY;
		
		FileFetcher ff = new FileFetcher(dayOfWeek);
		//TODO based on BarFinder's output, GeoCoder generates Lat and Lon, write to .map file, to plot on map
		
		launch(args);
	}
	
}
