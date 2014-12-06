package agents;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Body;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalCreationCondition;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Plans;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentCreated;
import jadex.micro.annotation.Description;
import plans.PickUpWastePlan;


@Agent
@Description("Collector agent")
@Plans(
		{@Plan(trigger = @Trigger(goals = CollectorAgent.PerformDepositWaste.class),body = @Body(PickUpWastePlan.class))}
)
public class CollectorAgent {
	@Goal
	public class PerformDepositWaste {
		
		/*
		@GoalCreationCondition(beliefs = "full" & "nearDeposit")
		public boolean checkStuff()
		{
			return !empty;
		}
		*/

	}



	@Agent
	protected BDIAgent agent;
	
	@Belief
	public String idCollector;
	
	@Belief
	public int capacity = 100;
	
	@Belief
	public int currentWasteQuantity = 0;
	
	@Belief
	public boolean full = false;
	
	@Belief
	public String WasteType;
	
	
	
	@AgentCreated
	public void init()
	{
		
		
	}



	public int getCapacity() {
		return capacity;
	}



	public int getCurrentWasteQuantity() {
		return currentWasteQuantity;
	}



	public void setCurrentWaste(int waste) {
		// TODO Auto-generated method stub
		currentWasteQuantity = waste;
		
	}
	
	
	
	
	

}
