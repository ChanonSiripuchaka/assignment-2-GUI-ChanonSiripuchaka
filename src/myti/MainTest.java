package myti;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import gui.TitleWindow;

class MainTest {
	ArrayList<Station> stations = new ArrayList<>();
	
	@Test
	public void MyTiSystemIsSameZoneTest() {
		stations.add(new Station("Central", ZonesEnum.ZONE_ONE));
		stations.add(new Station("Flagstaff", ZonesEnum.ZONE_ONE));
		stations.add(new Station("Richmond", ZonesEnum.ZONE_ONE));
		stations.add(new Station("Lilydale", ZonesEnum.ZONE_TWO));
		stations.add(new Station("Epping", ZonesEnum.ZONE_TWO));
		
		
		assertEquals(TitleWindow.isSameZone(0, 1, stations),true);
		
		
	}
	
	@Test
	public void MyTiSystemCanBuyPassTest() {
		Map<String, User> usersList = new HashMap<String, User>();
		
		User userTestOne = new User("lc", "lawrence.cavedon@rmit.edu.au", "Lawrence Cavedon", UserTypeEnum.SENIOR);
		User userTestTwo = new User("vm", "vuhuy.mai@rmit.edu.au", "Xiang Li", UserTypeEnum.ADULT);
		usersList.put(userTestOne.getId(), userTestOne);
		usersList.put(userTestTwo.getId(), userTestTwo);
		ArrayList<MyTi> myTis = new ArrayList<MyTi>();
		myTis .add(new MyTi(userTestOne));
		myTis.add(new MyTi(userTestTwo));

		assertEquals(TitleWindow.canBuyPass(myTis, 12, 0), false);

	}
	
	@Test
	public void getMyTiIndexTest() {
		Map<String, User> usersList = new HashMap<String, User>();
		
		User userTestOne = new User("lc", "lawrence.cavedon@rmit.edu.au", "Lawrence Cavedon", UserTypeEnum.SENIOR);
		User userTestTwo = new User("vm", "vuhuy.mai@rmit.edu.au", "Xiang Li", UserTypeEnum.ADULT);
		usersList.put(userTestOne.getId(), userTestOne);
		usersList.put(userTestTwo.getId(), userTestTwo);
		ArrayList<MyTi> myTis = new ArrayList<MyTi>();
		myTis .add(new MyTi(userTestOne));
		myTis.add(new MyTi(userTestTwo));

		assertEquals(TitleWindow.getMyTiIndex("vm", myTis), 1);
}
	

	@Test public void chargeCreditMax100Test() {
		Map<String, User> usersList = new HashMap<String, User>();
		
		User userTestOne = new User("lc", "lawrence.cavedon@rmit.edu.au", "Lawrence Cavedon", UserTypeEnum.SENIOR);
		User userTestTwo = new User("vm", "vuhuy.mai@rmit.edu.au", "Xiang Li", UserTypeEnum.ADULT);
		usersList.put(userTestOne.getId(), userTestOne);
		usersList.put(userTestTwo.getId(), userTestTwo);
		ArrayList<MyTi> myTis = new ArrayList<MyTi>();
		myTis .add(new MyTi(userTestOne));
		myTis.add(new MyTi(userTestTwo));

	    String result =	TitleWindow.checkCreditConstraints(myTis.get(0), 200);
		assertEquals("Sorry, the max amount of credit allowed is $100.00", result);

        
	}

}
	