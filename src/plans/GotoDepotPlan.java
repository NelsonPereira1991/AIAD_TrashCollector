package plans;

import java.util.Random;

import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;

import agents.CollectorAgent;
//TODO Importar depot

@Plan
public class GotoDepotPlan {

	private CollectorAgent collector; 
	//TODO Aplicar o shortest path aqui lalalalalalalal
	
		public GotoDepotPlan()
		{
			 
		}
		
		@PlanBody
		public void GotoDepotPlanBody()
		{
			
			//TODO coords = shortestPath()
			
			collector.setCurrentWaste(0);
			
			
		}
		
		public void shortestPath()
		{
			//TODO obter depot mais perto do agente
			
		}
}
