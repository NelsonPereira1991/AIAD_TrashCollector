package agents;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Body;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalContextCondition;
import jadex.bdiv3.annotation.GoalCreationCondition;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Plans;
import jadex.bdiv3.annotation.Trigger;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentCreated;
import jadex.micro.annotation.Description;
import plans.CreateWastePlan;

@Agent
@Description("Container Agent")
@Plans({@Plan(
		trigger = @Trigger(goals = ContainerAgent.PerformCreateWaste.class),body = @Body(CreateWastePlan.class))
})
public class ContainerAgent {
	
	@Goal
	public class PerformCreateWaste {
		
		@GoalCreationCondition(beliefs = "empty")
		public boolean checkContext()
		{
			return !empty;
		}
		
		

	}

	@Agent
	protected BDIAgent agent;
	
	@Belief
	public String id;
	
	@Belief
	public int capacity = 50;
	
	@Belief
	public int currentWasteQuantity;
	
	@Belief
	public boolean full = false;
	
	@Belief 
	public boolean empty = true;
	
	@Belief
	public String WasteType;
	
	@AgentCreated
	public void init()
	{
		
		
		
		
	}

	public void setCurrentWaste(int wasteAmount) 
	{
		if(currentWasteQuantity == 0)
		{
			currentWasteQuantity+= wasteAmount;
			empty = false;
		}
		
	}

	public int getCurrentWasteQuantity() {
		return currentWasteQuantity;
	}
	
	
	//TODO actualizar variavel empty no plano pickUpWaste
	

}
