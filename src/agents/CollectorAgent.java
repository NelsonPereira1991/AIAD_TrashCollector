package agents;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentCreated;
import jadex.micro.annotation.Description;


@Agent
@Description("Collector agent")
public class CollectorAgent {
	
	@Agent
	protected BDIAgent agent;
	
	@Belief
	public String idCollector;
	
	@Belief
	public int capacity = 20;
	
	@Belief
	public int currentWasteQuantity = 0;
	
	@Belief
	public boolean full = false;
	
	@Belief
	public String WasteType;
	
	
	
	@AgentCreated
	public void init()
	{
		
		
	}
	
	

}
