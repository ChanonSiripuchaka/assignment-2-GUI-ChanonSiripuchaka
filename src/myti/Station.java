package myti;

public class Station {
	private String name;
	private ZonesEnum zone;
	private int visitsStart;
	private int visitsEnd;

	public Station(String name, ZonesEnum zone) {
		super();
		this.name = name;
		this.zone = zone;
		this.visitsStart = 0;
		this.visitsEnd = 0;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ZonesEnum getZone() {
		return zone;
	}

	public void setZone(ZonesEnum zone) {
		this.zone = zone;
	}

	public int getVisitsStart() {
		return visitsStart;
	}

	public void setVisitsStart(int visitsStart) {
		this.visitsStart = visitsStart;
	}

	public int getVisitsEnd() {
		return visitsEnd;
	}

	public void setVisitsEnd(int visitsEnd) {
		this.visitsEnd = visitsEnd;
	}

	@Override
	public String toString() {
		return "Station [name=" + name + ", zone=" + zone + ", visitsStart=" + visitsStart + ", visitsEnd=" + visitsEnd
				+ "]";
	}

}
