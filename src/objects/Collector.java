package objects;

import map.Location;

public class Collector {
	int collectorID;
	private Location location;
	String wasteType;
	int capacity;
	
	private static int count = 0;
	
	
	public Collector(Location location, String wasteType, int capacity) {
		collectorID = count;
		count++;
		this.location = location;
		this.wasteType = wasteType;
		this.capacity = capacity;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getCollectorID() {
		return collectorID;
	}

	public String getWasteType() {
		return wasteType;
	}

	public int getCapacity() {
		return capacity;
	}
	
}
