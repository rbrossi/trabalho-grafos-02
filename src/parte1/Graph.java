package parte1;

import java.util.List;

public interface Graph {	
	List<Integer> getDegreeSequence();
	
	@Override
	String toString();
	
	void printToFile();
	
	int getVertexCount();
	
	int getEdgesCount();
	
	void DepthFirstSearch(int source);
	
	void BreadthFirstSearch(int source);
}
