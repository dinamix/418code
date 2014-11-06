package data;
import java.util.*;
import java.io.*;

public class Bus {
	private String vehtypename;
	private int route;
	private int vehnr;
	private String tod;
	private double t;
	private double tqdelay;
	private double distx;
	private int stops;
	private double stim;
	private int stp;
	private int stpltns;
	private int stpalt;
	private double stpwat;
	private int stpbd;
	private double stpdwl;
	private double dwltm;
	private double v;
	private double vdes;
	private double vtheo;
	private double stpsvct;
	private double sstpsdlwt;
	private int stpwp;
	private int line;
	private int link;
	private double length;
	private String co2;
	private String nox;
	private double head;
	
	public Bus(String bus) {
		String[] array = bus.split(",");
		
		//Clean up whitespace and tabs from input
		for(int i = 0; i < array.length; i++) {
			array[i] = cleanString(array[i]);
			System.out.println(array[i]);
		}
		
		vehtypename = array[0];
		route = Integer.parseInt(array[1]);
		vehnr = Integer.parseInt(array[2]);
		tod = array[3];
		t = Double.parseDouble(array[4]);
		tqdelay = Double.parseDouble(array[5]);
		distx = Double.parseDouble(array[6]);
		stops = Integer.parseInt(array[7]);
		stim = Double.parseDouble(array[8]);
		stp = Integer.parseInt(array[9]);
		stpltns = Integer.parseInt(array[10]);
		stpalt = Integer.parseInt(array[11]);
		stpwat = Double.parseDouble(array[12]);
		stpbd = Integer.parseInt(array[13]);
		stpdwl = Double.parseDouble(array[14]);
		dwltm = Double.parseDouble(array[15]);
		v = Double.parseDouble(array[16]);
		vdes = Double.parseDouble(array[17]);
		vtheo = Double.parseDouble(array[18]);
		stpsvct = Double.parseDouble(array[19]);
		sstpsdlwt = Double.parseDouble(array[20]);
		stpwp = Integer.parseInt(array[21]);
		line = Integer.parseInt(array[22]);
		link = Integer.parseInt(array[23]);
		length = Double.parseDouble(array[24]);
		co2 = array[25];
		nox = array[26];
		head = Double.parseDouble(array[27]);
	}

	public String getVehtypename() {
		return vehtypename;
	}

	public int getRoute() {
		return route;
	}

	public int getVehnr() {
		return vehnr;
	}

	public String getTod() {
		return tod;
	}

	public double getT() {
		return t;
	}

	public double getTqdelay() {
		return tqdelay;
	}

	public double getDistx() {
		return distx;
	}

	public int getStops() {
		return stops;
	}

	public double getStim() {
		return stim;
	}

	public int getStp() {
		return stp;
	}

	public int getStpltns() {
		return stpltns;
	}

	public int getStpalt() {
		return stpalt;
	}

	public double getStpwat() {
		return stpwat;
	}

	public int getStpbd() {
		return stpbd;
	}

	public double getStpdwl() {
		return stpdwl;
	}

	public double getDwltm() {
		return dwltm;
	}

	public double getV() {
		return v;
	}

	public double getVdes() {
		return vdes;
	}

	public double getVtheo() {
		return vtheo;
	}

	public double getStpsvct() {
		return stpsvct;
	}

	public double getSstpsdlwt() {
		return sstpsdlwt;
	}

	public int getStpwp() {
		return stpwp;
	}

	public int getLine() {
		return line;
	}

	public int getLink() {
		return link;
	}

	public double getLength() {
		return length;
	}

	public double getHead() {
		return head;
	}
	
	public String toString() {
		return vehtypename + "," + route + "," + vehnr + "," + tod + "," + t +"," + stim; 
	}

	//Intake of arbitrary sized data using ArrayList object
	public static ArrayList<Object> createArraylist(File filename) throws FileNotFoundException {
		Scanner in = createScannerComma(filename);
		ArrayList<Object> arraylist = new ArrayList<Object>();
		in.nextLine();
			
		while(in.hasNextLine()) {
			arraylist.add(new Bus(in.nextLine()));
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
		Object[] busarray = new Bus[arraylist.size()];
		
		arraylist.toArray(busarray);
		
		return busarray;
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
	
	public static void main(String[] args) throws IOException {
		File file = new File("Input//cdn_165_17_new_schedule_2h.csv");
		Object[] array = createArray(file);
		
		System.out.println(array[0]);
	}

}
