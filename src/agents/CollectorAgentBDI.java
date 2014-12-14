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
import objects.GarbageDepot;
import objects.GarbageObject;


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
			int freeSpaceCollector = capacity - currentWasteQuantity;
			FindWasteGoal goal = (FindWasteGoal) agent.dispatchTopLevelGoal(new FindWasteGoal(location, freeSpaceCollector)).get();
			System.out.println("Found waste from proper waste type!!");
			System.out.println("Waste picked Up!!");
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
		
		Location collectorLocation = goal.getLocation();
		int freeSpaceCollector = goal.getFreeSpaceCollector();
		ContainerAgentBDI container = null; //TODO= objects.GarbageObject.getContainerByLocation(collectorLocation);
		int containerWasteAmount;
		
		if (container != null) 
		{
			containerWasteAmount = container.getCurrentWasteQuantity();
			if (freeSpaceCollector > 0 && containerWasteAmount > 0)
			{
				if(container.getWasteType() == getWasteType())
				{
					int wasteInContainer = container.getCurrentWasteQuantity();
					int amountToPick;
					int newAmountContainer;
					
					if(freeSpaceCollector <= wasteInContainer)
					{
						//quando o collector não tem espaco suficiente para recolher o lixo todo
						//recolhe o espaço livre todo que tem
						amountToPick = freeSpaceCollector;
						
						//no container fica o restante
						newAmountContainer = wasteInContainer - amountToPick;
						
					}
					else
					{
						//quando o collector tem espaço para recolher o lixo todo do container
						//a quantidade a recolher é tudo o que está no container
						amountToPick = wasteInContainer;
						//no container fica então zero de lixo
						newAmountContainer = 0;
					}
					
					setCurrentWaste(amountToPick);
					container.setCurrentWaste(newAmountContainer);
					
					
				}
				else
				{
					System.out.println("Container and collector are of different waste types!!");
					throw new PlanFailureException();
				}
			}
			else
			{
				System.out.println("Collector does not have free space or container is empty!!");
				throw new PlanFailureException();
			}
		} 
		else
		{
			System.out.println("Not yet at a container location!!");
			throw new PlanFailureException();
		}
		
		
		
	}

	
	@Plan(trigger=@Trigger(goals=GoToDepotGoal.class))
	protected void GoToDepot(GoToDepotGoal goal){
		
		Location collectorLocation = goal.getLocation();
		GarbageDepot depot = objects.GarbageObject.getDepotByLocation(collectorLocation);
		
		if (depot != null) {
			System.out.println("Arrived at depot");
			int amountToBurn = getCurrentWasteQuantity();
			depot.BurnWaste(amountToBurn);//para efeitos de estatistica, ou seja incrementar o numero de vezes que o depot foi usado
		} else {
			
			throw new PlanFailureException();

		}
		
	}


	public int getCapacity() {
		return capacity;
	}



	public int getCurrentWasteQuantity() {
		return currentWasteQuantity;
	}



	public void setCurrentWaste(int waste) {
		currentWasteQuantity += waste;
		
	}

	public String getWasteType() {
		return WasteType;
	}
	

}
