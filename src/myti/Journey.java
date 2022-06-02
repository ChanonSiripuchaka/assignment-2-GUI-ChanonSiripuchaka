package myti;

public class Journey {
	private TravelPass pass;
	private MyTi myTi;
	private int startTime;
	private int endTime;
	private Days day;
	private Station end;
	private Station Start;
	private boolean sameZone;
	private boolean allDay;
	public Journey(TravelPass pass, MyTi myTi, int startTime, int endTime, Days day, Station end,
			Station start, boolean isSameZone , boolean isAllDay) {
		super();
		this.pass = pass;
		this.myTi = myTi;
		this.startTime = startTime;
		this.endTime = endTime;
		this.day = day;
		this.end = end;
		this.Start = start;
		this.sameZone = isSameZone;
		this.allDay = isAllDay;
	}
	public TravelPass getPass() {
		return pass;
	}
	public void setPass(TravelPass pass) {
		this.pass = pass;
	}
	public MyTi getMyTi() {
		return myTi;
	}
	public void setMyTi(MyTi myTi) {
		this.myTi = myTi;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	public Days getDay() {
		return day;
	}
	public void setDay(Days day) {
		this.day = day;
	}
	public Station getEnd() {
		return end;
	}
	public void setEnd(Station end) {
		this.end = end;
	}
	public Station getStart() {
		return Start;
	}
	public void setStart(Station start) {
		Start = start;
	}
	
	
	public boolean isSameZone() {
		return sameZone;
	}
	public void setSameZone(boolean sameZone) {
		this.sameZone = sameZone;
	}
	
	public boolean isAllDay() {
		return allDay;
	}
	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}
	@Override
	public String toString() {
		return "Journey [pass=" + pass.toString() + ", myTi=" + myTi.toString() + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", day=" + day + ", end=" + end + ", Start=" + Start + ", sameZone=" + sameZone + ", allDay=" + allDay
				+ "]";
	}
	
	
	
	
}
