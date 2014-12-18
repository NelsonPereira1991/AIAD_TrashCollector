package agents;
import interfaceGui.Config;
import interfaceGui.Config.Pos;
import interfaceGui.Elem_type;
import interfaceGui.Element;
import interfaceGui.WorldAgent;

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
import jadex.micro.annotation.Argument;
import jadex.micro.annotation.Arguments;
import jadex.micro.annotation.Description;
import plans.PickUpWastePlan;
import objects.GarbageDepot;
import objects.GarbageObject;


@Agent
@Description("Collector agent")
/*@Plans(
		{@Plan(trigger = @Trigger(goals = CollectorAgent.PerformDepositWaste.class),body = @Body(PickUpWastePlan.class))}
)*/
@Arguments({
	@Argument(name="capacity", clazz=Integer.class),
	@Argument(name="currentWasteQuantity", clazz=Integer.class),
	@Argument(name="WasteType", clazz=String.class),
	@Argument(name="location", clazz=Location.class)
})
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

	public Element collector;

	@Agent
	protected BDIAgent agent;
	
	@Belief
	public String idCollector;
	
	@Belief
	public int capacity;
	
	@Belief
	public int currentWasteQuantity;
	
	@Belief
	public boolean full = false;
	
	@Belief
	public String wasteType;
	
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
		/*
		currentWasteQuantity = 10;
		capacity = 100;
		*/
		/*
		@Argument(name="capacity", clazz=Integer.class),
		@Argument(name="currentWasteQuantity", clazz=Integer.class),
		@Argument(name="WasteType", clazz=String.class),
		@Argument(name="location", clazz=Location.class)
		*/
		
		System.out.println("IN INIT");
		this.capacity = (int) agent.getArgument("capacity");
		this.currentWasteQuantity = (int) agent.getArgument("currentWasteQuantity");
		this.wasteType = (String) agent.getArgument("wasteType");
		this.location = (Location) agent.getArgument("location");
		
		System.out.println("capacity:" + this.capacity);
		System.out.println("currentWasteQuantity:" + this.currentWasteQuantity);
		System.out.println("wasteType:" + this.wasteType);
		System.out.println("location:" + this.location);
		
	}
	
	@AgentBody
	public void body()
	{
		System.out.println("IN BODY!!!!!");
		Config.drawingArea.revalidate();
    	Config.drawingArea.repaint();
		
		try {
			int freeSpaceCollector = this.capacity - this.currentWasteQuantity;
			
			System.out.println("freeSpaceCollector: " + freeSpaceCollector);
			
			FindWasteGoal goal = (FindWasteGoal) agent.dispatchTopLevelGoal(new FindWasteGoal(this.location, freeSpaceCollector)).get();
			System.out.println("Found waste from proper waste type!!");
			System.out.println("Waste picked Up!!");
			System.out.println("Good bye!");
		} catch (PlanFailureException e) {
			System.out.println("Waste not found, still looking...");
			//TODO moveCollector
			Config.Pos newPos =  moveCollector();
			System.out.println("After moveCollector");
			System.out.println("old pos x/y:" + location.x  + "/" + location.y);
			System.out.println("new pos x/y:" + newPos.x  + "/" + newPos.y);
			
			Pos oldPos = new Pos(this.location.x, this.location.y);
			//WorldAgent.debugMap();
			WorldAgent.setElementPositionInContainers(oldPos, newPos);
			System.out.println("After setElementPositionInContainers");
			WorldAgent.setElementPositionInMap(oldPos, newPos);
			System.out.println("After setElementPositionInMap");
			this.location.x = newPos.x;
			this.location.y = newPos.y;
			//WorldAgent.debugMap();
			//Config.drawingArea.revalidate();
	    	//Config.drawingArea.repaint();
	    	//this.body();
			this.body();
		}
		
		/*
		try {
			GoToDepotGoal goal = (GoToDepotGoal) agent.dispatchTopLevelGoal(new GoToDepotGoal(currentWasteQuantity,location)).get();
			System.out.println("Waste burned");
			//eliminar o lixo do collector
			setCurrentWaste(0);
			System.out.println("Collector is now empty");
		} catch (PlanFailureException e) {
			System.out.println("Burner still not found");
		}
		*/
			
		
	}
	
	
	private Config.Pos moveCollector() {
		Config.Pos posResult = null;
		boolean result = false;
		//gerar direcao para movimento
		//verificar se é possivel mover-se nessa direcao
		while(result == false)
		{
			Random rn = new Random();
			int seedDirection = rn.nextInt(4) + 1;
			
			String direction = null;
			
			if(seedDirection == 1)
			{
				direction = "up";
			}
			else if(seedDirection == 2)
			{
				direction = "down";
			}
			else if(seedDirection == 3)
			{
				direction = "left";
			}
			else if(seedDirection == 4)
			{
				direction = "right";
			}
			
			switch (direction) {
			case "up":
				int x = location.x;
				int y = location.y - 1;
				Element element = WorldAgent.getElementByPosition(x, y);
				if (element != null) 
				{
					if(element.getType() == Elem_type.ROAD || element.getType() == Elem_type.DOMESTIC_CONTAINER || element.getType() == Elem_type.GLASS_CONTAINER || element.getType() == Elem_type.PACKAGE_CONTAINER || element.getType() == Elem_type.PAPER_CONTAINER)
					{
						//result = true;
						posResult = new Pos(x, y);
						result = true;
					}
				}
				break;
			case "down":
				int x1 = location.x;
				int y1 = location.y + 1;
				Element element1 = WorldAgent.getElementByPosition(x1, y1);
				if (element1 != null) 
				{
					if(element1.getType() == Elem_type.ROAD || element1.getType() == Elem_type.DOMESTIC_CONTAINER || element1.getType() == Elem_type.GLASS_CONTAINER || element1.getType() == Elem_type.PACKAGE_CONTAINER || element1.getType() == Elem_type.PAPER_CONTAINER)
					{
						//result = true;
						posResult = new Pos(x1, y1);
						result = true;
					}
				}
				break;
			case "left":
				int x2 = location.x - 1;
				int y2 = location.y; 
				Element element2 = WorldAgent.getElementByPosition(x2, y2);
				if (element2 != null) 
				{
					if(element2.getType() == Elem_type.ROAD || element2.getType() == Elem_type.DOMESTIC_CONTAINER || element2.getType() == Elem_type.GLASS_CONTAINER || element2.getType() == Elem_type.PACKAGE_CONTAINER || element2.getType() == Elem_type.PAPER_CONTAINER)
					{
						//result = true;
						posResult = new Pos(x2, y2);
						result = true;
					}
				}
				break;
				
			case "right":
				int x3 = location.x + 1;
				int y3 = location.y;
				Element element3 = WorldAgent.getElementByPosition(x3, y3);
				if (element3 != null) 
				{
					if(element3.getType() == Elem_type.ROAD || element3.getType() == Elem_type.DOMESTIC_CONTAINER || element3.getType() == Elem_type.GLASS_CONTAINER || element3.getType() == Elem_type.PACKAGE_CONTAINER || element3.getType() == Elem_type.PAPER_CONTAINER)
					{
						//result = true;
						posResult = new Pos(x3, y3);
						result = true;
					}
				}
				break;

			default:
				break;
			}
		}
		
		
		return posResult;
	}

	@Plan(trigger=@Trigger(goals=FindWasteGoal.class))
	protected void findWaste(FindWasteGoal goal){
		System.out.println("In findWaste");
		Location collectorLocation = goal.getLocation();
		int freeSpaceCollector = goal.getFreeSpaceCollector();
		//ContainerAgentBDI container = objects.GarbageObject.getContainerByLocation(collectorLocation);
		
		Element container = WorldAgent.getContainerByPosition(collectorLocation.x,collectorLocation.y);
		
		int containerWasteAmount;
		
		if (container != null) 
		{
			//containerWasteAmount = container.getCurrentWasteQuantity();
			containerWasteAmount = container.getCurrentWaste();
			if (freeSpaceCollector > 0 && containerWasteAmount > 0)
			{
				//if(container.getWasteType() == getWasteType())
				if(container.getWasteType() == getWasteType())
				{
					//int wasteInContainer = container.getCurrentWasteQuantity();
					int wasteInContainer = container.getCurrentWaste();
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
					//container.setCurrentWaste(newAmountContainer);
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
		return wasteType;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	
	

}
