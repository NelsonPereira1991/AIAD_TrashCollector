package agents;
import java.util.Random;

import goals.CreateWasteGoal;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Body;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalContextCondition;
import jadex.bdiv3.annotation.GoalCreationCondition;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Plans;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.impl.PlanFailureException;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentCreated;
import jadex.micro.annotation.Description;
//import plans.CreateWastePlan;
import map.Location;

@Agent
@Description("Container Agent")
/*@Plans({@Plan(
		trigger = @Trigger(goals = ContainerAgent.PerformCreateWaste.class),body = @Body(CreateWastePlan.class))
})*/
/*@Plans({@Plan(
trigger = @Trigger(goals = CreateWasteGoal.class))
})*/
public class ContainerAgentBDI {
	
	/*
	@Goal
	public class PerformCreateWaste {
		
		@GoalCreationCondition(beliefs = "empty")
		public boolean checkContext()
		{
			return !empty;
		}
		
		

	}
	*/

	@Agent
	protected BDIAgent agent;
	
	@Belief
	public String id;
	
	@Belief
	public int capacity = 50;
	
	@Belief
	public int currentWasteQuantity = 0;
	
	@Belief
	public boolean full = false;
	
	@Belief 
	public boolean empty = true;
	
	@Belief
	public String wasteType;
	
	@Belief
	public Location location;
	
	
	@AgentCreated
	public void init()
	{
		//TODO location = (Location) agent.getArgument("location");
		//TODO
		/*if(location == null)
		{
			
		}
		*/
		//TODO wasteType = (String) agent.getArgument("wasteType");
		//TODO
		/*if(wasteType == null)
		{
			
		}
		*/
		
		//TODO id = (String) agent.getArgument("id");
		
		currentWasteQuantity = 0;
		capacity = 50;
		full = false;
		empty = true;
		
	}

	
	@AgentBody
	public void body()
	{
		try {
			//int capacity = 50;
			
			//int currentWasteQuantity = 0;
			
			CreateWasteGoal goal = (CreateWasteGoal) agent.dispatchTopLevelGoal(new CreateWasteGoal(currentWasteQuantity, capacity)).get();
			System.out.println("The current waste is now: "+goal.getCurrentWasteQuantity());
			System.out.println("Good bye!");
		} catch (PlanFailureException e) {
			System.out.println("No waste was generated!!!");
		}
		
		
	}
	
	@Plan(trigger=@Trigger(goals=CreateWasteGoal.class))
	protected void generateWaste(CreateWasteGoal goal){
		System.out.println("Is the container empty?!");
		int currentWaste = goal.getCurrentWasteQuantity();
		if (currentWaste == 0 /*&& currentWaste < goal.getCapacity()*/)
		{
			System.out.println("Generating waste");
			int[] wasteRandom = {10,20,30,40,50}; 
			Random r = new Random();
			int index = r.nextInt(wasteRandom.length);
			int wasteAmount = wasteRandom[index];
			goal.setCurrentWasteQuantity(wasteAmount);
		}
		else
		{
			throw new PlanFailureException();
		}
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
