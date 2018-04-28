package parte1;

import java.io.IOException;

public class Main {

	public static void main(String arg[]) throws IOException {
		Graph graph1 = new GraphList("C:/temp/teste1.txt", "C:/temp/saida.txt");

		/*System.out.println(graph1.toString());
		graph1.DepthFirstSearch(4);*/
		
		System.out.println(graph1.toString());
		graph1.printToFile();
		graph1.DepthFirstSearch(5);
	}
}
