package fileio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

import myti.MyTi;
import myti.User;
import myti.UserTypeEnum;
import myti.ZoneOne2Hour;
import myti.ZoneOneAllDay;
import myti.ZoneOneTwo2Hour;
import myti.ZoneOneTwoAllDay;

public class Utils {
	
	public static Map<String, User> usersList = new HashMap<String, User>();
	public static ArrayList<MyTi> myTis = new ArrayList<MyTi>();
	static ArrayList<String> readFromFileStrings = new ArrayList<>();
	static int pricesindex = 0;
	
	
	public static void readLines(String filename) {

		Scanner input = null;
		try {
			input = new Scanner(new File(filename));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (input.hasNext()) {
			String line = input.nextLine();
			readFromFileStrings.add(line);
		}
		input.close();
	
	}

	public static void createUsersFromReadLines(){
		for(String line : readFromFileStrings) {
			if(line.charAt(0)!='#'&&line.charAt(1)!='u') {
				String [] parts = line.split(":");
				createUser(parts);
			}
			if(line.equals("#prices")) {
				pricesindex = readFromFileStrings.indexOf(line);
				break;
			}
		}
		
		
	}
	
	
	public static void readPrices() {
		String [] partsZoneOne2Hour = readFromFileStrings.get(pricesindex+1).split(":");
		ZoneOne2Hour.price =Double.parseDouble(partsZoneOne2Hour[2]);
		String [] partsZoneOneTwo2Hour = readFromFileStrings.get(pricesindex+2).split(":");
		ZoneOneTwo2Hour.price =Double.parseDouble(partsZoneOneTwo2Hour[2]);
		String [] partsZoneOneAllDay = readFromFileStrings.get(pricesindex+3).split(":");
		ZoneOneAllDay.price =Double.parseDouble(partsZoneOneAllDay[2]);
		String [] partsZoneOneTwoAllDay = readFromFileStrings.get(pricesindex+4).split(":");
		ZoneOneTwoAllDay.price =Double.parseDouble(partsZoneOneTwoAllDay[2]);
		
	}
	
	
	public static void writeToFile() throws IOException {
			File fout = new File("test_data.txt");
			FileOutputStream fos = new FileOutputStream(fout);
		 
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		 
			for (String line : readFromFileStrings) {
				bw.write(line);
				bw.newLine();
			}
		 
			bw.close();
		
	}
	
	
	public static void populateLinesArrayListToWrite() {
		readFromFileStrings.clear();
		readFromFileStrings.add("#users");
		for(MyTi myti : myTis) {
			String type = myti.getUser().getType()==UserTypeEnum.SENIOR ? "senior" : myti.getUser().getType()==UserTypeEnum.ADULT ? "adult" : "junior";
			readFromFileStrings.add(myti.getUser().getId()+ ":"+type+":"+myti.getUser().getName()+":"+myti.getUser().getEmail()+":"+myti.getMyTi());
		}
		readFromFileStrings.add("#prices");
		readFromFileStrings.add("2Hour:Zone1:"+ZoneOne2Hour.price);
		readFromFileStrings.add("2Hour:Zone12:"+ZoneOneTwo2Hour.price);
		readFromFileStrings.add("AllDay:Zone1:"+ZoneOneAllDay.price);
		readFromFileStrings.add("AllDay:Zone12:"+ZoneOneTwoAllDay.price);
		
	}
	
	
	
	
	
	private static void createUser(String[] parts) {
		UserTypeEnum type;
		if(parts[1].equals("senior")) {
			type = UserTypeEnum.SENIOR;
		}
		else if(parts[1].equals("adult")) {
			type = UserTypeEnum.ADULT;
		}
		else {
			type = UserTypeEnum.JUNIOR;
		}
		User user = new User(parts[0], parts[3], parts[2], type);
		usersList.put(user.getId(), user);
		MyTi myTi = new MyTi(user);
		myTi.setMyTi(Double.parseDouble(parts[4]));
		myTis.add(myTi);

	}
}
