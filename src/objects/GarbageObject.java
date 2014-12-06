package objects;

import java.util.ArrayList;

import map.AppInterface;
import agents.*;

public class GarbageObject {

	
	public static enum Object_Types {
		Collector, Container, Depot
	}
	
	private AppInterface appInt = null;
	

	private ArrayList<CollectorAgent> collector_agents;
	private ArrayList<ContainerAgent> container_agents;
	
	//TODO AQUI Provavelmente merda
	private ArrayList<GarbageDepot> depot_agents;
	
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

}
