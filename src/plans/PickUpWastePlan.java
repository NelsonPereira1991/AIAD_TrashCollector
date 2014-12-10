package plans;

import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;
import agents.ContainerAgentBDI;
import agents.CollectorAgentBDI;

@Plan
public class PickUpWastePlan {
	
	private int amountToPickUp;
	private int amountInContainer;
	private int collectorFreeSpace;
	private ContainerAgentBDI container;
	private CollectorAgentBDI collector;
	
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
