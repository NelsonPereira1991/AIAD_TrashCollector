package objects;

import map.Location;

public class GarbageDepot {
	//private int x;
	//private int y;
	int depotID;
	public Location location;
	public int numWasteBurned;
	
	private static int count = 0;
	//capacidade infinita
	
	/*
	public GarbageDepot(int xcoord, int ycoord, String WType)
	{
		x = xcoord;
		y = ycoord;
		WasteType = WType;
	}
	*/
	
	public GarbageDepot(Location location)
	{
		this.depotID = count;
		count++;
		this.location = location;
		this.numWasteBurned = 0;
	}

	public void BurnWaste(int amountToBurn) {
		numWasteBurned += amountToBurn;
		
	}

	public int getDepotID() {
		return depotID;
	}
	
	
}
