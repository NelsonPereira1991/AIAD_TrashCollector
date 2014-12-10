package agents;
import java.util.Random;

import goals.CreateWasteGoal;
import goals.FindWasteGoal;
import goals.GoToDepotGoal;
import map.Location;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Body;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalCreationCondition;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Plans;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.bdiv3.runtime.impl.PlanFailureException;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentCreated;
import jadex.micro.annotation.Description;
import plans.PickUpWastePlan;


@Agent
@Description("Collector agent")
/*@Plans(
		{@Plan(trigger = @Trigger(goals = CollectorAgent.PerformDepositWaste.class),body = @Body(PickUpWastePlan.class))}
)*/
public class CollectorAgentBDI {
	
	/*
	@Goal
	public class PerformDepositWaste {
		//@GoalCreationCondition(beliefs = "full" & "nearDeposit")
		//public boolean checkStuff()
		//{
		//	return !empty;
		//}
	}
	*/


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
	
	@Belief
	public Location location;
	
	
	
	@AgentCreated
	public void init()
	{
		/*
		location = (Location) agent.getArgument("location");
		WasteType = (String) agent.getArgument("WasteType");
		idCollector = (String) agent.getArgument("idCollector");
		*/
		//full = false;
		currentWasteQuantity = 10;
		capacity = 100;		
	}
	
	@AgentBody
	public void body()
	{
		try {
			FindWasteGoal goal = (FindWasteGoal) agent.dispatchTopLevelGoal(new FindWasteGoal()).get();
			System.out.println("Found waste from proper waste type!!");
			//TODO adicionar quantidade de waste levantada ao collector e retirar esse waste do container
			//setCurrentWaste(quantidadeLevantada);
			//TODO setContainerWaste blablabla
			System.out.println("Good bye!");
		} catch (PlanFailureException e) {
			System.out.println("Waste not found, still looking...");
		}
		
		try {
			GoToDepotGoal goal = (GoToDepotGoal) agent.dispatchTopLevelGoal(new GoToDepotGoal(currentWasteQuantity,location)).get();
			System.out.println("Waste burned");
			//eliminar o lixo do collector
			setCurrentWaste(0);
			System.out.println("Collector is now empty");
		} catch (PlanFailureException e) {
			System.out.println("Burner still not found");
		}
			
		
	}
	
	
	@Plan(trigger=@Trigger(goals=FindWasteGoal.class))
	protected void findWaste(FindWasteGoal goal){
		
		/*
		if ()
		{
			
		}
		else
		{
			throw new PlanFailureException();
		}
		*/
	}

	
	@Plan(trigger=@Trigger(goals=GoToDepotGoal.class))
	protected void GoToDepot(GoToDepotGoal goal){
		//TODO
		/*
		if(goal.getLocation() in DepotLocations[])
		{
			System.out.println("Arrived at depot");
			//A location sera entao a de um depot
			GarbageDepot depot = getDepotByLocation(location);
			depot.BurnWaste();//para efeitos de estatistica, ou seja incrementar o numero de vezes que o depot foi usado
			
		}
		else
		{
			throw new PlanFailureException();
		}
		*/
		
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
