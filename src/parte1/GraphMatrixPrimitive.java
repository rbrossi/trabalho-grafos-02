package parte1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class GraphMatrixPrimitive extends Graph {
	private int[][] matrix;
	private Map<Integer,Node> nodeMap;
	private int vertexCount;
	private int time;

	public GraphMatrixPrimitive(String fileNameIn, String fileNameOut) {
		super(fileNameIn, fileNameOut);
	}

	@Override
	void init() {
		nodeMap = new LinkedHashMap<Integer, Node>();
	}

	@Override
	void createFromFile(String fileNameIn) {
		try {
			FileReader reader = new FileReader(fileNameIn);
			BufferedReader buffReader = new BufferedReader(reader);

			String linha = buffReader.readLine();

			vertexCount = Integer.parseInt(linha);
			matrix = new int[vertexCount][vertexCount];

			int iteration = 0;

			linha = buffReader.readLine();
			while (linha != null) {
				System.out.println(iteration++);
				String[] edgeNodes = linha.split(" ");

				int vertex1 = Integer.parseInt(edgeNodes[0]);
				int vertex2 = Integer.parseInt(edgeNodes[1]);

				Node node1 = new Node(vertex1);
				Node node2 = new Node(vertex2);

				node1.setDegree(node1.getDegree()+1);
				node2.setDegree(node2.getDegree()+1);

				nodeMap.put(vertex1-1, node1);
				nodeMap.put(vertex2-1, node2);
				
				int edges = matrix[vertex1-1][vertex2-1];
				matrix[vertex1-1][vertex2-1] = edges + 1;
				matrix[vertex2-1][vertex1-1] = edges + 1;

				edgeCount++;
				linha = buffReader.readLine();
			}
			buffReader.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Integer> getDegreeSequence() {
		List<Integer> degreeSequence = new LinkedList<Integer>();
		for (int i = 0; i < vertexCount; i++) {
			degreeSequence.add(0);
		}
		for (int i = 0; i < matrix.length; i++) {
			int[] row = matrix[i];
			int sum = 0;
			for (int j : row) {
				sum+= j;
			}
			degreeSequence.set(i, sum);
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
		return vertexCount;
	}

	@Override
	public void DepthFirstSearch(int origin) {
		Node source = nodeMap.values().stream().filter(n -> n.getData() == origin).findFirst().get();
		int[] colorArray = new int[vertexCount];

		HashMap<Integer, Iterator<Node>> iterMap = new HashMap<>();

		// add iterators into a hashmap with label of each vertex as key
		for (Node node : nodeMap.values()) {
			iterMap.put(node.getData(), getNeighbours(node).iterator());
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
			Iterator<Node> iterator = iterMap.get(peek.getData());

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
		printTree(fileNameOut.replace(".txt", "BFS.txt"));
	}

	private void printTree(String fileName) {
		StringBuilder b = new StringBuilder();
		nodeMap.values().forEach(n -> {
			String txt = String.format("vertex: %d, father: %d, level: %d \n", n.getData(),
					n.getFather() != null ? n.getFather().getData(): null, n.getLevel());
			System.out.print(txt);
			b.append(txt);
		});

		printToFile(b.toString(), fileName);
	}

	private Set<Node> getNeighbours(Node node) {
		Set<Node> neighbours = new LinkedHashSet<>();
		
		for (int i = 0; i < matrix[node.getData()-1].length; i++) {
			if (matrix[node.getData()-1][i] > 0) {
				neighbours.add(nodeMap.get(i));
			}
		}
		
		
		return neighbours;
	}

	@Override
	public void BreadthFirstSearch(int source) {
		Queue<Node> q = new PriorityQueue<Node>();
		Node origin = nodeMap.values().stream().filter(n -> n.getData() == source).findFirst().get();
		q.add(origin);

		while (!q.isEmpty()) {
			Node u = q.poll();
			for (Node n : u.getNeighbours()) {
				if (n.getColor() == Color.WHITE) {
					q.add(n);
					n.setColor(Color.GREY);
					n.setFather(u);
					n.setLevel(u.getLevel() + 1);
				}

			}
			u.setColor(Color.BLACK);
		}
		printTree(fileNameOut.replace(".txt", "BFS.txt"));

	}

	@Override
	public String toString() {
		return String.format("vertex count: %d, edges count: %d, degree sequence: %s", getVertexCount(),
				getEdgesCount(), getDegreeSequence().toString());
	}
}
