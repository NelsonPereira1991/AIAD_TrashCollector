package interfaceGui;
import interfaceGui.Config.Pos;
import jadex.base.Starter;
import jadex.bdiv3.BDIAgent;
import jadex.bridge.IComponentIdentifier;
import jadex.bridge.IExternalAccess;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.search.SServiceProvider;
import jadex.bridge.service.types.cms.CreationInfo;
import jadex.bridge.service.types.cms.IComponentManagementService;
import jadex.commons.future.ThreadSuspendable;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import map.Location;
import agents.CollectorAgentBDI;

//TODO vai ser o agente mundo
public class WorldAgent  
{
	//Mapa do mundo
	static MapStage stage = new MapStage(1000,1000);
	public static int map_x_limit,map_y_limit;
	public static BDIAgent agent; 
	
	//variaveis para calculos das mÃƒÂ©tricas
	/*long init_time = 0; 
	long final_time =0; 
	public static int exit_cars = 0;
	public static int num_cars=0;
	int sem_agents = 0;
	public static int max_cars=0; //Nï¿½mero mï¿½ximos de carros numa fila ï¿½nica
	public static int total_max_cars=0;
	static long time_passed=0;
	static long time_limit=60000;
	static long counting_time = 0;
	static long now;
	//instante atual, inicialmente igual a init_time
	long instant_t = 0;  */
	
	//directorio do mapa atual selecionado para simulacao
	static String current_map_path = "src/map.txt";
	

	//representacao do mapa currente, com todos os elementos do tipo Element presentes no mesmo
	public static ArrayList<ArrayList<Element>> current_map = new ArrayList<ArrayList<Element>>();
	
	//TODO arraylists para containers
	static ArrayList<Element> containers = new ArrayList<Element>();
	static ArrayList<Element> depots = new ArrayList<Element>();
	
	
	/*
	char getDir(int old_x,int old_y,int new_x,int new_y)
	{
		if(old_x>new_x)
		{
			return 'O';
		}
		else if(new_x>old_x)
		{
			return 'E';
		}
		else if(old_y>new_y)
		{
			return 'N';
		}
		else if(new_y>old_y)
		{
			return 'S';
		}
		else
		{
			return 'a';
		}
	}*/
	
	/**
	 * @throws FileNotFoundException 
	 * 
	 */

	public void loadWorld() throws FileNotFoundException
	{
		
		stage.setPreferredSize(new Dimension(1000,1000));
		stage.setFocusable(true);
		stage.setVisible(true);
		
		Config.drawingArea.add(stage);

		Scanner scanner = new Scanner(new FileReader(current_map_path)).useDelimiter("\\n");//alterar para path
		int rows = 0;
		while (scanner.hasNext()) 
		{
			ArrayList<Element> row = new ArrayList<Element>();
			String line = scanner.next();
			
			for(int i=0; i < line.length(); i++)
			{
				char element = line.charAt(i);
				
					
					String[] arg_sem = null;
				
				
				if(element == 'X' || element == 'x')
				{
					Element elem = new Element(Elem_type.WALKWAY,new Config.Pos(i, rows));
					row.add(elem);
				}
				else if(element == ' ')
				{
					Element elem = new Element(Elem_type.ROAD,new Config.Pos(i, rows));
					row.add(elem);
				}
				else if(element == 'P')
				{
					Element elem = new Element(Elem_type.PAPER_COLLECTOR,new Config.Pos(i, rows));
					row.add(elem);
				}
				else if(element == 'p')
				{
					Element elem = new Element(Elem_type.PAPER_CONTAINER,new Config.Pos(i, rows));
					row.add(elem);
					containers.add(elem);
				}
				else if(element == 'V')
				{
					Element elem = new Element(Elem_type.GLASS_COLLECTOR,new Config.Pos(i, rows));
					row.add(elem);
				}
				else if(element == 'v')
				{
					Element elem = new Element(Elem_type.GLASS_CONTAINER,new Config.Pos(i, rows));
					row.add(elem);
					containers.add(elem);
				}
				else if(element == 'E')
				{
					Element elem = new Element(Elem_type.PACKAGE_COLLECTOR,new Config.Pos(i, rows));
					row.add(elem);
				}
				else if(element == 'e')
				{
					Element elem = new Element(Elem_type.PACKAGE_CONTAINER,new Config.Pos(i, rows));
					row.add(elem);
					containers.add(elem);
				}
				else if(element == 'D')
				{
					Element elem = new Element(Elem_type.DOMESTIC_COLLECTOR,new Config.Pos(i, rows));
					row.add(elem);
				}
				else if(element == 'd')
				{
					Element elem = new Element(Elem_type.DOMESTIC_CONTAINER,new Config.Pos(i, rows));
					row.add(elem);
					containers.add(elem);
				}
				else if(element == 'B')
				{
					Element elem = new Element(Elem_type.DEPOT,new Config.Pos(i, rows));
					row.add(elem);
					depots.add(elem);
					
				}
				
				map_x_limit=i;
			}
			current_map.add(row);
			map_y_limit = rows;
			rows++;
		}
		createAgents();
		Config.drawingArea.revalidate();
    	Config.drawingArea.repaint();


	}
	
	//TODO iniciar agentes
	public static void createAgents()
	{
		//TODO buscar ao current_map a info necessária para criar os agents
		for (int i = 0; i < current_map.size(); i++) 
		{
			for (int j = 0; j < current_map.get(i).size(); j++) 
			{
				
				if (current_map.get(i).get(j).getType() == Elem_type.PAPER_COLLECTOR) 
				{
					/*
					CollectorAgentBDI paperCollector = new CollectorAgentBDI();
					paperCollector.capacity = current_map.get(i).get(j).getCapacity();
					paperCollector.currentWasteQuantity = current_map.get(i).get(j).getCurrentWaste();
					paperCollector.WasteType = "Papel";
					Location location = new Location(current_map.get(i).get(j).getCoords().x, current_map.get(i).get(j).getCoords().y);
					paperCollector.setLocation(location);
					
					paperCollector.init();
					*/
					
					agent = new BDIAgent();
					Map<String, Object> agentArgs = new HashMap<String, Object>();
					int capacity = current_map.get(i).get(j).getCapacity();
					int currentWasteQuantity = current_map.get(i).get(j).getCurrentWaste();
					String wasteType = "Papel";
					Location location = new Location(current_map.get(i).get(j).getCoords().x, current_map.get(i).get(j).getCoords().y);
					
					agentArgs.put("capacity", capacity);
					agentArgs.put("currentWasteQuantity", currentWasteQuantity);
					agentArgs.put("wasteType", wasteType);
					agentArgs.put("location", location);
					
					CreationInfo cInfo = new CreationInfo(agentArgs);
					System.out.println("Is paper collector");
					deployAgent("bin/agents/CollectorAgentBDI.class", cInfo);
					
				}
				else if (current_map.get(i).get(j).getType() == Elem_type.GLASS_COLLECTOR) 
				{
					System.out.println("Is glass collector");
					/*
					CollectorAgentBDI vidroCollector = new CollectorAgentBDI();
					vidroCollector.capacity = current_map.get(i).get(j).getCapacity();
					vidroCollector.currentWasteQuantity = current_map.get(i).get(j).getCurrentWaste();
					vidroCollector.WasteType = "Vidro";
					Location location = new Location(current_map.get(i).get(j).getCoords().x, current_map.get(i).get(j).getCoords().y);
					vidroCollector.setLocation(location);
					vidroCollector.init();
					*/
					
					/*
					agent = new BDIAgent();
					Map<String, Object> agentArgs = new HashMap<String, Object>();
					int capacity = current_map.get(i).get(j).getCapacity();
					int currentWasteQuantity = current_map.get(i).get(j).getCurrentWaste();
					String wasteType = "Vidro";
					Location location = new Location(current_map.get(i).get(j).getCoords().x, current_map.get(i).get(j).getCoords().y);
					
					agentArgs.put("capacity", capacity);
					agentArgs.put("currentWasteQuantity", currentWasteQuantity);
					agentArgs.put("wasteType", wasteType);
					agentArgs.put("location", location);
					
					CreationInfo cInfo = new CreationInfo(agentArgs);
					deployAgent("bin/agents/CollectorAgentBDI.class", cInfo);
					*/
					
				}
				else if (current_map.get(i).get(j).getType() == Elem_type.PACKAGE_COLLECTOR) 
				{
					System.out.println("Is package collector");
					/*
					CollectorAgentBDI embalagemCollector = new CollectorAgentBDI();
					embalagemCollector.capacity = current_map.get(i).get(j).getCapacity();
					embalagemCollector.currentWasteQuantity = current_map.get(i).get(j).getCurrentWaste();
					embalagemCollector.WasteType = "Embalagens";
					Location location = new Location(current_map.get(i).get(j).getCoords().x, current_map.get(i).get(j).getCoords().y);
					embalagemCollector.setLocation(location);
					embalagemCollector.init();
					*/
					
					/*
					agent = new BDIAgent();
					Map<String, Object> agentArgs = new HashMap<String, Object>();
					int capacity = current_map.get(i).get(j).getCapacity();
					int currentWasteQuantity = current_map.get(i).get(j).getCurrentWaste();
					String wasteType = "Embalagens";
					Location location = new Location(current_map.get(i).get(j).getCoords().x, current_map.get(i).get(j).getCoords().y);
					
					agentArgs.put("capacity", capacity);
					agentArgs.put("currentWasteQuantity", currentWasteQuantity);
					agentArgs.put("wasteType", wasteType);
					agentArgs.put("location", location);
					
					CreationInfo cInfo = new CreationInfo(agentArgs);
					deployAgent("bin/agents/CollectorAgentBDI.class", cInfo);
					*/
				}
				else if (current_map.get(i).get(j).getType() == Elem_type.DOMESTIC_COLLECTOR) 
				{
					System.out.println("Is domestic collector");
					/*
					CollectorAgentBDI domesticoCollector = new CollectorAgentBDI();
					domesticoCollector.capacity = current_map.get(i).get(j).getCapacity();
					domesticoCollector.currentWasteQuantity = current_map.get(i).get(j).getCurrentWaste();
					domesticoCollector.WasteType = "Domesticos";
					Location location = new Location(current_map.get(i).get(j).getCoords().x, current_map.get(i).get(j).getCoords().y);
					domesticoCollector.setLocation(location);
					domesticoCollector.init();
					*/
					
					/*
					agent = new BDIAgent();
					Map<String, Object> agentArgs = new HashMap<String, Object>();
					int capacity = current_map.get(i).get(j).getCapacity();
					int currentWasteQuantity = current_map.get(i).get(j).getCurrentWaste();
					String wasteType = "Domesticos";
					Location location = new Location(current_map.get(i).get(j).getCoords().x, current_map.get(i).get(j).getCoords().y);
					
					agentArgs.put("capacity", capacity);
					agentArgs.put("currentWasteQuantity", currentWasteQuantity);
					agentArgs.put("wasteType", wasteType);
					agentArgs.put("location", location);
					
					CreationInfo cInfo = new CreationInfo(agentArgs);
					deployAgent("bin/agents/CollectorAgentBDI.class", cInfo);
					*/
				}
				else if(current_map.get(i).get(j).getType() == Elem_type.PAPER_CONTAINER)
				{
					System.out.println("Is paper container");
					//TODO para já só no paper container e o depot
					
				}
				else if(current_map.get(i).get(j).getType() == Elem_type.GLASS_CONTAINER)
				{
					System.out.println("Is glass container");
					
				}
				else if(current_map.get(i).get(j).getType() == Elem_type.PACKAGE_CONTAINER)
				{
					System.out.println("Is package container");
					
				}
				else if(current_map.get(i).get(j).getType() == Elem_type.DOMESTIC_CONTAINER)
				{
					System.out.println("Is domestic container");
					
				}
				else if(current_map.get(i).get(j).getType() == Elem_type.DEPOT)
				{
					System.out.println("Is depot");
				}
				else
				{
					//DO NOTHING
				}
				
				
				
				
			}
		}
		
	}
	
	
	
	public static boolean setElementPositionInMap(Pos oldPos, Pos newPos)
	{
		boolean result = false;
		for (int i = 0; i < current_map.size(); i++) 
		{
			for (int j = 0; j < current_map.get(i).size(); j++) 
			{
				if(current_map.get(i).get(j).getCoords().x == oldPos.x && current_map.get(i).get(j).getCoords().y == oldPos.y)
				{
					current_map.get(i).get(j).setPosition(newPos);
					return true;
				}
			}
		}
		return result;
		
	}
	
	public static boolean setElementPositionInContainers(Pos oldPos, Pos newPos)
	{
		boolean result = false;
		for (int i = 0; i < containers.size(); i++) 
		{
			if(containers.get(i).getCoords().x == oldPos.x && containers.get(i).getCoords().y == oldPos.y)
			{
				containers.get(i).setPosition(newPos);
				return true;
			}
			
		}
		
		
		return result;
		
	}

	public static Element getContainerByPosition(int x, int y) {
		Element result = null;
		
		for (int i = 0; i < current_map.size(); i++) 
		{
			for (int j = 0; j < current_map.get(i).size(); j++) 
			{
				Elem_type type = current_map.get(i).get(j).getType();
				if(type == Elem_type.DOMESTIC_CONTAINER || type == Elem_type.GLASS_CONTAINER || type == Elem_type.PACKAGE_CONTAINER || type == Elem_type.PAPER_CONTAINER) 
				{
					if(current_map.get(i).get(j).getCoords().x == x && current_map.get(i).get(j).getCoords().y == y)
					{
						return current_map.get(i).get(j);
					}
				}
			}
			
		}
		
		
		return result;
	}
	
	public static Element getElementByPosition(int x, int y) 
	{	
		Element result = null;
		for (int i = 0; i < current_map.size(); i++) 
		{
			for (int j = 0; j < current_map.get(i).size(); j++) 
			{
				if(current_map.get(i).get(j).getCoords().x == x && current_map.get(i).get(j).getCoords().y == y)
				{
					return current_map.get(i).get(j);
				}
			}
		}
		return result;
	}
	
	public static void deployAgent(String classPath, CreationInfo cInfo) {
		ThreadSuspendable sus = new ThreadSuspendable();
		
		if(agent == null)
		{
			System.err.println("Agent is null");
		}
		else
		{
			System.out.println("not null");
			
			IExternalAccess pl = Starter.createPlatform(new String[0]).get(sus);
			 IComponentManagementService cms = SServiceProvider.getService(pl.getServiceProvider(),
				        IComponentManagementService.class, RequiredServiceInfo.SCOPE_PLATFORM).get(sus);
			
			
			
			/**
			 * General interface for components that the container can execute.
			 */
			 /*
			IComponentManagementService cms = SServiceProvider.getService(agent.getServiceProvider(), IComponentManagementService.class,
					RequiredServiceInfo.SCOPE_PLATFORM).get(sus);
			*/

			IComponentIdentifier hw = cms.createComponent(classPath, cInfo).getFirstResult(sus);
			System.out.println("started: " + hw);
		}	
	}
	
}