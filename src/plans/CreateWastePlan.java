package plans;


import agents.ContainerAgent;
import java.util.Random;

import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;

@Plan
public class CreateWastePlan {
	private int wasteAmount;
	private ContainerAgent container;
	
	private int[] wasteRandom = {10,20,30,40,50}; 
	
	//TODO só chamar quando o container estiver com lixo igual a zero
	public CreateWastePlan()
	{
		Random r = new Random();
		int index = r.nextInt(wasteRandom.length);
		wasteAmount = wasteRandom[index]; 
	}
	
	@PlanBody
	public void CreateWastePlanBody()
	{
		
		container.setCurrentWaste(wasteAmount);
		
	}

}
