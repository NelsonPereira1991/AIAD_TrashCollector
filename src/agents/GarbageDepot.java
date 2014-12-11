package agents;

import map.Location;

public class GarbageDepot {
	//private int x;
	//private int y;
	public Location location;
	public int numWasteBurned;
	//capacidade infinita
	
	/*
	public GarbageDepot(int xcoord, int ycoord, String WType)
	{
		x = xcoord;
		y = ycoord;
		WasteType = WType;
	}
	*/
	
	public GarbageDepot(Location location, String WasteType)
	{
		this.location = location;
		this.numWasteBurned = 0;
	}

	public void BurnWaste(int amountToBurn) {
		numWasteBurned += amountToBurn;
		
	}
}
