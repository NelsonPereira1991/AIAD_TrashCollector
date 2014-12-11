package plans;

import agents.CollectorAgentBDI;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;

@Plan
public class WanderPlan {
	
	private CollectorAgentBDI collector;
	
	public WanderPlan()
	{
		
	}

	@PlanBody
	public void WanderPlanBody()
	{
		
		//TODO collector.move()
		
	}
}
