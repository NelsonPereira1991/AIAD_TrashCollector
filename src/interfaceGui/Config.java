package interfaceGui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.awt.event.*;

import javax.swing.*;

import java.awt.*;
                

public class Config extends JFrame 
{
	public static JPanel controlArea = new JPanel(new GridLayout(5, 1)); //menus de seleccao
	public static JPanel drawingArea = new JPanel(new GridLayout(1, 1));
	public static WorldAgent worldAgent;
	
	
	public static class Pos
	{
		public int x,y;
		
		
		public Pos(int x,int y)
		{
			this.x=x;
			this.y=y;
			
		}
	}
	
	public Config() {
	    super("GarbageCollector");
	    WindowUtilities.setNativeLookAndFeel();
	    addWindowListener(new ExitListener());
	    Container content = getContentPane();
	    content.setBackground(Color.lightGray);
	 
	    content.add(controlArea, BorderLayout.EAST);
	    
	    // Preferred height is irrelevant, since using WEST region
	    drawingArea.setPreferredSize(new Dimension(1050,1050));
	    drawingArea.setBorder(BorderFactory.createLineBorder (Color.blue, 2));
	    content.add(drawingArea, BorderLayout.WEST);
	    pack();
	    setVisible(true);
	  }
	
	
	public static void main(String args[])
	{
	    new Config();
	    try {
	    	worldAgent = new WorldAgent();
	    	worldAgent.loadWorld();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
  
  
   
}
