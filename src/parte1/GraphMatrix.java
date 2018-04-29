package parte1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GraphMatrix extends Graph {
	private LinkedList<LinkedList<Integer>> matrix;
	private Map<Node, Integer> nodeMap;
	private int vertexCount;
	private int time;

	public GraphMatrix(String fileNameIn, String fileNameOut) {
		super(fileNameIn, fileNameOut);
	}

	@Override
	void init() {
		matrix = new LinkedList<LinkedList<Integer>>();
		nodeMap = new HashMap<Node, Integer>();
	}

	@Override
	void createFromFile(String fileNameIn) {
		try {
			FileReader reader = new FileReader(fileNameIn);
			BufferedReader buffReader = new BufferedReader(reader);

			String linha = buffReader.readLine();

			vertexCount = Integer.parseInt(linha);
			// initMatrix(vertexCount);

			int iteration = 0;
			int countIndex = 0;

			linha = buffReader.readLine();
			while (linha != null) {
				System.out.println(iteration++);
				String[] edgeNodes = linha.split(" ");

				int vertex1 = Integer.parseInt(edgeNodes[0]);
				int vertex2 = Integer.parseInt(edgeNodes[1]);

				Node node1 = new Node(vertex1);
				Node node2 = new Node(vertex2);

				if (!nodeMap.containsKey(node1)) {
					nodeMap.put(node1, countIndex++);
				}

				if (!node1.equals(node2) && !nodeMap.containsKey(node2)) {
					nodeMap.put(node2, countIndex++);
				}

				int indexNode1 = nodeMap.get(node1);
				int indexNode2 = nodeMap.get(node2);

				// System.out.println(String.format("x: %d y: %d", indexNode1, indexNode2));

				int higherIndex = Math.max(indexNode1, indexNode2);

				int toBeAdded = (higherIndex + 1) - matrix.size();

				for (int i = 0; i < toBeAdded; i++) {
					matrix.add(new LinkedList<Integer>());
				}
				
				for (int i = 0; i < matrix.size(); i++) {
					while (matrix.get(i).size() <= higherIndex) {
						matrix.get(i).add(0);
					}
				}

				Integer edges = matrix.get(indexNode1).get(indexNode2);
				matrix.get(indexNode1).set(indexNode2, edges + 1);
				matrix.get(indexNode2).set(indexNode1, edges + 1);

				node1.degree++;
				node2.degree++;

				edgeCount++;
				linha = buffReader.readLine();
			}
			buffReader.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initMatrix(int verticeCount) {
		System.out.println("init matrix...");
		for (int i = 0; i < verticeCount; i++) {
			LinkedList<Integer> list = new LinkedList<Integer>();
			for (int j = 0; j < verticeCount; j++) {
				list.add(0);
			}
			matrix.add(list);
		}
		System.out.println("done init matrix...");
	}

	@Override
	public List<Integer> getDegreeSequence() {
		List<Integer> degreeSequence = new LinkedList<Integer>();
		for (int i = 0; i < vertexCount; i++) {
			degreeSequence.add(0);
		}
		for (int i = 0; i < matrix.size(); i++) {
			LinkedList<Integer> row = matrix.get(i);
			int degree = row.stream().mapToInt(e -> e).sum();
			degreeSequence.set(i, degree);
		}
		return degreeSequence.stream().sorted().collect(Collectors.toList());
	}
	
	public LinkedList getNeighbours(int index) {
		
		/*Stream<Integer> neighboursStream = matrix.get(index).stream().filter(x -> x > 0);
		List<Integer> neighbours = neighboursStream.map(i -> nodeMap.values().stream().filter(j -> j == i).findFirst().get()).collect(Collectors.toList());
		*/
		
		return null;
	}

	@Override
	public int getVertexCount() {
		return nodeMap.keySet().size();
	}

	@Override
	public void DepthFirstSearch(int origin) {
		/*Node source = nodeMap.keySet().stream().filter(n -> n.data == origin).findFirst().get();
		int[] colorArray = new int[vertexCount];

		HashMap<Integer, Iterator<Node>> iterMap = new HashMap<>();

		// add iterators into a hashmap with label of each vertex as key
		for (Node node : nodes) {
			iterMap.put(node.data, node.neighbours.iterator());
		}

		Stack<Node> stack = new Stack<>();

		time = time + 1;
		source.setColor(Color.GREY);
		source.setOpenTime(time);
		stack.push(source);

		while (!stack.isEmpty()) {

			Node peek = stack.peek();

			// get the iterator of peek so that iterator will continue
			// from its last position.
			Iterator<Node> iterator = iterMap.get(peek.data);

			if (iterator.hasNext()) {

				Node adjVertex = iterator.next();

				if (adjVertex.getColor() != Color.WHITE) {
					time = time + 1;
					adjVertex.setOpenTime(time);
					adjVertex.setColor(Color.GREY);
					stack.push(adjVertex);
				}
			} else {
				time = time + 1;
				peek.setCloseTime(time);
				stack.pop();
			}
		}
		printTree(fileNameOut.replace(".txt", "BFS.txt"));*/
	}

	@Override
	public void BreadthFirstSearch(int source) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return String.format("vertex count: %d, edges count: %d, degree sequence: %s", getVertexCount(),
				getEdgesCount(), getDegreeSequence().toString());
	}
}
