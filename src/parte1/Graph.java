package parte1;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public abstract class Graph {
	protected int edgeCount;
	
	protected String fileNameOut;

	public Graph(String fileNameIn, String fileNameOut) {
		System.out.println("Creating graph...");
		init();
		createFromFile(fileNameIn);
		this.fileNameOut = fileNameOut;
		System.out.println("Done creating graph...");
	}
	
	abstract void init();
	abstract List<Integer> getDegreeSequence();
	abstract void createFromFile(String fileName);

	@Override
	public String toString() {
		return String.format("vertex count: %d, edges count: %d, degree sequence: %s", getVertexCount(),
				getEdgesCount(), getDegreeSequence().toString());
	}

	public void printToFile() {
		printToFile(toString(), fileNameOut);
	}

	protected void printToFile(String text, String fileName) {
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}

			PrintWriter out = new PrintWriter(file);
			out.println(text);
			out.close();
		} catch (Exception e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}
	}
	
	public abstract int getVertexCount();
	
	public int getEdgesCount() {
		return edgeCount;
	}
	
	public abstract void DepthFirstSearch(int source);
	
	public abstract void BreadthFirstSearch(int source);
}
