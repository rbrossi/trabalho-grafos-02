package parte1;

import java.util.ArrayList;
import java.util.List;

class Node {
		int data;
		boolean visited;
		List<Node> neighbours;

		Node(int data) {
			this.data = data;
			this.neighbours = new ArrayList<>();

		}

		public void addneighbours(Node neighbourNode) {
			this.neighbours.add(neighbourNode);
		}

		public List<Node> getNeighbours() {
			return neighbours;
		}

		public void setNeighbours(List<Node> neighbours) {
			this.neighbours = neighbours;
		}
	}