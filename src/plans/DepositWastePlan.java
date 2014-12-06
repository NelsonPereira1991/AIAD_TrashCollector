package plans;

import agents.CollectorAgent;
import agents.ContainerAgent;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;

@Plan
public class DepositWastePlan {
	
	//private GarbageDepotAgent gdepot;
	private CollectorAgent collector;
	
	public DepositWastePlan()
	{
		
	}
	
	
	@PlanBody
	public void DepositWasteBody()
	{
		collector.setCurrentWaste(0);
	}

}
