package gui;
	
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fileio.Utils;
import javafx.application.Application;
import javafx.stage.Stage;
import myti.MyTi;
import myti.User;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private TitleWindow title;
	private Map<String, User> users;
	private ArrayList<MyTi> mytis;
	private ArrayList<String> s;

	@Override
	public void start(Stage primaryStage) {
		try {
			Parameters params = getParameters();
			List<String> list = params.getRaw();
			Utils.readLines(list.get(0));
			Utils.createUsersFromReadLines();
			Utils.readPrices();
			title = new TitleWindow(primaryStage , list);
			Scene scene = new Scene(title.getRoot(),600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("MyTi Ticketing System");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
