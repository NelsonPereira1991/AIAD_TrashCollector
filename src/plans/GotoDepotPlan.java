package plans;

import java.util.Random;

import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;

import agents.CollectorAgentBDI;
//TODO Importar depot

@Plan
public class GotoDepotPlan {

	private CollectorAgentBDI collector; 
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
