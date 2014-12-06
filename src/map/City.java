package map;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;


public class City extends JPanel{
	public static JLabel selected=null;
	
	private int width;
	private int height;
	
	private Gridpanel[][] square;
	
	private graphMap map;
	
	public City(int width,int height){
		this.width=width;
		this.height=height;
	}
	
	public City(String mapFile) throws FileNotFoundException{
		this.map=new graphMap(mapFile);
		this.width=map.width;
		this.height=map.height;
		
		
		
		
	}	
}
