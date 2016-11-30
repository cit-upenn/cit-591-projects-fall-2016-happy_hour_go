package mapMaker;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Go extends Application implements MapComponentInitializedListener, EventHandler<ActionEvent> {
	
	// implements EventHandler<ActionEvent>
	private GoogleMapView mapView;
	private GoogleMap map;

	private DataSender ds;
	
	/* GUI components */
	private Button goButton;
	private Stage stage;

	@Override
	public void start(Stage Stage) throws Exception {
		ds = new DataSender();
		this.stage = Stage;
		mapView = new GoogleMapView();
		mapView.addMapInializedListener(this);
		
		/* Set tool bar */
		goButton = new Button("Happy Hour Go!");
		goButton.setOnAction(this);
		goButton.setMaxSize(130, 130);
		goButton.setStyle("-fx-background-color: linear-gradient(#ffd65b, #e68400), "
				+ "linear-gradient(#ffef84, #f2ba44), linear-gradient(#ffea6a, #efaa22),"
				+ "linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%);"
				+ " -fx-text-fill: #654b00;");
		
		ToolBar tb = new ToolBar();
		tb.getItems().add(goButton);
		tb.opaqueInsetsProperty(); // set opaqueness ?

		/* set overall layout */
//		BorderPane bp = new BorderPane();
//		Scene scene = new Scene(bp);
//		bp.setCenter(mapView);
//		bp.setTop(tb);
		
		StackPane sp = new StackPane();
		Scene scene = new Scene(sp);
		sp.getChildren().addAll(mapView, goButton);
		sp.setAlignment(Pos.CENTER);

		Stage.setScene(scene);
		Stage.setTitle("Happy Hour Go!");
		Stage.show();
		
//		scene.getStylesheets().add(Button.class.getResource("Button.css").toExternalForm());
	}
	
	/**
	 * initialize the map
	 */
    public void mapInitialized() {
    	LatLong center = new LatLong(ds.addrLon.get(0),ds.addrLat.get(0));

    	MapOptions options = new MapOptions();
		options.center(center)
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
	}
    
	/**
	 * Handle an mouse-clicking event
	 */
	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == goButton) { 
            System.out.println("Bars now on Happy Hour...");
            // BarSearcher.search(); // call the search algorithm
		}
	}
	
	/**
	 * Main method to run Go
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}

