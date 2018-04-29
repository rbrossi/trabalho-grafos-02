package parte1;

import java.io.IOException;

public class Main {

	public static void main(String arg[]) throws IOException {
		long startTime = System.nanoTime();
		Graph graph1 = new GraphList("C:/temp/teste22.txt", "C:/temp/saida.txt");

		/*
		 * System.out.println(graph1.toString()); graph1.DepthFirstSearch(4);
		 */

		System.out.println(graph1.toString());
		graph1.printToFile();

		graph1.DepthFirstSearch(5);
		
		long endTime = System.nanoTime();

		long durationMilis = (endTime - startTime) / 1000000000;
		System.out.println(durationMilis+" seconds");

		int dataSize = 1024 * 1024;

		Runtime runtime = Runtime.getRuntime();

		System.out.println("Max memory: " + runtime.maxMemory() / dataSize + "MB");
		System.out.println("Total memory: " + runtime.totalMemory() / dataSize + "MB");
		System.out.println("Free memory: " + runtime.freeMemory() / dataSize + "MB");
		System.out.println("Used memory: " + (runtime.totalMemory() - runtime.freeMemory()) / dataSize + "MB");
		
		
	}
}
