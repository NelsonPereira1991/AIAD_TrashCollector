package objects;

import map.Location;

public class Container {
	int containerID;
	private Location location;
	String wasteType;
	int capacity;
	
	private static int count = 0;
	
	public Container(Location location, String wasteType, int capacity) {
		containerID = count;
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

	public int getContainerID() {
		return containerID;
	}

	public String getWasteType() {
		return wasteType;
	}

	public int getCapacity() {
		return capacity;
	}
	
}
