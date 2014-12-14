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
	
	
	//AQUI
	
	public static ArrayList<Collector> collectors;
	public static ArrayList<Container> containers;
	public static ArrayList<GarbageDepot> depots;
	
	
	//private float speed = SPEED.Normal.speed;
	private static GarbageObject entity = null;
	public boolean defined;
	
	public boolean memory = false;
	public boolean comunication = false;

	public GarbageObject() {
		collector_agents = new ArrayList<>();
		container_agents = new ArrayList<>();
		//depot_agents = new ArrayList<>();
		
		collectors = new ArrayList<>();
		containers = new ArrayList<>();
		depots = new ArrayList<>();
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
	
	
	public static Container getContainerByLocation(Location location)
	{
		Container result = null;
		
		for(int i = 0; i < containers.size();i++)
		{
			if(location.x == containers.get(i).getLocation().x && location.y == containers.get(i).getLocation().y)
			{
				result = containers.get(i);
				return result;
			}
		}
		
		return result;
	}
	
	/*
	public static Collector getCollectorByLocation(Location location)
	{
		Collector result = null;
		
		for(int i = 0; i < collectors.size();i++)
		{
			if(location.x == collectors.get(i).getLocation().x && location.y == collectors.get(i).getLocation().y)
			{
				result = collectors.get(i);
				return result;
			}
		}
		
		return result;
	}
	*/
	
	
	public static GarbageDepot getDepotByLocation(Location location)
	{
		GarbageDepot result = null;
		
		for(int i = 0; i < depots.size();i++)
		{
			if(location.x == depots.get(i).location.x && location.y == depots.get(i).location.y)
			{
				result = depots.get(i);
				return result;
			}
		}
		
		return result;
	}
	
	/*
	public static ContainerAgentBDI getContainerAgentBDIByID(int idContainer)
	{
		
		for (int i = 0; i < containers.size(); i++) {
			
		}
	}
	*/
	
	
	
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
