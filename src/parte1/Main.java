package parte1;

import java.io.IOException;	

public class Main {	
	public static void main(String arg[]) throws IOException {
		long startTime = System.nanoTime();
		
		Graph graph1 = new GraphMatrixPrimitive("C:/temp/entrada.txt", "C:/temp/saida.txt");		
		System.out.println(graph1.toString());
		graph1.BreadthFirstSearch(1);
		
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
