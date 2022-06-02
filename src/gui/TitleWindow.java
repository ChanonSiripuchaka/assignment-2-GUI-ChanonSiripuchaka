package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TitleWindow {

	private AnchorPane pane;

	public TitleWindow(Stage stage) {
	      TabPane tabPane = new TabPane();

	      
	      Tab buyJourney = new Tab();
	      buyJourney.setText("Buy Journey");
	      
	      VBox tabOneVBox = new VBox(5);
	      tabOneVBox.setPadding(new Insets(10,0,0,0));
	      
	      HBox userHbox = new HBox(10);
	      Label userListLabel = new Label("Select User");
	      ComboBox<String> usersList = new ComboBox<String>();
	      userHbox.getChildren().addAll(userListLabel , usersList);
	      
	      
	      HBox startStationHbox = new HBox(10);
	      Label startStationListLabel = new Label("Select Start Station");
	      ComboBox<String> startStationList = new ComboBox<String>();
	      startStationHbox.getChildren().addAll(startStationListLabel ,startStationList);
	      
	      HBox endStationHbox = new HBox(10);
	      Label endStationListLabel = new Label("Select End Station");
	      ComboBox<String> endStationList = new ComboBox<String>();
	      endStationHbox.getChildren().addAll(endStationListLabel , endStationList);
	      
	      HBox daysHbox = new HBox(10);
	      Label dayListLabel = new Label("Select Day");
	      ComboBox<String> daysList = new ComboBox<String>();
	      daysHbox.getChildren().addAll(dayListLabel , daysList);

	      HBox startTimeHbox = new HBox(10);
	      Label startTimeLabel = new Label("Enter start time");
	      TextField startTimeTextField = new TextField();
	      startTimeHbox.getChildren().addAll(startTimeLabel , startTimeTextField);

	      HBox endTimeHbox = new HBox(10);
		  Label endTimeLabel = new Label("Enter end time");
	      TextField endTimeTextField = new TextField();
	      endTimeHbox.getChildren().addAll(endTimeLabel , endTimeTextField);


	      Label messageBoxLabel = new Label("Status Box");
	      TextArea messageBoxTextArea = new TextArea();
	      
	      HBox buttonsHbox = new HBox(10);
		  Button btnPurchase = new Button("Purchase");
	      Button btnCancel = new Button("Cancel");
	      buttonsHbox.getChildren().addAll(btnPurchase , btnCancel);
 
	      
	      tabOneVBox.getChildren().addAll(userHbox, startStationHbox, endStationHbox ,daysHbox ,startTimeHbox , endTimeHbox ,messageBoxLabel , messageBoxTextArea , buttonsHbox);
	      
	      	      
	      buyJourney.setContent(tabOneVBox);
	      
	      Tab manageUsers = new Tab();
	      manageUsers.setText("Manage Users");
	      
	      Tab displayReports = new Tab();
	      displayReports.setText("Display Reports");
	      
	      
	      
	      
	      
	      //Adding tabs to the tab pane
	      tabPane.getTabs().addAll(buyJourney, manageUsers, displayReports);
	      
	      
	      //Setting anchor pane as the layout
	      pane = new AnchorPane();
	      AnchorPane.setTopAnchor(tabPane, 15.0);
	      AnchorPane.setRightAnchor(tabPane, 15.0);
	      AnchorPane.setBottomAnchor(tabPane, 15.0);
	      AnchorPane.setLeftAnchor(tabPane, 15.0);
	      pane.getChildren().addAll(tabPane);
      }
	
	public Pane getRoot() {
		return pane;
	}
	
}
