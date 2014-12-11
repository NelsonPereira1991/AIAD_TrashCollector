package goals;

import map.Location;
import jadex.bdiv3.annotation.Goal;

@Goal
public class GoToDepotGoal {
	protected int quantityToBurn; 
	protected Location location;
	
	public GoToDepotGoal(int currentWasteQuantity, Location location) {
		quantityToBurn = currentWasteQuantity;
		this.location = location;
	}

	public int getQuantityToBurn() {
		return quantityToBurn;
	}

	public void setQuantityToBurn(int quantityToBurn) {
		this.quantityToBurn = quantityToBurn;
	}

	public Location getLocation() {
		return location;
	}
	
	

}
