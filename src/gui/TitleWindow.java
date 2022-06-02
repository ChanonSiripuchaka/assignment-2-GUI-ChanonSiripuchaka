package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import myti.User;

public class TitleWindow {

	private AnchorPane pane;

	public TitleWindow(Stage stage) {
	      TabPane tabPane = new TabPane();

	      //Tab Buy Journey starts Here
	      
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
	      //Tab Buy Journey Finish Here
	      
	      
	      
	      Tab manageUsers = new Tab();
	      manageUsers.setText("Manage Users");
	      
	      VBox tabTwoVBox = new VBox(5);
	      tabTwoVBox.setPadding(new Insets(10,0,0,0));
	      
	      HBox userDetailHbox = new HBox(10);
	      
	      TableView tableView = new TableView();
	      tableView.setPrefWidth(150);
	      tableView.setPrefHeight(200);
	      
	      TableColumn<User, String> column1 = 
	      new TableColumn<>("Id");
	      
	      column1.setCellValueFactory(
	          new PropertyValueFactory<>("id"));


	      TableColumn<User, String> column2 = 
	      new TableColumn<>("Name");
	      
	      column2.setCellValueFactory(
	          new PropertyValueFactory<>("name"));


	      tableView.getColumns().add(column1);
	      tableView.getColumns().add(column2);
	      //Still to do
//	      tableView.getItems().add(
//	        new Person("John", "Doe"));
//	      tableView.getItems().add(
//	        new Person("Jane", "Deer"));

	      TextArea userDetailTextArea = new TextArea();
	      userDetailTextArea.setPrefWidth(200);
	      userDetailTextArea.setPrefHeight(250);
	      
	      VBox addCreditVBox  = new VBox(10);
	      Label addCreditLabel = new Label("Enter Credit to enter");
	      TextField txtAddCredit = new TextField();
	      Button btnAddCredit = new Button("Add Credit");
	      addCreditVBox.getChildren().addAll(addCreditLabel, txtAddCredit , btnAddCredit);
	      
	      userDetailHbox.getChildren().addAll(tableView , userDetailTextArea, addCreditVBox);
	      userDetailHbox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
	    	        + "-fx-border-width: 1;" + "-fx-border-insets: 5;"
	    	        + "-fx-border-radius: 5;" + "-fx-border-color: black;");

	      
	      VBox addUserVBox = new VBox(10);
	      
	      HBox userIdHbox = new HBox(10);
	      Label userIdLabel = new Label("Enter user id");
	      TextField userIdTextField = new TextField();
	      userIdHbox.getChildren().addAll(userIdLabel , userIdTextField);

	      HBox userEmailHbox = new HBox(10);
		  Label userEmailLabel = new Label("Enter user email");
	      TextField userEmailTextField = new TextField();
	      userEmailHbox.getChildren().addAll(userEmailLabel , userEmailTextField);
	     
	      HBox userNameHbox = new HBox(10);
	      Label userNameLabel = new Label("Enter user name");
	      TextField userNameTextField = new TextField();
	      userNameHbox.getChildren().addAll(userNameLabel , userNameTextField);

	      HBox userTypebox = new HBox(10);
	      Label userTypeLabel = new Label("Select User type");
	      ComboBox<String> userTypes = new ComboBox<String>();
	      userTypebox.getChildren().addAll(userTypeLabel , userTypes);

	      Button btnAddUser = new Button("Add User");
	      
	      TextArea statusMessage = new  TextArea();
	      statusMessage.setPrefHeight(40);
	      
	      
	      addUserVBox.getChildren().addAll(userIdHbox , userEmailHbox, userNameHbox , userTypebox, btnAddUser , statusMessage);
	      
	      addUserVBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
	    	        + "-fx-border-width: 1;" + "-fx-border-insets: 5;"
	    	        + "-fx-border-radius: 5;" + "-fx-border-color: black;");

	      tabTwoVBox.getChildren().addAll(userDetailHbox, addUserVBox );
	      
	      manageUsers.setContent(tabTwoVBox);
	      
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
