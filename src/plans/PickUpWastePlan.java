package plans;

import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;
import agents.ContainerAgent;
import agents.CollectorAgent;

@Plan
public class PickUpWastePlan {
	
	private int amountToPickUp;
	private int amountInContainer;
	private int collectorFreeSpace;
	private ContainerAgent container;
	private CollectorAgent collector;
	
	public PickUpWastePlan()
	{
		amountInContainer = container.getCurrentWasteQuantity();
		collectorFreeSpace = collector.getCapacity() - collector.getCurrentWasteQuantity();
	}
	
	@PlanBody
	public void PickUpWastePlanBody()
	{
		if(collectorFreeSpace < amountInContainer)
		{
			amountToPickUp = amountInContainer;
			container.setCurrentWaste(amountInContainer - amountToPickUp);
			collector.setCurrentWaste(amountToPickUp);
		}
	}

}
