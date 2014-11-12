package data;
import java.util.*;
import java.io.*;

public class Node {
	private double delay;
	private int fromlink;
	private int tolink;
	private double maxqueue;
	private int node;
	private int veh;
	private String movement;
	private String los;
	
	public Node(String nodes) {
		String[] array = nodes.split(",");
		
		//Clean up whitespace and tabs from input
		for(int i = 0; i < array.length; i++) {
			array[i] = cleanString(array[i]);
			//System.out.println(array[i]);
		}
		
		delay = Double.parseDouble(array[0]);
		fromlink = Integer.parseInt(array[1]);
		tolink = Integer.parseInt(array[2]);
		maxqueue = Double.parseDouble(array[3]);
		node = Integer.parseInt(array[4]);
		veh = Integer.parseInt(array[5]);
		movement = array[6];
	}
	
	public double getDelay() {
		return delay;
	}

	public int getFromlink() {
		return fromlink;
	}

	public int getTolink() {
		return tolink;
	}

	public double getMaxqueue() {
		return maxqueue;
	}

	public int getNode() {
		return node;
	}

	public int getVeh() {
		return veh;
	}

	public String getMovement() {
		return movement;
	}
	
	public String getLos() {
		return los;
	}
	
	public String toString() {
		return node + "," + delay + "," + veh; 
	}
	
	//Intake of arbitrary sized data using ArrayList object
	public static ArrayList<Object> createArraylist(File filename) throws FileNotFoundException {
		Scanner in = createScannerComma(filename);
		ArrayList<Object> arraylist = new ArrayList<Object>();
		in.nextLine();
			
		while(in.hasNextLine()) {
			arraylist.add(new Node(in.nextLine()));
		}
		
		arraylist.trimToSize();
		in.close();
		return arraylist;
	}
	
	//create a bus object array given specified file GENERIC
	public static Object[] createArray(File file) throws IOException {
		ArrayList<Object> arraylist = createArraylist(file);
		Object[] busarray = convertArraylist(arraylist);
		return busarray;
	}
	
	//convert arraylist in order to make array GENERIC
	public static Object[] convertArraylist(ArrayList<Object> arraylist) {
		Object[] nodearray = new Node[arraylist.size()];
		
		arraylist.toArray(nodearray);
		
		return nodearray;
	}
	
	//creates a scanner object that is comma delimited for various STM file to 
	//avoid any kind of whitespace delimiting issues
	public static Scanner createScannerComma(File filename) throws FileNotFoundException {
		Scanner in = new Scanner(filename);
		in.useDelimiter(",");
		return in;
	}
	
	public static String cleanString(String line) {
		return line.replaceAll("\\s+","");
	}	
	
	public static void outputLOS(File losfile, Node[] nodearray) throws IOException {
		PrintWriter out = new PrintWriter(losfile);
		out.println("node" + "," + "totaldelay" + "," + "LOS");
		double UV = 0;
		
		for(int i = 0; i < nodearray.length - 1; i++) {
			
			if(nodearray[i].node == nodearray[i+1].node) {
				UV += (nodearray[i].veh * nodearray[i].delay);
			}
			else if(nodearray[i].movement.equals("All")) {
				double totaldelay = (UV / nodearray[i].veh);
				nodearray[i].los = computeLOS(totaldelay);
				out.println(nodearray[i].node + "," + totaldelay + "," + nodearray[i].los);
				
				//reset UV here
				UV = 0;
			}
		}
		out.close();
	}
	
	public static String computeLOS(double delay) {
		String los = "";
		
		if(delay <= 10) {
			los = "A";
		}
		else if(delay > 10 && delay <= 20) {
			los = "B";
		}
		else if(delay > 20 && delay <= 35) {
			los = "C";
		}
		else if(delay > 35 && delay <= 55) {
			los = "D";
		}
		else if(delay > 55 && delay <= 80) {
			los = "E";
		}
		else if(delay > 80) {
			los = "F";
		}
		
		return los;
	}

	public static void main(String[] args) throws IOException {
		File file = new File("Input//cdn_165_17_new_schedule_1.csv");
		
		Object[] array = createArray(file);
		Node[] nodearray = (Node[])array;
		
		System.out.println(nodearray.length);
		System.out.println(nodearray[0]);
		
		File newfile = new File("Output//nodetestout.csv");
		outputLOS(newfile,nodearray);
	}

}
