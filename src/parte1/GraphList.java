package parte1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class GraphList extends Graph {
	private MySet<Node> nodes;
	private int time;

	public GraphList(String fileNameIn, String fileNameOut) {
		super(fileNameIn, fileNameOut);
	}

	@Override
	void init() {
		edgeCount = 0;
		time = 0;
		nodes = new MySet<Node>();
	}

	@Override
	void createFromFile(String fileName) {
		try {
			FileReader reader = new FileReader(fileName);
			BufferedReader buffReader = new BufferedReader(reader);

			String linha = buffReader.readLine();
			int iteration = 0;

			linha = buffReader.readLine();
			while (linha != null) {
				System.out.println(iteration++);
				String[] edgeNodes = linha.split(" ");

				int vertex1 = Integer.parseInt(edgeNodes[0]);
				int vertex2 = Integer.parseInt(edgeNodes[1]);

				Node node1 = new Node(vertex1);
				Node node2 = null;
				node1 = nodes.addNode(node1);
				node1.degree++;

				if (vertex1 != vertex2) {
					node2 = new Node(vertex2);
					node2 = nodes.addNode(node2);
					node1.addNeighbour(node2);
					node2.addNeighbour(node1);
				} else {
					node2 = node1;
					node1.addNeighbour(node2);
				}
				node2.degree++;

				// System.out.println(Arrays.toString(nodes.toArray()));
				// matrix[getIndex(nodes, node1)][getIndex(nodes, node2)]++;
				edgeCount++;
				linha = buffReader.readLine();
			}
			buffReader.close();
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("It was not possible to find the file: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("It was not possible to read the file: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}
	}

	@Override
	public List<Integer> getDegreeSequence() {
		return nodes.stream().map(n -> n.degree).sorted().collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return String.format("vertex count: %d, edges count: %d, degree sequence: %s", getVertexCount(),
				getEdgesCount(), getDegreeSequence().toString());
	}

	public void printAdjacencyList() {
		StringBuilder b = new StringBuilder();
		nodes.stream().forEach(n -> {
			b.append("\n" + n.toString());
			n.neighbours.stream().forEach(h -> {
				b.append(" -> " + h.data);
			});
		});
		System.out.println(b.toString());
	}

	@Override
	public int getVertexCount() {
		return nodes.size();
	}

	/*@Override
	public void DepthFirstSearch(int source) {
		time = 0;
		Node origin = nodes.stream().filter(n -> n.data == source).findFirst().get();
		dfsVisit(origin);

		for (Node n : nodes) {
			if (n.getColor() == Color.WHITE) {
				dfsVisit(n);
			}
		}

		printTree(fileNameOut.replace(".txt", "DFS.txt"));
	}

	private void dfsVisit(Node u) {
		u.setColor(Color.GREY);
		time++;
		u.setOpenTime(time);
		for (Node n : u.getNeighbours()) {
			if (n.getColor() == Color.WHITE) {
				n.setFather(u);
				n.setLevel(u.getLevel() + 1);
				dfsVisit(n);
			}
		}
		u.setColor(Color.BLACK);
		time++;
		u.setCloseTime(time);
	}*/

	@Override
	public void DepthFirstSearch(int origin) {
		Node source = nodes.stream().filter(n -> n.data == origin).findFirst().get();

		initVertices(source);

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
		printTree(fileNameOut.replace(".txt", "BFS.txt"));
	}

	/**
	 * Make all vertices undiscovered
	 * 
	 * @param graph
	 */
	private void initVertices(Node source) {

		for (Node node : nodes) {

			if (node != source)
				node.setColor(Color.WHITE);
		}
	}

	private void printTree(String fileName) {
		StringBuilder b = new StringBuilder();
		nodes.forEach(n -> {
			String txt = String.format("vertex: %d, father: %d, level: %d \n", n.data,
					n.father != null ? n.father.data : null, n.level);
			System.out.print(txt);
			b.append(txt);
		});

		printToFile(b.toString(), fileName);
	}

	@Override
	public void BreadthFirstSearch(int source) {
		Queue<Node> q = new PriorityQueue<Node>();
		Node origin = nodes.stream().filter(n -> n.data == source).findFirst().get();
		q.add(origin);

		while (!q.isEmpty()) {
			Node u = q.poll();
			for (Node n : u.neighbours) {
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

	private static int getIndex(Set<? extends Object> set, Object value) {
		int result = 0;
		for (Object entry : set) {
			if (entry.equals(value))
				return result;
			result++;
		}
		return -1;
	}
}
