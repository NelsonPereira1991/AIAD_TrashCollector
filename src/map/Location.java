package map;

public class Location {
	
	public int x,y;
	
	public Location(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public void movement(){
		Object garbageCollector;
		AppInterface appInt = garbageCollector.getEntity().getAppInt();
		if(appInt==null){
			if(garbageCollector.defined){
				System.out.println("World isn't defined");
			}
			x+=(Math.round(Math.random()));
			y+=(Math.round(Math.random()));
			
			return;
		}
		
		++x;
		if(x>=appInt.getWidth()){
			x=0;
			y++;
			if(y>=appInt.getHeight())
				y=0;
			
		}
	}
	
	public boolean equals(Location location){
		if(x==location.x && y==location.y)
			return true;
		else
			return false;
	}
	
	@Override
	public Location clone(){
		return new Location(x,y);
	}
	
	public String toString(){
		return x + " , " + y;
	}
	
}
