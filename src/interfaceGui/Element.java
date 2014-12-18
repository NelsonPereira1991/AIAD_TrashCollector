package interfaceGui;

import com.android.dx.merge.CollisionPolicy;

public class Element
{
	
	private Elem_type type;
	private Config.Pos coords;
	private int wasteCapacity;
	private int currentWaste;
	
	

	public Element(Elem_type type)
	{
		this.type = type;
	}
	
//PAPER_COLLECTOR, GLASS_COLLECTOR, DEPOT, PACKAGE_COLLECTOR, DOMESTIC_COLLECTOR, PAPER_CONTAINER, GLASS_CONTAINER, PACKAGE_CONTAINER, DOMESTIC_CONTAINER
	public Element(Elem_type type, Config.Pos position)
	{
		this.type = type;
		this.coords = position;
		
		if(this.type == Elem_type.PAPER_COLLECTOR || this.type == Elem_type.GLASS_COLLECTOR || this.type == Elem_type.PACKAGE_COLLECTOR || this.type == Elem_type.DOMESTIC_COLLECTOR)
		{
			this.currentWaste = 0;
			this.wasteCapacity = 100;
		}
		else if(this.type == Elem_type.PAPER_CONTAINER || this.type == Elem_type.GLASS_CONTAINER || this.type == Elem_type.PACKAGE_CONTAINER || this.type == Elem_type.DOMESTIC_CONTAINER)
		{
			this.currentWaste = 0;
			this.wasteCapacity = 150;
		}
		else
		{
				this.currentWaste = -1;
				this.wasteCapacity = -1;
			
		}
		
		
	}
	
	public void setPosition(Config.Pos pos)
	{
		System.out.println("Dentro do setPosition");
		coords = pos;
	}
	
	public void setType(Elem_type t)
	{
		type = t;
	}
	
	public Elem_type getType() 
	{
		return type;
	}
	
	public Config.Pos getCoords() 
	{
		return coords;
	}

	public int getCapacity() {
		return this.wasteCapacity;
	}

	public int getCurrentWaste() {
		return this.currentWaste;
	}
	
	public void setCurrentWaste(int currentWaste) 
	{
		this.currentWaste = currentWaste;
	}

	public String getWasteType() {
		String result = null;
		
		if(this.type == Elem_type.PAPER_COLLECTOR || this.type == Elem_type.PAPER_CONTAINER)
		{
			result = "Papel";
		}
		else if(this.type == Elem_type.GLASS_COLLECTOR || this.type == Elem_type.GLASS_CONTAINER)
		{
			result = "Vidro";
		}
		else if(this.type == Elem_type.PACKAGE_COLLECTOR || this.type == Elem_type.PACKAGE_CONTAINER)
		{
			result = "Embalagens";
		}
		else if(this.type == Elem_type.DOMESTIC_COLLECTOR || this.type == Elem_type.DOMESTIC_CONTAINER)
		{
			result = "Domesticos";
		}
			
		
		return result;
	}
	
}
