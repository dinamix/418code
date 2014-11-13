package data;
import java.io.File;
import java.io.IOException;

//THIS IS JUST USED TO RUN NODE AND BUS FILES IN EXE
public class RunNode {
	public static void main(String[] args) throws IOException {
		File inputfolder = new File("NodeInput//");
		File[] fileinput = inputfolder.listFiles();

		for(int i = 0; i < fileinput.length; i++) {
			Object[] array = Node.createArray(fileinput[i]);
			Node[] nodearray = (Node[])array;	
			File newfile = new File("NodeOutput//output_" + fileinput[i].getName());
			Node.outputLOS(newfile,nodearray);
		}
	}
}
