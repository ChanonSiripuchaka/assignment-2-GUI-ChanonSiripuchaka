package myti;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import fileio.Utils;

public class MyTiSystem {

	public static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		ArrayList<Journey> journeys = new ArrayList<>();
		ArrayList<Station> stations = new ArrayList<Station>();
		createTestStations(stations);
		NumberFormat df = new DecimalFormat("#0.00");

		while (true) {

			System.out.println("Choose an option:");
			System.out.println("1. Buy a Journey for a User");
			System.out.println("2. Recharge a MyTi card for a User");
			System.out.println("3. Show remaining credit for a User");
			System.out.println("4. Print User Reports");
			System.out.println("5. Update pricing");
			System.out.println("6. Show Station statistics");
			System.out.println("7. Add a new User");
			System.out.println("8. Quit");
			System.out.print("Please make a selection : ");
			if (input.hasNextInt()) {
				int userSelection = input.nextInt();
				System.out.println();

				switch (userSelection) {
				case 1: // Journey Create
					if (Utils.usersList.size() > 0) {
							int myTiIndex = 0;
							int day = 0;
							int startTime = 0;
							int endTime = 0;
							int startStationIndex = 0;
							int endStationIndex = 0;
							printUsersIds(Utils.usersList);
							if (input.hasNext()) {
								String userId = input.next();
								myTiIndex = getMyTiIndex(userId, Utils.myTis);
								printDays();
								if (input.hasNextInt()) {
									day = input.nextInt();
									System.out.println("Enter start time for the journey(00 to 24): ");
									startTime = input.nextInt();
									System.out.println("Enter end time for the journey(00 to 24): ");
									endTime = input.nextInt();

									printStationsForSelection(stations);
									System.out.println("Choose start station: ");
									if (input.hasNextInt()) {
										startStationIndex = input.nextInt();

										printStationsForSelection(stations);
										System.out.println("Choose end station: ");
										if (input.hasNextInt()) {
											endStationIndex = input.nextInt();
											// selectpass
											TravelPass pass = null;
											TravelPass ifAllDayPass = null;
											ZonesEnum zone = null;
											boolean isAllDay = false;
											boolean isSameZone = false;
											if (isSameZone(startStationIndex, endStationIndex, stations)) {
												if ((endTime - startTime) <= 2 && stations.get(startStationIndex)
														.getZone() == ZonesEnum.ZONE_ONE) {
													pass = new ZoneOne2Hour();
													ifAllDayPass = new ZoneOneAllDay();
													isSameZone = true;
													zone = ZonesEnum.ZONE_ONE;
												} else if ((endTime - startTime) > 2 && stations.get(startStationIndex)
														.getZone() == ZonesEnum.ZONE_ONE) {
													pass = new ZoneOneAllDay();
													isSameZone = true;

													isAllDay = true;
													zone = ZonesEnum.ZONE_ONE;
												} else if ((endTime - startTime) <= 2 && stations.get(startStationIndex)
														.getZone() == ZonesEnum.ZONE_TWO) {
													pass = new ZoneTwo2Hour();
													isSameZone = true;
													ifAllDayPass = new ZoneOneAllDay();
													zone = ZonesEnum.ZONE_TWO;
												} else if ((endTime - startTime) > 2 && stations.get(startStationIndex)
														.getZone() == ZonesEnum.ZONE_TWO) {
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
													int check = checkZoneAlreadyBooked(journeys, userId, zone,
															Days.values()[day]);
													if (check != -1) {
														if (journeys.get(check).isAllDay()) {
															System.out.println(
																	"User has already booked a journey in this zone and day for all day");
														} else {
															if (isAllDay) {
																double difference = pass.getPrice()
																		- journeys.get(check).getPass().getPrice();
																double myTiCredit = journeys.get(check).getMyTi()
																		.getMyTi();
																journeys.get(check).getMyTi()
																		.setMyTi(myTiCredit - difference);
																journeys.get(check).setPass(pass);
																journeys.get(check).setAllDay(true);
																stations.get(endStationIndex).setVisitsEnd(
																		stations.get(endStationIndex).getVisitsEnd()
																				+ 1);
																stations.get(startStationIndex).setVisitsStart(
																		stations.get(startStationIndex).getVisitsStart()
																				+ 1);

																System.out.println("Journey updated");
															} else {
																if ((pass.getPrice() + journeys.get(check).getPass()
																		.getPrice() < ifAllDayPass.getPrice())) {
																	journeys.add(new Journey(pass, Utils.myTis.get(myTiIndex),
																			startTime, endTime, Days.values()[day],
																			stations.get(endStationIndex),
																			stations.get(startStationIndex), isSameZone,
																			isAllDay));
																	Utils.myTis.get(myTiIndex).setMyTi(Utils.myTis.get(myTiIndex).getMyTi()-pass.getPrice());

																	System.out.println("Journey created");
																} else {
																	double difference = pass.getPrice()
																			- journeys.get(check).getPass().getPrice();
																	double myTiCredit = journeys.get(check).getMyTi()
																			.getMyTi();
																	journeys.get(check).getMyTi()
																			.setMyTi(myTiCredit - difference);
																	journeys.get(check).setPass(pass);
																	journeys.get(check).setAllDay(true);
																	stations.get(endStationIndex).setVisitsEnd(
																			stations.get(endStationIndex).getVisitsEnd()
																					+ 1);
																	stations.get(startStationIndex).setVisitsStart(
																			stations.get(startStationIndex)
																					.getVisitsStart() + 1);

																	System.out.println("Journey Updated");
																}
															}
														}
													} else {
														journeys.add(new Journey(pass, Utils.myTis.get(myTiIndex), startTime,
																endTime, Days.values()[day],
																stations.get(endStationIndex),
																stations.get(startStationIndex), true, isAllDay));
														Utils.myTis.get(myTiIndex).setMyTi(Utils.myTis.get(myTiIndex).getMyTi()-pass.getPrice());
														stations.get(endStationIndex).setVisitsEnd(
																stations.get(endStationIndex).getVisitsEnd() + 1);
														stations.get(startStationIndex).setVisitsStart(
																stations.get(startStationIndex).getVisitsStart() + 1);

														System.out.println("Journey created");
													}
												} else {
													int check = checkZoneAlreadyBooked(journeys, userId, zone,
															Days.values()[day]);
													if (check != -1) {
														if (journeys.get(check).isAllDay()) {
															System.out.println(
																	"User has already booked a journey in the zones and day for all day");
														} else {
															if (isAllDay) {
																double difference = pass.getPrice()
																		- journeys.get(check).getPass().getPrice();
																double myTiCredit = journeys.get(check).getMyTi()
																		.getMyTi();
																journeys.get(check).getMyTi()
																		.setMyTi(myTiCredit - difference);
																journeys.get(check).setPass(pass);
																journeys.get(check).setAllDay(true);
																stations.get(endStationIndex).setVisitsEnd(
																		stations.get(endStationIndex).getVisitsEnd()
																				+ 1);
																stations.get(startStationIndex).setVisitsStart(
																		stations.get(startStationIndex).getVisitsStart()
																				+ 1);

																System.out.println("Journey Updated");
															} else {
																if ((pass.getPrice() + journeys.get(check).getPass()
																		.getPrice() < ifAllDayPass.getPrice())) {
																	journeys.add(new Journey(pass, Utils.myTis.get(myTiIndex),
																			startTime, endTime, Days.values()[day],
																			stations.get(endStationIndex),
																			stations.get(startStationIndex), isSameZone,
																			isAllDay));
																	Utils.myTis.get(myTiIndex).setMyTi(Utils.myTis.get(myTiIndex).getMyTi()-pass.getPrice());

																	stations.get(endStationIndex).setVisitsEnd(
																			stations.get(endStationIndex).getVisitsEnd()
																					+ 1);
																	stations.get(startStationIndex).setVisitsStart(
																			stations.get(startStationIndex)
																					.getVisitsStart() + 1);

																	System.out.println("Journey created");
																} else {
																	double difference = pass.getPrice()
																			- journeys.get(check).getPass().getPrice();
																	double myTiCredit = journeys.get(check).getMyTi()
																			.getMyTi();
																	journeys.get(check).getMyTi()
																			.setMyTi(myTiCredit - difference);
																	journeys.get(check).setPass(pass);
																	journeys.get(check).setAllDay(true);
																	stations.get(endStationIndex).setVisitsEnd(
																			stations.get(endStationIndex).getVisitsEnd()
																					+ 1);
																	stations.get(startStationIndex).setVisitsStart(
																			stations.get(startStationIndex)
																					.getVisitsStart() + 1);

																	System.out.println("Journey Updated");
																}
															}
														}
													} else {
														journeys.add(new Journey(pass, Utils.myTis.get(myTiIndex), startTime,
																endTime, Days.values()[day],
																stations.get(endStationIndex),
																stations.get(startStationIndex), isSameZone, isAllDay));
														Utils.myTis.get(myTiIndex).setMyTi(Utils.myTis.get(myTiIndex).getMyTi()-pass.getPrice());

														stations.get(endStationIndex).setVisitsEnd(
																stations.get(endStationIndex).getVisitsEnd() + 1);
														stations.get(startStationIndex).setVisitsStart(
																stations.get(startStationIndex).getVisitsStart() + 1);

														System.out.println("Journey created");
													}
												}

											} else {
												journeys.add(new Journey(pass, Utils.myTis.get(myTiIndex), startTime, endTime,
														Days.values()[day], stations.get(endStationIndex),
														stations.get(startStationIndex), isSameZone, isAllDay));
												Utils.myTis.get(myTiIndex).setMyTi(Utils.myTis.get(myTiIndex).getMyTi()-pass.getPrice());

												stations.get(endStationIndex)
														.setVisitsEnd(stations.get(endStationIndex).getVisitsEnd() + 1);
												stations.get(startStationIndex).setVisitsStart(
														stations.get(startStationIndex).getVisitsStart() + 1);
												System.out.println("Journey created");

											}
										} else {
											System.out.println("Invalid Input");

										}
									} else {
										System.out.println("Invalid Input");

									}

								} else {
									System.out.println("Invalid Input");
								}

							}

						
					} else {
						System.out.println("No Users In the list. Create a user before buying a journey.");
					}

					break;
				case 2: // Recharge MyTi
					if (Utils.usersList.size() > 0) {
						System.out.println("Recharge");
						printUsersIds(Utils.usersList);
						if (input.hasNext()) {
							String id = input.next();
							int myTiIndex = getMyTiIndex(id, Utils.myTis);
							System.out.print("How much do you want to add : ");

							String crdt = input.next();
							System.out.println();
							double crd = Double.parseDouble(crdt);

							while (!(((crd + Utils.myTis.get(myTiIndex).getMyTi()) <= 100) && (crd % 5 == 0))) {
								System.out.println(checkCreditConstraints(Utils.myTis.get(myTiIndex), crd));
								System.out.print("How much do you want to add : ");

								crdt = input.nextLine();
								System.out.println();
								crd = Double.parseDouble(crdt);
							}

							Utils.myTis.get(myTiIndex).setMyTi(Utils.myTis.get(myTiIndex).getMyTi() + crd);
							System.out.println(Utils.myTis.get(myTiIndex).currentCredit());
							System.out.println();
							break;

						} else {
							System.out.println("No Users In the list. Create a user before buying a journey.");
						}

					}

					break;
				case 3: // Show remaining Credit
					if (Utils.usersList.size() > 0) {
						printUsersIds(Utils.usersList);
						if (input.hasNext()) {
							String id = input.next();
							int myTiIndex = getMyTiIndex(id, Utils.myTis);
							if (myTiIndex != -1) {
								System.out.println(Utils.myTis.get(myTiIndex).currentCredit());
								System.out.println();
							} else {
								System.out.println("Wrong choice");
							}
						} else {
							System.out.println("Wrong choice");
						}

					} else {
						System.out.println("No Users In the list. Create a user before buying a journey.");
					}

					break;
				case 4: // print reports
					if (journeys.size() > 0) {
						for (Journey journey : journeys) {
							System.out.println(journey.toString());
						}
					} else {
						System.out.println("No journeys In the list. Create journeys before printing.");
					}

					break;
				case 5: // update pricing
					outer: while (true) {
						System.out.println("Choose an option:");
						System.out.println("1. Zone One - 2 Hour Pass");
						System.out.println("2. Zone One - All Day Pass");
						System.out.println("3. Zone Two - 2 Hour Pass");
						System.out.println("4. Zone Two - All Day Pass");
						System.out.println("5. Zone One/Two - 2 Hour Pass");
						System.out.println("6. Zone One/Two - All Day Pass");
						System.out.print("Please make a selection : ");
						if (input.hasNextInt()) {
							int selection = input.nextInt();
							switch (selection) {
							case 1:
								System.out.println("Current Price for the pass is: " + ZoneOne2Hour.price);
								System.out.print("Please Enter new price : ");
								if (input.hasNextDouble()) {
									double priceNew = input.nextDouble();
									ZoneOne2Hour.price = priceNew;
									System.out.println(
											"Price Changed. Updated Price for the pass is: " + ZoneOne2Hour.price);
									break outer;
								} else {
									System.out.println("Invalid input");
								}

								break;
							case 2:
								System.out.println("Current Price for the pass is: " + ZoneOneAllDay.price);
								System.out.print("Please Enter new price : ");
								if (input.hasNextDouble()) {
									double priceNew = input.nextDouble();
									ZoneOneAllDay.price = priceNew;
									System.out.println(
											"Price Changed. Updated Price for the pass is: " + ZoneOneAllDay.price);
									break outer;
								} else {
									System.out.println("Invalid input");
								}
								break;
							case 3:
								System.out.println("Current Price for the pass is: " + ZoneTwo2Hour.price);
								System.out.print("Please Enter new price : ");
								if (input.hasNextDouble()) {
									double priceNew = input.nextDouble();
									ZoneTwo2Hour.price = priceNew;
									System.out.println(
											"Price Changed. Updated Price for the pass is: " + ZoneTwo2Hour.price);
									break outer;
								} else {
									System.out.println("Invalid input");
								}
								break;
							case 4:
								System.out.println("Current Price for the pass is: " + ZoneTwoAllDay.price);
								System.out.print("Please Enter new price : ");
								if (input.hasNextDouble()) {
									double priceNew = input.nextDouble();
									ZoneTwoAllDay.price = priceNew;
									System.out.println(
											"Price Changed. Updated Price for the pass is: " + ZoneTwoAllDay.price);
									break outer;
								} else {
									System.out.println("Invalid input");
								}
								break;
							case 5:
								System.out.println("Current Price for the pass is: " + ZoneOneTwo2Hour.price);
								System.out.print("Please Enter new price : ");
								if (input.hasNextDouble()) {
									double priceNew = input.nextDouble();
									ZoneOneTwo2Hour.price = priceNew;
									System.out.println(
											"Price Changed. Updated Price for the pass is: " + ZoneOneTwo2Hour.price);
									break outer;
								} else {
									System.out.println("Invalid input");
								}
								break;
							case 6:
								System.out.println("Current Price for the pass is: " + ZoneOneTwoAllDay.price);
								System.out.print("Please Enter new price : ");
								if (input.hasNextDouble()) {
									double priceNew = input.nextDouble();
									ZoneOneTwoAllDay.price = priceNew;
									System.out.println(
											"Price Changed. Updated Price for the pass is: " + ZoneOneTwoAllDay.price);
									break outer;
								} else {
									System.out.println("Invalid input");
								}

								break;
							default:
								System.out.println("Wrong choice.");
								break;
							}
						} else {
							System.out.println("Invalid Input.");
						}

					}

					break;
				case 6: // show station stats
					System.out.println("Following are the stations stats:\n");
					for (Station station : stations) {
						System.out.println(station.toString());
					}

					break;
				case 7: // add new user
					String id = "";
					while (true) {
						System.out.println("Enter User Id: ");
						id = input.next();
						if (!searchId(id, Utils.usersList)) {
							break;
						} else {
							System.out.println("Id Already in use, please enter a different id.");
						}
					}
					System.out.println("Enter User Name: ");
					String name = input.next();
					System.out.println("Enter User Email: ");
					String email = input.next();
					UserTypeEnum userType = getUserType();
					User user = new User(id, email, name, userType);
					Utils.usersList.put(id, user);
					MyTi myTi = new MyTi(user);
					Utils.myTis.add(myTi);

					break;
				case 8: // exit

					System.out.println("Goodbye!");
					System.exit(0);

					break;
				default:
					System.out.println("Sorry, that is an invalid option!");
					System.out.println();
					break;

				}
			} else {
				System.out.println("Only numeric input is supported");
			}
		}

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

	public static boolean isSameZone(int startStationIndex, int endStationIndex, ArrayList<Station> stations) {
		if (stations.get(startStationIndex).getZone() == stations.get(endStationIndex).getZone()) {
			return true;
		}
		return false;
	}

	private static void printStationsForSelection(ArrayList<Station> stations) {
		System.out.println("Select Station: ");
		for (int i = 0; i < stations.size(); i++) {
			System.out.println(i + ": " + stations.get(i).getName());
		}
	}

	private static void printDays() {
		Days[] days = Days.values();
		for (int i = 0; i < days.length; i++) {
			System.out.println(i + ": " + days[i]);
		}
		System.out.println("Choose day: ");
	}

	

	public static boolean searchId(String id, Map<String, User> usersList) {
		for (String key : usersList.keySet()) {
			if (key.equals(id)) {
				return true;
			}
		}
		return false;
	}

	public static int getMyTiIndex(String id, ArrayList<MyTi> myTis) {

		for (MyTi myTi : myTis) {
			if (myTi.getUser().getId().equals(id)) {
				return myTis.indexOf(myTi);
			}
		}
		return -1;
	}

	public static void printUsersIds(Map<String, User> usersList) {
		System.out.println("Select User: ");
		for (Map.Entry<String, User> entry : usersList.entrySet()) {
			String key = entry.getKey();
			User value = entry.getValue();
			System.out.println("Id: " + key + ", Username: " + value.getName());
		}

	}

	public static UserTypeEnum getUserType() {
		while (true) {

			System.out.println("Select User Type:");
			System.out.println("1: Senior");
			System.out.println("2: Junior");
			System.out.println("3: Adult");
			if (input.hasNextInt()) {
				int type = input.nextInt();
				switch (type) {
				case 1:
					return UserTypeEnum.SENIOR;

				case 2:
					return UserTypeEnum.JUNIOR;
				case 3:
					return UserTypeEnum.ADULT;
				default:
					System.out.println("Wrong input");
					break;
				}
			}

			else {
				System.out.println("Wrong input, only numerical inputs allowed.");
			}

		}
	}

	private static void createTestStations(ArrayList<Station> stations) {
		stations.add(new Station("Central", ZonesEnum.ZONE_ONE));
		stations.add(new Station("Flagstaff", ZonesEnum.ZONE_ONE));
		stations.add(new Station("Richmond", ZonesEnum.ZONE_ONE));
		stations.add(new Station("Lilydale", ZonesEnum.ZONE_TWO));
		stations.add(new Station("Epping", ZonesEnum.ZONE_TWO));

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

	// printing menu options
	public static void printOptions() {
		System.out.println("Choose an option:");
		System.out.println("1. Buy a Journey for a User");
		System.out.println("2. Recharge a MyTi card for a User");
		System.out.println("3. Show remaining credit for a User");
		System.out.println("4. Print User Reports");
		System.out.println("5. Update pricing");
		System.out.println("6. Show Station statistics");
		System.out.println("7. Add a new User");
		System.out.println("8. Quit");
		System.out.print("Please make a selection : ");

	}
}
