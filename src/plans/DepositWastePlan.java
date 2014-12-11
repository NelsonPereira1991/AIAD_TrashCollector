package plans;

import agents.CollectorAgentBDI;
import agents.ContainerAgentBDI;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;

@Plan
public class DepositWastePlan {
	
	//private GarbageDepotAgent gdepot;
	private CollectorAgentBDI collector;
	
	public DepositWastePlan()
	{
		
	}
	
	
	@PlanBody
	public void DepositWasteBody()
	{
		collector.setCurrentWaste(0);
	}

}
