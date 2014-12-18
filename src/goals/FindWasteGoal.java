package goals;

import map.Location;
import jadex.bdiv3.annotation.Goal;

@Goal
public class FindWasteGoal {
	protected Location location;
	protected int freeSpaceCollector;

	public FindWasteGoal(Location location, int freeSpaceCollector) {
		//this.location = location;
		this.location = new Location(location.x, location.y);
		
		this.freeSpaceCollector = freeSpaceCollector;
		System.out.println("FindWasteGoal constructor");
	}

	public Location getLocation() {
		return location;
	}

	public int getFreeSpaceCollector() {
		return freeSpaceCollector;
	}
}
