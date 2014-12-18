package map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import objects.Collector;
import objects.Container;
import objects.GarbageDepot;
import objects.GarbageObject;

import org.bridj.cpp.com.COMRuntime.COINIT;

import plans.DepositWastePlan;

public class readFile {

	
	public static final char COMMENT = '#';
	public static final char SIZE_MAP='S';
	
	public static objects.GarbageObject garbObject;
	
	public int x; //sizeX map
	public int y; //sizeY map
	
	public readFile(String fileMap, String fileCollectors, String fileContainers, String fileDepot)throws FileNotFoundException{
			if (fileMap != null)
				readFileMap(fileMap);
			if(fileCollectors != null)
				readFileCollectors(fileCollectors);
			if(fileContainers != null)
				readFileContainers(fileContainers);
			if(fileDepot != null)
				readFileDepot(fileDepot);
	}
	
	private void readFileDepot(String filename) throws FileNotFoundException{
		File f = new File(filename);
		Scanner readDepot = new Scanner(f);
		
		int roads=0;
		int lines=0;
		while(readDepot.hasNextLine()){
			if (parseDepotLine(readDepot.nextLine(), lines)) {
				roads++;
			}
			lines++;
		}
		System.out.println("Read " + roads + " roads.");
		
		readDepot.close();
	}
	
	private boolean parseDepotLine(String nextLine, int line) {
		if (nextLine.length() == 0 || nextLine.toCharArray()[0] == COMMENT)
			return false;
	
		int x = -1,y = -1;
		String[] thisLine = nextLine.split(",");
		System.out.println("Depots:");
		try {
			x = Integer.parseInt(thisLine[0]);
			System.out.println("x = "  + x);
			y = Integer.parseInt(thisLine[1]);
			System.out.println("y = " + y);
		} catch (NumberFormatException ex) {
				System.out.println("Invalid road at line " + line + ".");
		}
		Location location = new Location(x, y);
		GarbageDepot depot = new GarbageDepot(location);
		garbObject.depots.add(depot);
		return true;
	}

	private void readFileContainers(String filename) throws FileNotFoundException{
		File f = new File(filename);
		Scanner readContainers = new Scanner(f);
		
		int roads=0;
		int lines=0;
		while(readContainers.hasNextLine()){
			if (parseContainersLine(readContainers.nextLine(), lines)) {
				roads++;
			}
			lines++;
		}
		System.out.println("Read " + roads + " roads.");
		
		readContainers.close();
	}
	
	private boolean parseContainersLine(String nextLine, int line) {
		if (nextLine.length() == 0 || nextLine.toCharArray()[0] == COMMENT)
			return false;
		int type = -1, x = -1, y = -1, capacityMax = 0;
		String[] thisLine = nextLine.split("/");
		String[] coord = thisLine[1].split(",");
		
		System.out.println("Containers:");
		try {
			type = Integer.parseInt(thisLine[0]);
			System.out.println("type = "  + type);
			x = Integer.parseInt(coord[0]);
			System.out.println("x = " + x);
			y = Integer.parseInt(coord[1]);
			System.out.println("y = " + y);
			capacityMax = Integer.parseInt(thisLine[2]);
			System.out.println("capacity = " + capacityMax);
			if (type != 1 && type != 2 && type != 3 && type != 4)
				throw new NumberFormatException();
		} catch (NumberFormatException ex) {
				System.out.println("Invalid road at line " + line + ".");
		}
		Location location = new Location(x, y);
		String wasteType = null;
		switch (type) {
		case 1:
			wasteType = "Papel";
			break;
		case 2:
			wasteType = "Vidro";
			break;
		case 3:
			wasteType = "Embalagem";
			break;
		case 4:
			wasteType = "Domesticos";
			break;

		default:
			break;
		}
		Container containerObject = new Container(location, wasteType, capacityMax);
		garbObject.containers.add(containerObject);
		
		return true;
		
	}
	
	private void readFileCollectors(String filename) throws FileNotFoundException{
		File f = new File(filename);
		Scanner readCollectors = new Scanner(f);
		
		int roads=0;
		int lines=0;
		while(readCollectors.hasNextLine()){
			if (parseCollectorsLine(readCollectors.nextLine(), lines)) {
				roads++;
			}
			lines++;
		}
		System.out.println("Read " + roads + " roads.");
		
		readCollectors.close();
	}
	
	private boolean parseCollectorsLine(String nextLine, int line) {
		if (nextLine.length() == 0 || nextLine.toCharArray()[0] == COMMENT)
			return false;
		
		int type = -1, x = -1, y = -1, capacityMax = -1;
		String[] thisLine = nextLine.split("/");
		String[] coord = thisLine[1].split(",");
		
		System.out.println("Collectors:");
		try {
			type = Integer.parseInt(thisLine[0]);
			System.out.println("type = "  + type);
			x = Integer.parseInt(coord[0]);
			System.out.println("x = " + x);
			y = Integer.parseInt(coord[1]);
			System.out.println("y = " + y);
			capacityMax = Integer.parseInt(thisLine[2]);
			System.out.println("capacity = " + capacityMax);
			if (type != 1 && type != 2 && type != 3 && type != 4)
				throw new NumberFormatException();
		} catch (NumberFormatException ex) {
				System.out.println("Invalid road at line " + line + ".");
		}
		Location location = new Location(x, y);
		String wasteType = null;
		
		switch (type) {
		case 1:
			wasteType = "Papel";
			break;
		case 2:
			wasteType = "Vidro";
			break;
		case 3:
			wasteType = "Embalagem";
			break;
		case 4:
			wasteType = "Domesticos";
			break;

		default:
			break;
		}
		Collector collector = new Collector(location, wasteType, capacityMax);
		garbObject.collectors.add(collector);
		return true;
	}

	private void readFileMap(String filename) throws FileNotFoundException{
		File f= new File(filename);
		Scanner readMap= new Scanner(f);
		
		int roads=0;
		int lines=0;
		while(readMap.hasNextLine()){
			
			if (parseLine(readMap.nextLine(), lines)) {
				roads++;
			}
			lines++;
		}
		System.out.println("Read " + roads + " roads.");
		
		readMap.close();
	}
	
	private boolean parseLine(String nextLine, int line){
		if(nextLine.length()==0 || nextLine.toCharArray()[0] == COMMENT)
			return false;
		if(nextLine.toCharArray()[0]==SIZE_MAP){
			System.out.println("Map:");
			try{
				String[] size_map = nextLine.split(" ");
				size_map = size_map[1].split("x");
				x = Integer.parseInt(size_map[0]);
				System.out.println("SIZEX = " + x);
				y = Integer.parseInt(size_map[1]);
				System.out.println("SIZEY = " + y);
			}
			catch(NumberFormatException except){
				System.err.println("Bad size");
				System.exit(1);
			}
			return false;
		}	
		int xi, yi, xf, yf, way;
		String[] thisLine = nextLine.split("/");
		String[] initial = thisLine[0].split(",");
		String[] destination = thisLine[1].split(",");
		
		try {
			xi = Integer.parseInt(initial[0]);
			System.out.println("xi = "  + xi);
			yi = Integer.parseInt(initial[1]);
			System.out.println("yi = " + yi);
			xf = Integer.parseInt(destination[0]);
			System.out.println("xf = " + xf);
			yf = Integer.parseInt(destination[1]);
			System.out.println("yf = " + yf);
			way = Integer.parseInt(thisLine[2]);
			System.out.println("way = " + way);
			if (way != 0 && way != 1)
				throw new NumberFormatException();
		} catch (NumberFormatException ex) {
				System.out.println("Invalid road at line " + line + ".");
		}
		return true;
		
	}		
	
	/*
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		garbObject = new GarbageObject();
		readFile r = new readFile("map.txt", "collectors.txt", "containers.txt","depot.txt");
		
		Location location = new Location(5, 3);
		Container container = garbObject.getContainerByLocation(location);
		
		System.out.println("container id is:" + container.getContainerID());
		
		
		
	}
	
	*/
	
		

}
