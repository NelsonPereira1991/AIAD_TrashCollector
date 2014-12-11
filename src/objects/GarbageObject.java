package objects;

import java.util.ArrayList;

import map.AppInterface;
import map.Location;
import agents.*;

public class GarbageObject {

	
	public static enum Object_Types {
		Collector, Container, Depot
	}
	
	private AppInterface appInt = null;
	

	private ArrayList<CollectorAgentBDI> collector_agents;
	private static ArrayList<ContainerAgentBDI> container_agents;
	
	//TODO AQUI Provavelmente erros
	private static ArrayList<GarbageDepot> depot_agents;
	
	//private float speed = SPEED.Normal.speed;
	private static GarbageObject entity = null;
	public boolean defined;
	
	public boolean memory = false;
	public boolean comunication = false;

	public GarbageObject() {
		collector_agents = new ArrayList<>();
		container_agents = new ArrayList<>();
		depot_agents = new ArrayList<>();
	}
	
	public static GarbageObject getEntity() {
		if(entity == null)
		{
			entity = new GarbageObject();
		}
		return entity;
	}

	public AppInterface getAppInt() {
		return appInt;
	}
	
	
	public static ContainerAgentBDI getContainerByLocation(Location location)
	{
		ContainerAgentBDI result = null;
		
		for(int i = 0; i < container_agents.size();i++)
		{
			if(location.x == container_agents.get(i).location.x && location.y == container_agents.get(i).location.y)
			{
				result = container_agents.get(i);
				return result;
			}
		}
		
		return result;
	}
	
	
	public static GarbageDepot getDepotByLocation(Location location)
	{
		GarbageDepot result = null;
		
		for(int i = 0; i < depot_agents.size();i++)
		{
			if(location.x == depot_agents.get(i).location.x && location.y == depot_agents.get(i).location.y)
			{
				result = depot_agents.get(i);
				return result;
			}
		}
		
		return result;
	}
	
	public boolean moveCollector(CollectorAgentBDI collector, String direction)
	{
		//Direction: up/down/left/right
		boolean result = true;
		switch (direction) {
		case "up":
			collector.location.y--;
			break;
		case "down":
			collector.location.y++;
			break;
		case "left":
			collector.location.x--;
			break;
		case "right":
			collector.location.x++;
			break;

		default:
			System.err.println("Unknown direction");
			result = false;
			break;
		}
		
		return result;
		
	}

}
