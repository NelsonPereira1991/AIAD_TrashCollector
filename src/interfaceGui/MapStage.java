package interfaceGui;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextArea;


import javax.swing.JPanel;
import javax.swing.JTextArea;


public class MapStage extends JPanel 
{
	
	public int pos[][];

	/**
	 * 
	 */
	private static final long serialVersionUID = -4671319662944047170L;
	public int x_limit,y_limit;

	
	public MapStage(int x_limit,int y_limit)
	{
		this.x_limit=x_limit;
		this.y_limit=y_limit;
	}
	
	public void paintComponent(Graphics g)
	{
		
		super.paintComponent(g);		

		for(int row_num = 0; row_num < WorldAgent.current_map.size(); row_num++)
		{
			for(Element e : WorldAgent.current_map.get(row_num))
			{
						/*
						System.out.println("Size de current_map"+WorldAgent.current_map.size());
						System.out.println("Num de elems da row" + row_num + " : "+ WorldAgent.current_map.get(row_num).size());
						System.out.println("x: " + e.getCoords().x + " y: "+e.getCoords().y + " symbol: " + e.getType());
				 		*/

				int cellX = (10 + e.getCoords().x * 15);
				int cellY = (10 + e.getCoords().y * 15);

				if(e.getType().equals(Elem_type.WALKWAY))
					g.setColor(Color.GRAY);
				else if(e.getType().equals(Elem_type.ROAD))
					g.setColor(Color.WHITE);
				else if(e.getType().equals(Elem_type.DEPOT))
					g.setColor(Color.RED);
				else if(e.getType().equals(Elem_type.DOMESTIC_CONTAINER))
					g.setColor(Color.BLACK);
				else if(e.getType().equals(Elem_type.GLASS_CONTAINER))
					g.setColor(Color.GREEN);
				else if(e.getType().equals(Elem_type.PAPER_CONTAINER))
					g.setColor(Color.BLUE);
				else if(e.getType().equals(Elem_type.PACKAGE_CONTAINER))
					g.setColor(Color.YELLOW);
				else if(e.getType().equals(Elem_type.DOMESTIC_COLLECTOR))
					g.setColor(Color.PINK);
				else if(e.getType().equals(Elem_type.GLASS_COLLECTOR))
					g.setColor(Color.MAGENTA);
				else if(e.getType().equals(Elem_type.PAPER_COLLECTOR))
					g.setColor(Color.CYAN);
				else if(e.getType().equals(Elem_type.PACKAGE_COLLECTOR))
					g.setColor(Color.ORANGE);
				
				/*
				if(e.getType().equals(Elem_type.CAR) || e.getType().equals(Elem_type.CAR_ENTRY))
				{
					
				/*	g.setColor(Color.ORANGE);
					g.drawRect(cellX, cellY, 15, 15);
					*/
					/*provisorio*/
		/*			g.setColor(Color.BLACK);
					g.drawRect(cellX, cellY, 15, 15);
					((Graphics2D) g).setColor(Color.ORANGE);
					((Graphics2D) g).setStroke(new BasicStroke(3f));
				
				
					
				}
				else if(e.getType().equals(Elem_type.GREEN_SEM))
					g.setColor(Color.GREEN);
				else if(e.getType().equals(Elem_type.RED_SEM))
					g.setColor(Color.RED);
				else if(e.getType().equals(Elem_type.EXIT))
					g.setColor(Color.PINK);
				else if(e.getType().equals(Elem_type.ENTRY))
					g.setColor(Color.YELLOW);
				else if(e.getType().equals(Elem_type.CAR_GREEN) || e.getType().equals(Elem_type.CAR_RED))	
					{
					//g.setColor(Color.ORANGE);
						
						g.setColor(Color.BLACK);
						g.drawRect(cellX, cellY, 15, 15);
						g.setColor(Color.ORANGE);
						((Graphics2D) g).setStroke(new BasicStroke(3f));
					}
				
*/
				//g.drawRect(cellX, cellY, 15, 15);
				g.fillRect(cellX, cellY, 15, 15);
				
				//g.setColor(Color.RED);
				//g.drawString("Simulation time: " + Long.toString(WorldAgent.now/1000 ),5,(WorldAgent.map_y_limit + cellY) * 15);
				g.drawString("Legenda: PACKAGE_COLL - Orange"
						+ "PAPER_COLL - cyan"
						+ "DOMESTIC_COLL - Pink"
						+ "GLASS_COLL - Magenta " ,5,(WorldAgent.map_y_limit + cellY) * 15);

			}

		}
	
	}
}
