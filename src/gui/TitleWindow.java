package gui;

import java.awt.Event;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import fileio.Utils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import myti.Days;
import myti.Journey;
import myti.MyTi;
import myti.Station;
import myti.TravelPass;
import myti.User;
import myti.UserTypeEnum;
import myti.ZoneOne2Hour;
import myti.ZoneOneAllDay;
import myti.ZoneOneTwo2Hour;
import myti.ZoneOneTwoAllDay;
import myti.ZoneTwo2Hour;
import myti.ZoneTwoAllDay;
import myti.ZonesEnum;

public class TitleWindow {

	private VBox pane;
	private Map<String, User> s;
	private ComboBox<String> usersListTabThree;

	public TitleWindow(Stage stage, List<String> list) {

		ArrayList<Journey> journeys = new ArrayList<>();
		ArrayList<Station> stations = new ArrayList<Station>();
		createTestStations(stations);

		ArrayList<String> userIds = new ArrayList<String>();
		s = Utils.usersList;
		for (Map.Entry<String, User> entry : Utils.usersList.entrySet()) {
			userIds.add(entry.getKey());
		}
		ArrayList<String> stationsNames = new ArrayList<String>();
		for (Station station : stations) {
			stationsNames.add(station.getName());
		}
		TabPane tabPane = new TabPane();

		/*
		 * 
		 * Tab Buy Journey starts Here
		 */

		Tab buyJourney = new Tab();
		buyJourney.setText("Buy Journey");

		VBox tabOneVBox = new VBox(5);
		// tabOneVBox.setPadding(new Insets(10,0,0,0));

		HBox userHbox = new HBox(10);
		Label userListLabel = new Label("Select User");
		ComboBox<String> usersList = new ComboBox<String>();
		populateComboBox(userIds, usersList);
		userHbox.getChildren().addAll(userListLabel, usersList);

		HBox startStationHbox = new HBox(10);
		Label startStationListLabel = new Label("Select Start Station");
		ComboBox<String> startStationList = new ComboBox<String>();
		populateComboBox(stationsNames, startStationList);
		startStationHbox.getChildren().addAll(startStationListLabel, startStationList);

		HBox endStationHbox = new HBox(10);
		Label endStationListLabel = new Label("Select End Station");
		ComboBox<String> endStationList = new ComboBox<String>();
		populateComboBox(stationsNames, endStationList);
		endStationHbox.getChildren().addAll(endStationListLabel, endStationList);

		HBox daysHbox = new HBox(10);
		Label dayListLabel = new Label("Select Day");
		ComboBox<String> daysList = new ComboBox<String>();
		ArrayList<String> days = new ArrayList<String>();
		for (Days day : Days.values()) {
			days.add(day.toString());
		}
		populateComboBox(days, daysList);
		daysHbox.getChildren().addAll(dayListLabel, daysList);

		HBox startTimeHbox = new HBox(10);
		Label startTimeLabel = new Label("Enter start time");
		TextField startTimeTextField = new TextField();
		startTimeHbox.getChildren().addAll(startTimeLabel, startTimeTextField);

		HBox endTimeHbox = new HBox(10);
		Label endTimeLabel = new Label("Enter end time");
		TextField endTimeTextField = new TextField();
		endTimeHbox.getChildren().addAll(endTimeLabel, endTimeTextField);

		Label messageBoxLabel = new Label("Status Box");
		TextArea messageBoxTextArea = new TextArea();

		HBox buttonsHbox = new HBox(10);
		Button btnPurchase = new Button("Purchase");
		btnPurchase.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (Utils.usersList.size() > 0) {
					int myTiIndex = 0;
					int day = 0;
					int startTime = 0;
					int endTime = 0;
					int startStationIndex = 0;
					int endStationIndex = 0;
					String userId = usersList.getSelectionModel().getSelectedItem();
					myTiIndex = getMyTiIndex(userId, Utils.myTis);
					day = daysList.getSelectionModel().getSelectedIndex();
					startTime = Integer.parseInt(startTimeTextField.getText());
					endTime = Integer.parseInt(startTimeTextField.getText());
					

					startStationIndex = startStationList.getSelectionModel().getSelectedIndex();

					endStationIndex = endStationList.getSelectionModel().getSelectedIndex();
					// selectpass
					TravelPass pass = null;
					TravelPass ifAllDayPass = null;
					ZonesEnum zone = null;
					boolean isAllDay = false;
					boolean isSameZone = false;
					if (isSameZone(startStationIndex, endStationIndex, stations)) {
						if ((endTime - startTime) <= 2
								&& stations.get(startStationIndex).getZone() == ZonesEnum.ZONE_ONE) {
							pass = new ZoneOne2Hour();
							ifAllDayPass = new ZoneOneAllDay();
							isSameZone = true;
							zone = ZonesEnum.ZONE_ONE;
						} else if ((endTime - startTime) > 2
								&& stations.get(startStationIndex).getZone() == ZonesEnum.ZONE_ONE) {
							pass = new ZoneOneAllDay();
							isSameZone = true;

							isAllDay = true;
							zone = ZonesEnum.ZONE_ONE;
						} else if ((endTime - startTime) <= 2
								&& stations.get(startStationIndex).getZone() == ZonesEnum.ZONE_TWO) {
							pass = new ZoneTwo2Hour();
							isSameZone = true;
							ifAllDayPass = new ZoneOneAllDay();
							zone = ZonesEnum.ZONE_TWO;
						} else if ((endTime - startTime) > 2
								&& stations.get(startStationIndex).getZone() == ZonesEnum.ZONE_TWO) {
							pass = new ZoneTwoAllDay();
							isSameZone = true;

							isAllDay = true;
							zone = ZonesEnum.ZONE_TWO;
						}
					} else {
						if ((endTime - startTime) <= 2) {
							pass = new ZoneOneTwo2Hour();
							ifAllDayPass = new ZoneOneTwoAllDay();
							isSameZone = false;
							zone = ZonesEnum.ZONE_BOTH;

						} else if ((endTime - startTime) > 2) {
							pass = new ZoneOneTwoAllDay();
							isSameZone = false;

							isAllDay = true;
							zone = ZonesEnum.ZONE_BOTH;

						}
					}
					if (journeys.size() > 0) {
						if (isSameZone) {
							int check = checkZoneAlreadyBooked(journeys, userId, zone, Days.values()[day]);
							if (check != -1) {
								if (journeys.get(check).isAllDay()) {
									messageBoxTextArea.setText(
											"User has already booked a journey in this zone and day for all day");
								} else {
									if (isAllDay) {
										double difference = pass.getPrice() - journeys.get(check).getPass().getPrice();
										double myTiCredit = journeys.get(check).getMyTi().getMyTi();
										journeys.get(check).getMyTi().setMyTi(myTiCredit - difference);
										journeys.get(check).setPass(pass);
										journeys.get(check).setAllDay(true);
										stations.get(endStationIndex)
												.setVisitsEnd(stations.get(endStationIndex).getVisitsEnd() + 1);
										stations.get(startStationIndex)
												.setVisitsStart(stations.get(startStationIndex).getVisitsStart() + 1);

										messageBoxTextArea.setText("Journey updated");
									} else {
										if ((pass.getPrice() + journeys.get(check).getPass().getPrice() < ifAllDayPass
												.getPrice())) {
											journeys.add(new Journey(pass, Utils.myTis.get(myTiIndex), startTime,
													endTime, Days.values()[day], stations.get(endStationIndex),
													stations.get(startStationIndex), isSameZone, isAllDay));
											Utils.myTis.get(myTiIndex)
													.setMyTi(Utils.myTis.get(myTiIndex).getMyTi() - pass.getPrice());

											messageBoxTextArea.setText("Journey created");
										} else {
											double difference = pass.getPrice()
													- journeys.get(check).getPass().getPrice();
											double myTiCredit = journeys.get(check).getMyTi().getMyTi();
											journeys.get(check).getMyTi().setMyTi(myTiCredit - difference);
											journeys.get(check).setPass(pass);
											journeys.get(check).setAllDay(true);
											stations.get(endStationIndex)
													.setVisitsEnd(stations.get(endStationIndex).getVisitsEnd() + 1);
											stations.get(startStationIndex).setVisitsStart(
													stations.get(startStationIndex).getVisitsStart() + 1);

											messageBoxTextArea.setText("Journey Updated");
										}
									}
								}
							} else {
								journeys.add(new Journey(pass, Utils.myTis.get(myTiIndex), startTime, endTime,
										Days.values()[day], stations.get(endStationIndex),
										stations.get(startStationIndex), true, isAllDay));
								Utils.myTis.get(myTiIndex)
										.setMyTi(Utils.myTis.get(myTiIndex).getMyTi() - pass.getPrice());
								stations.get(endStationIndex)
										.setVisitsEnd(stations.get(endStationIndex).getVisitsEnd() + 1);
								stations.get(startStationIndex)
										.setVisitsStart(stations.get(startStationIndex).getVisitsStart() + 1);

								messageBoxTextArea.setText("Journey created");
							}
						} else {
							int check = checkZoneAlreadyBooked(journeys, userId, zone, Days.values()[day]);
							if (check != -1) {
								if (journeys.get(check).isAllDay()) {
									messageBoxTextArea.setText(
											"User has already booked a journey in the zones and day for all day");
								} else {
									if (isAllDay) {
										double difference = pass.getPrice() - journeys.get(check).getPass().getPrice();
										double myTiCredit = journeys.get(check).getMyTi().getMyTi();
										journeys.get(check).getMyTi().setMyTi(myTiCredit - difference);
										journeys.get(check).setPass(pass);
										journeys.get(check).setAllDay(true);
										stations.get(endStationIndex)
												.setVisitsEnd(stations.get(endStationIndex).getVisitsEnd() + 1);
										stations.get(startStationIndex)
												.setVisitsStart(stations.get(startStationIndex).getVisitsStart() + 1);

										messageBoxTextArea.setText("Journey Updated");
									} else {
										if ((pass.getPrice() + journeys.get(check).getPass().getPrice() < ifAllDayPass
												.getPrice())) {
											journeys.add(new Journey(pass, Utils.myTis.get(myTiIndex), startTime,
													endTime, Days.values()[day], stations.get(endStationIndex),
													stations.get(startStationIndex), isSameZone, isAllDay));
											Utils.myTis.get(myTiIndex)
													.setMyTi(Utils.myTis.get(myTiIndex).getMyTi() - pass.getPrice());

											stations.get(endStationIndex)
													.setVisitsEnd(stations.get(endStationIndex).getVisitsEnd() + 1);
											stations.get(startStationIndex).setVisitsStart(
													stations.get(startStationIndex).getVisitsStart() + 1);

											messageBoxTextArea.setText("Journey created");
										} else {
											double difference = pass.getPrice()
													- journeys.get(check).getPass().getPrice();
											double myTiCredit = journeys.get(check).getMyTi().getMyTi();
											journeys.get(check).getMyTi().setMyTi(myTiCredit - difference);
											journeys.get(check).setPass(pass);
											journeys.get(check).setAllDay(true);
											stations.get(endStationIndex)
													.setVisitsEnd(stations.get(endStationIndex).getVisitsEnd() + 1);
											stations.get(startStationIndex).setVisitsStart(
													stations.get(startStationIndex).getVisitsStart() + 1);

											messageBoxTextArea.setText("Journey Updated");
										}
									}
								}
							} else {
								journeys.add(new Journey(pass, Utils.myTis.get(myTiIndex), startTime, endTime,
										Days.values()[day], stations.get(endStationIndex),
										stations.get(startStationIndex), isSameZone, isAllDay));
								Utils.myTis.get(myTiIndex)
										.setMyTi(Utils.myTis.get(myTiIndex).getMyTi() - pass.getPrice());

								stations.get(endStationIndex)
										.setVisitsEnd(stations.get(endStationIndex).getVisitsEnd() + 1);
								stations.get(startStationIndex)
										.setVisitsStart(stations.get(startStationIndex).getVisitsStart() + 1);

								messageBoxTextArea.setText("Journey created");
							}
						}

					} else {
						journeys.add(new Journey(pass, Utils.myTis.get(myTiIndex), startTime, endTime,
								Days.values()[day], stations.get(endStationIndex), stations.get(startStationIndex),
								isSameZone, isAllDay));
						Utils.myTis.get(myTiIndex).setMyTi(Utils.myTis.get(myTiIndex).getMyTi() - pass.getPrice());

						stations.get(endStationIndex).setVisitsEnd(stations.get(endStationIndex).getVisitsEnd() + 1);
						stations.get(startStationIndex)
								.setVisitsStart(stations.get(startStationIndex).getVisitsStart() + 1);
						messageBoxTextArea.setText("Journey created");

					}

				} else {
					messageBoxTextArea.setText("No Users In the list. Create a user before buying a journey.");
				}
			}
		});
		Button btnCancel = new Button("Cancel");
		btnCancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				startTimeTextField.setText("");
				endTimeTextField.setText("");
				
				messageBoxTextArea.setText("You cancelled the process");
			}
		});
		buttonsHbox.getChildren().addAll(btnPurchase, btnCancel);

		tabOneVBox.getChildren().addAll(userHbox, startStationHbox, endStationHbox, daysHbox, startTimeHbox,
				endTimeHbox, messageBoxLabel, messageBoxTextArea, buttonsHbox);
		tabOneVBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: black;");

		buyJourney.setContent(tabOneVBox);
		/*
		 * 
		 * 
		 * Tab Buy Journey Finish Here
		 * 
		 */

		/*
		 * 
		 * 
		 * Tab Manage users Starts Here
		 * 
		 */

		TextArea statusMessage = new TextArea();
		statusMessage.setPrefHeight(40);
		Tab manageUsers = new Tab();
		manageUsers.setText("Manage Users");

		VBox tabTwoVBox = new VBox(5);
		tabTwoVBox.setPadding(new Insets(10, 0, 0, 0));

		HBox userDetailHbox = new HBox(10);

		TableView<User> tableView = new TableView<User>();
		tableView.setPrefWidth(150);
		tableView.setPrefHeight(200);

		TableColumn<User, String> column1 = new TableColumn<>("Id");

		column1.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<User, String> column2 = new TableColumn<>("Name");

		column2.setCellValueFactory(new PropertyValueFactory<>("name"));

		tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		// Still to do
		populateTableView(tableView);
		
		TextArea userDetailTextArea = new TextArea();
		userDetailTextArea.setPrefWidth(200);
		userDetailTextArea.setPrefHeight(250);
		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				String id = tableView.getSelectionModel().getSelectedItem().getId();
				for (MyTi myti : Utils.myTis) {
					if (myti.getUser().getId().equals(id)) {
						String data = "User Details:\n" + "User Id : " + id + "User Name : " + myti.getUser().getName()
								+ "\n" + "User Email : " + myti.getUser().getEmail() + "\n" + "User Type : "
								+ myti.getUser().getType() + "\n" + "Current Credit : " + myti.currentCredit();
						userDetailTextArea.setText(data);
					}
				}
			}
		});

		VBox addCreditVBox = new VBox(10);
		Label addCreditLabel = new Label("Enter Credit to enter");
		TextField txtAddCredit = new TextField();
		Button btnAddCredit = new Button("Add Credit");
		btnAddCredit.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				String id = tableView.getSelectionModel().getSelectedItem().getId();
				
				for (MyTi myti : Utils.myTis) {
					if(myti.getUser().getId().equals(id)) {
						myti.setMyTi(Double.parseDouble(txtAddCredit.getText()));
						txtAddCredit.setText("");
						statusMessage.setText("Credit Updated");
							if (myti.getUser().getId().equals(id)) {
								String data = "User Details:\n" + "User Id : " + id + "User Name : " + myti.getUser().getName()
										+ "\n" + "User Email : " + myti.getUser().getEmail() + "\n" + "User Type : "
										+ myti.getUser().getType() + "\n" + "Current Credit : " + myti.currentCredit();
								userDetailTextArea.setText(data);
							}
						

					}
				}	
			}
		});
		addCreditVBox.getChildren().addAll(addCreditLabel, txtAddCredit, btnAddCredit);

		userDetailHbox.getChildren().addAll(tableView, userDetailTextArea, addCreditVBox);
		userDetailHbox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: black;");

		VBox addUserVBox = new VBox(10);

		HBox userIdHbox = new HBox(10);
		Label userIdLabel = new Label("Enter user id");
		TextField userIdTextField = new TextField();
		userIdHbox.getChildren().addAll(userIdLabel, userIdTextField);

		HBox userEmailHbox = new HBox(10);
		Label userEmailLabel = new Label("Enter user email");
		TextField userEmailTextField = new TextField();
		userEmailHbox.getChildren().addAll(userEmailLabel, userEmailTextField);

		HBox userNameHbox = new HBox(10);
		Label userNameLabel = new Label("Enter user name");
		TextField userNameTextField = new TextField();
		userNameHbox.getChildren().addAll(userNameLabel, userNameTextField);

		HBox userTypebox = new HBox(10);
		Label userTypeLabel = new Label("Select User type");
		ComboBox<String> userTypes = new ComboBox<String>();
		ArrayList<String> types = new ArrayList<String>();
		for (UserTypeEnum type : UserTypeEnum.values()) {
			types.add(type.toString());
		}
		populateComboBox(types, userTypes);
		userTypebox.getChildren().addAll(userTypeLabel, userTypes);

		Button btnAddUser = new Button("Add User");
		btnAddUser.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				String id = "";
				id = userIdTextField.getText();
				String name = userNameTextField.getText();
				String email = userEmailTextField.getText();
				UserTypeEnum userType = getUserType(userTypes);
				if (!searchId(id, Utils.usersList)) {
				
					User user = new User(id, email, name, userType);
					Utils.usersList.put(id, user);
					MyTi myTi = new MyTi(user);
					Utils.myTis.add(myTi);
					statusMessage.setText("User Created");
					userIds.clear();
					usersList.getItems().clear();
					usersListTabThree.getItems().clear();
					for (Map.Entry<String, User> entry : Utils.usersList.entrySet()) {
						userIds.add(entry.getKey());
						usersList.getItems().add(entry.getValue().getId());
						usersListTabThree.getItems().add(entry.getValue().getId());
					}
					populateTableView(tableView);
					userIdTextField.setText("");
					userNameTextField.setText("");
					userEmailTextField.setText("");
					
					
					
					
				} else {
						statusMessage.setText("Id Already in use, please enter a different id.");
				}
				

			}
		});
		

		addUserVBox.getChildren().addAll(userIdHbox, userEmailHbox, userNameHbox, userTypebox, btnAddUser,
				statusMessage);

		addUserVBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: black;");

		tabTwoVBox.getChildren().addAll(userDetailHbox, addUserVBox);

		manageUsers.setContent(tabTwoVBox);
		/*
		 * 
		 * 
		 * Tab manage users Finish Here
		 * 
		 */

		/*
		 * 
		 * Tab display reports starts here
		 * 
		 */
		Tab displayReports = new Tab();
		TextArea reportsArea = new TextArea();
		displayReports.setText("Display Reports");
		VBox tabThreeVBox = new VBox(5);
		tabThreeVBox.setPadding(new Insets(10, 0, 0, 0));

		HBox userHboxTabThree = new HBox(10);
		Label userListLabelTabThree = new Label("Select User");
		usersListTabThree = new ComboBox<String>();
		populateComboBox(userIds, usersListTabThree);
		userHboxTabThree.getChildren().addAll(userListLabelTabThree, usersListTabThree);

		Button btnDisplayJourney = new Button("Display User Journeys");
		btnDisplayJourney.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				String id = usersListTabThree.getSelectionModel().getSelectedItem();
				String data = "";
				for (Journey journey : journeys) {
					if(journey.getMyTi().getUser().getId().equals(id)) {
						
						data = "User Name : " + journey.getMyTi().getUser().getName() +"\n"+
								"Journey Day : " + journey.getDay() + "\n" +
								"Journey Time : " + journey.getStartTime() + " to " + journey.getEndTime() +"\n"+
								"Journey Travel Pass: " + journey.getPass() +"\n"+
								"Journey Stations : " + journey.getStart() + " to " + journey.getEnd()+"\n";
					}
				}
				reportsArea.setText(data);
			}
		});
		reportsArea.setEditable(false);

		tabThreeVBox.getChildren().addAll(userHboxTabThree, btnDisplayJourney, reportsArea);
		tabThreeVBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: black;");

		displayReports.setContent(tabThreeVBox);

		// Adding tabs to the tab pane
		tabPane.getTabs().addAll(buyJourney, manageUsers, displayReports);

		// Setting anchor pane as the layout
		pane = new VBox();
		Button btnSave = new Button("Save");
		btnSave.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				Utils.populateLinesArrayListToWrite();
				try {
					Utils.writeToFile(list.get(1));
					Alert alertBox = new Alert(AlertType.CONFIRMATION);
					 
				
					 
	                alertBox.setContentText("Data Saved");
	 
	                alertBox.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Button btnQuit = new Button("Quit (without saving)");
		btnQuit.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				Platform.exit();
			}
		});
//		AnchorPane.setTopAnchor(tabPane, 15.0);
//		AnchorPane.setRightAnchor(tabPane, 15.0);
//		AnchorPane.setBottomAnchor(tabPane, 15.0);
//		AnchorPane.setLeftAnchor(tabPane, 15.0);
//		AnchorPane.setTopAnchor(btnSave, 15.0);
//		AnchorPane.setRightAnchor(btnSave, 15.0);
//		AnchorPane.setBottomAnchor(btnSave, 15.0);
//		AnchorPane.setLeftAnchor(btnSave, 15.0);
		HBox buttonsHBox = new HBox(20);
		Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);

        Region region2 = new Region();
        HBox.setHgrow(region2, Priority.ALWAYS);

		buttonsHBox.getChildren().addAll(region1, btnSave , btnQuit , region2);
		pane.getChildren().addAll(buttonsHBox, tabPane);
	}

	private void populateTableView(TableView<User> tableView) {
		tableView.getItems().clear();
		for (Map.Entry<String, User> entry : Utils.usersList.entrySet()) {
			tableView.getItems().add(entry.getValue());
		}
	
	}

	void populateComboBox(ArrayList<String> data, ComboBox<String> cb) {
		for (String string : data) {
			cb.getItems().add(string);
		}
	}

	private static void createTestStations(ArrayList<Station> stations) {
		stations.add(new Station("Central", ZonesEnum.ZONE_ONE));
		stations.add(new Station("Flagstaff", ZonesEnum.ZONE_ONE));
		stations.add(new Station("Richmond", ZonesEnum.ZONE_ONE));
		stations.add(new Station("Lilydale", ZonesEnum.ZONE_TWO));
		stations.add(new Station("Epping", ZonesEnum.ZONE_TWO));

	}

	public static int getMyTiIndex(String id, ArrayList<MyTi> myTis) {

		for (MyTi myTi : myTis) {
			if (myTi.getUser().getId().equals(id)) {
				return myTis.indexOf(myTi);
			}
		}
		return -1;
	}

	public static boolean isSameZone(int startStationIndex, int endStationIndex, ArrayList<Station> stations) {
		if (stations.get(startStationIndex).getZone() == stations.get(endStationIndex).getZone()) {
			return true;
		}
		return false;
	}

	private static int checkZoneAlreadyBooked(ArrayList<Journey> journeys, String userId, ZonesEnum zone, Days day) {
		for (Journey journey : journeys) {
			if (journey.getDay() == day && journey.getMyTi().getUser().getId().equals(userId)
					&& journey.getPass().getZone() == zone) {
				return journeys.indexOf(journey);
			}
		}
		return -1;
	}

	public static boolean searchId(String id, Map<String, User> usersList) {
		for (String key : usersList.keySet()) {
			if (key.equals(id)) {
				return true;
			}
		}
		return false;
	}

	public static UserTypeEnum getUserType(ComboBox<String> userTypes) {
		int type = userTypes.getSelectionModel().getSelectedIndex();
		switch (type) {
		case 0:
			return UserTypeEnum.SENIOR;

		case 1:
			return UserTypeEnum.JUNIOR;
		case 2:
			return UserTypeEnum.ADULT;
		}
		return null;

	}
	public static String checkCreditConstraints(MyTi myTi, double crd) {
		if (!((crd + myTi.getMyTi()) <= 100)) {
			return "Sorry, the max amount of credit allowed is $100.00";

		} else if (!(crd % 5 == 0)) {
			return "Sorry, you can only add multiples of $5.00";

		}
		return null;
	}
	public static boolean canBuyPass(ArrayList<MyTi> myTis, double price, int userId) {

		if (price > myTis.get(userId).getMyTi()) {

			return false;

		} else {
			return true;
		}
	}

	public Pane getRoot() {
		return pane;
	}

}
