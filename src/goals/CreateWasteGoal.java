package goals;

import jadex.bdiv3.annotation.Goal;

@Goal
public class CreateWasteGoal {
	protected int currentWasteQuantity;
	protected int capacity;
	
	public CreateWasteGoal(int current, int cap)
	{
		currentWasteQuantity = current;
		capacity = cap;
	}

	public int getCurrentWasteQuantity() {
		return currentWasteQuantity;
	}

	public void setCurrentWasteQuantity(int currentWasteQuantity) {
		this.currentWasteQuantity = currentWasteQuantity;
	}

	public int getCapacity() {
		return capacity;
	}
	
	

}
