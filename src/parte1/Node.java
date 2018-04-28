package parte1;

import java.util.ArrayList;
import java.util.List;

class Node implements Comparable<Node> {
	int data;
	Color color;
	List<Node> neighbours;
	int degree;
	int openTime;
	int closeTime;
	Node father;
	int level;
	//int distance;
	
	Node(int data) {
		this.data = data;
		this.neighbours = new ArrayList<>();
		this.degree = 0;
		this.openTime = 0;
		this.closeTime = 0;
		this.color = Color.WHITE;
		this.father = null;
		this.level = 0;
		//this.distance = 0;
	}

	public void addneighbours(Node neighbourNode) {
		this.neighbours.add(neighbourNode);
	}

	public List<Node> getNeighbours() {
		return neighbours;
	}

	public void addNeighbour(Node node) {
		neighbours.add(node);
	}

	public void setNeighbours(List<Node> neighbours) {
		this.neighbours = neighbours;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	@Override
	public int compareTo(Node o) {
		return this.data - o.data;
	}

	public int getOpenTime() {
		return openTime;
	}

	public void setOpenTime(int openTime) {
		this.openTime = openTime;
	}

	public int getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(int closeTime) {
		this.closeTime = closeTime;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Node other = (Node) obj;
		return other.data == this.data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + data;
		return result;
	}

	@Override
	public String toString() {
		return String.valueOf(data);
	}

	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
	}
	
	public int getLevel(){
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
}