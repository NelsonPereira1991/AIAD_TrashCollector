package plans;

import agents.CollectorAgent;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;

@Plan
public class WanderPlan {
	
	private CollectorAgent collector;
	
	public WanderPlan()
	{
		
	}

	@PlanBody
	public void WanderPlanBody()
	{
		
		//TODO collector.move()
		
	}
}
