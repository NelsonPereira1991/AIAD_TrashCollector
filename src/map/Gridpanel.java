package map;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class Gridpanel extends JLabel implements MouseListener{
	public int x, y;

	public Image image;
	
	public boolean collector = false;
	
	public boolean container = false;
	
	public boolean wasteDepot = false;
	
	public boolean oneWay = false, twoWay = false;
	
	public boolean oneWayVertical = false, twoWayVertical = false;
	
	public boolean oneWayNorth = false, oneWaySouth = false, oneWayEast = false, oneWayWest = false;
	
	public Gridpanel(int x, int y){
		this.x=x;
		this.y=y;
		
		addMouseListener(this);
		setHorizontalAlignment(SwingConstants.RIGHT);
	}
	
	public Location getLoc(){
		return new Location(x,y);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
