package mapMaker;

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
import mapMaker.AlertBox;
import search.FileFetcher;
import yelp.YelpAPI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.web.WebView;
import javafx.scene.effect.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
	private AlertBox alert;
	
	private ArrayList<Bar> searchResult;
	private ArrayList<Marker> markers;
	private DataSender ds;	
	private InfoWindow infoWindowStore;

	
	@Override
	public void start(Stage Stage) throws Exception {
		
		//this.stage = Stage;
		mapView = new GoogleMapView();
		mapView.addMapInializedListener(this);
		goButton = new Button("Happy Hour Go!");
		goButton.setMaxSize(130, 130);
		goButton.setStyle("-fx-background-color: linear-gradient(#ffd65b, #e68400), linear-gradient(#ffef84, #f2ba44), linear-gradient(#ffea6a, #efaa22), linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%); -fx-text-fill: #654b00;");
		
		sidePane = new VBox(10);
		sidePane.setPadding(new Insets(20, 20, 20, 20));
		sidePane.setPrefHeight(50);
		sidePane.setPrefWidth(400);
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

		Stage.setMaximized(true);
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
				.zoomControl(true)
				.styleString("[{'featureType':'landscape','stylers':[{'hue':'#FFBB00'},{'saturation':43.400000000000006},{'lightness':37.599999999999994},{'gamma':1}]},{'featureType':'road.highway','stylers':[{'hue':'#FFC200'},{'saturation':-61.8},{'lightness':45.599999999999994},{'gamma':1}]},{'featureType':'road.arterial','stylers':[{'hue':'#FF0300'},{'saturation':-100},{'lightness':51.19999999999999},{'gamma':1}]},{'featureType':'road.local','stylers':[{'hue':'#FF0300'},{'saturation':-100},{'lightness':52},{'gamma':1}]},{'featureType':'water','stylers':[{'hue':'#0078FF'},{'saturation':-13.200000000000003},{'lightness':2.4000000000000057},{'gamma':1}]},{'featureType':'poi','stylers':[{'hue':'#00FF6A'},{'saturation':-1.0989010989011234},{'lightness':11.200000000000017},{'gamma':1}]}]");
				
        map = mapView.createMap(options);
        
        setupJSAlerts(mapView.getWebview());
        
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        String name = "nothing";
        infoWindowOptions.content(name);
        infoWindowStore = new InfoWindow(infoWindowOptions);
        
        goButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				try {
					getSearchResult();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				ds = new DataSender(searchResult);
				
				System.out.println("Bars now on Happy Hour...");
				
				if(ds.getAddrLat() == null || ds.getAddrLat().size() == 0) {
					AlertBox.display("No Happy Hour Now!","Come back later.");
					return;
				}
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
    	
//    	test alert box with no Happy Hour Found. Uncomment next line to test
//    	now.set(2016, 12, 6, 10, 0, 0);

    	FileFetcher ff = new FileFetcher(now.get(Calendar.DAY_OF_WEEK));
		BarData bd = new BarData(ff);
		BarFinder bf = new BarFinder(now, bd);
		searchResult = bf.find();
//		for (Bar bar : searchResult) {
//			System.out.println(bar.name + ' ' + bar.startTimeString + ' ' + bar.endTimeString + ' ' + bar.descriptionString);
//		}
    }
    
    private void putMarker() {
    	//Add all marker to the map
        for (int i = 0; i < ds.getAddrLat().size(); i++){
	        MarkerOptions markerOptions = new MarkerOptions();

	        LatLong markerCenter = new  LatLong(ds.getAddrLat().get(i),ds.getAddrLon().get(i));
	        markerOptions.position(markerCenter)
	        			.icon("http://i.imgur.com/elPucS5.png")
	                    .title("My Marker" + i)
	                    .animation(Animation.BOUNCE)
	                    .visible(true);
	        Marker marker = new Marker(markerOptions);

	        markers.add(marker);
	        map.addMarker(marker);

	        //Add a Info to the map
	        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
	        String name = ds.getName().get(i);
	        infoWindowOptions.content(name);
	        InfoWindow barInfoWindow = new InfoWindow(infoWindowOptions);
	        
	        String startTime = ds.getStartTime().get(i);
	        String endTime = ds.getEndTime().get(i);
	        String description = ds.getDescription().get(i);

			map.addUIEventHandler(marker, UIEventType.click, (JSObject obj) -> {
				
				map.setCenter(markerCenter);
				map.setZoom(15);

				infoWindowStore.close();
				barInfoWindow.open(map, marker);
				
				String result = YelpAPI.search1(name);
//				System.out.println(barInfoWindow.getContent() +"-----------------");
				
				Label nameLabel = new Label(name);
				Label timeLabel = new Label(startTime + " - " + endTime);
				Label descLabel = new Label(description);
				descLabel.setWrapText(true);
				
				sidePane.getChildren().clear();
				sidePane.getChildren().addAll(nameLabel, timeLabel, descLabel);
				infoWindowStore  = barInfoWindow;
			});
		
        }
	}
    
    /**
     * This method setup Alert box on the map
     * @param webView
     */
    private void setupJSAlerts(WebView webView) {
        webView.getEngine().setOnAlert( e -> {
            Stage popup = new Stage();
            popup.initOwner(stage);
            popup.initStyle(StageStyle.UTILITY);
            popup.initModality(Modality.WINDOW_MODAL);

            StackPane content = new StackPane();
            content.getChildren().setAll(
              new Label(e.getData())
            );
            content.setPrefSize(200, 100);

            popup.setScene(new Scene(content));
            popup.showAndWait();
        });
    }

	public static void main(String[] args) {
		launch(args);
	}
	
}
