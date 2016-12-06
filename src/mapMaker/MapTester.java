package mapMaker;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
import search.Bar;
import search.BarData;
import search.BarFinder;
import mapMaker.DataSender;
import search.FileFetcher;
import yelp.YelpAPI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.effect.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * This is the map tester, which will create the google map and display the bar's location and the information of happy hour
 * @author Jiahui, He Gao, Han Zhu
 *
 */
public class MapTester extends Application implements MapComponentInitializedListener{

	private GoogleMapView mapView;
	private GoogleMap map;
	
	/* GUI components */
	private Stage stage;
	private Button goButton;
	private VBox sidePane;
	
	private ArrayList<Bar> searchResult;
	private ArrayList<Marker> markers;
	private DataSender ds;	
	
	@Override
	public void start(Stage Stage) throws Exception {
		
		//this.stage = Stage;
		mapView = new GoogleMapView();
		mapView.addMapInializedListener(this);
		goButton = new Button("Happy Hour Go!");
		goButton.setMaxSize(130, 130);
		goButton.setStyle("-fx-background-color: linear-gradient(#ffd65b, #e68400), "
				+ "linear-gradient(#ffef84, #f2ba44), linear-gradient(#ffea6a, #efaa22),"
				+ "linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%);"
				+ " -fx-text-fill: #654b00;");
		
		sidePane = new VBox(10);
		sidePane.setPadding(new Insets(20, 20, 0, 20));
		sidePane.setPrefHeight(50);
		sidePane.setPrefWidth(300);
		sidePane.setAlignment(Pos.TOP_CENTER);
		sidePane.getChildren().add(goButton);
		markers = new ArrayList<Marker>();

//		ToolBar tb = new ToolBar();
//		tb.getItems().add(goButton);
//		tb.opaqueInsetsProperty(); // set opaqueness ?
		
		/* set overall layout */
		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp);
		bp.setCenter(mapView);
		bp.setRight(sidePane);
		
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
    	LatLong center = new LatLong(39.952903, -75.164106);

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
        
//        DropShadow shadow = new DropShadow();
      
//        goButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {goButton.setEffect(shadow); });
////        goButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
////        	goButton.setEffect(shadow); });
        
        goButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// Call search algorithm
				try {
					getSearchResult();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				ds = new DataSender(searchResult);
				System.out.println("Bars now on Happy Hour...");				
				
				map.setZoom(13);
				putMarker();
			}
        });
    }
    /**
     * This method runs search algorithm upon button press, initialize DataSender
     * @param 
     * @throws FileNotFoundException 
     */
    private void getSearchResult() throws FileNotFoundException {
    	Calendar now = Calendar.getInstance();
    	System.out.println(now);
    	FileFetcher ff = new FileFetcher(now.get(Calendar.DAY_OF_WEEK));
		BarData bd = new BarData(ff);
		BarFinder bf = new BarFinder(now, bd);
		searchResult = bf.find();
//		System.out.println(searchResult.size());
    }
    
    
    private void putMarker() {
    	//Add all marker to the map
        for (int i = 0; i < ds.getAddrLat().size(); i++){
	        MarkerOptions markerOptions = new MarkerOptions();

	        LatLong markerCenter = new  LatLong(ds.getAddrLat().get(i),ds.getAddrLon().get(i));
	        markerOptions.position(markerCenter)
	                    .visible(Boolean.TRUE)
	                    .title("My Marker" + i)
	                    .animation(Animation.BOUNCE);


	        Marker marker = new Marker( markerOptions );

	        markers.add(marker);
	        map.addMarker(marker);


	        //Add a Info to the map
	        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
	        String name = ds.getName().get(i);
	        infoWindowOptions.content(name);
	        InfoWindow barInfoWindow = new InfoWindow(infoWindowOptions);
			map.addUIEventHandler(marker, UIEventType.click, (JSObject obj) -> {
				
				map.setCenter(markerCenter);
				map.setZoom(14);
				marker.setAnimation(Animation.BOUNCE);
				marker.setAnimation(Animation.DROP);

				barInfoWindow.open(map, marker);
				YelpAPI.start(barInfoWindow.getContent());
//				System.out.println(barInfoWindow.getContent() +"-----------------");
				Label nameLabel = new Label(name);
				sidePane.getChildren().add(nameLabel);
			});
        }
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
