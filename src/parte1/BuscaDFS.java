package parte1;

import java.util.List;
import java.util.Stack;

public class BuscaDFS {
/*
	static 

	// Recursive DFS
	public void dfs(Node node) {
		System.out.print(node.data + " ");
		List<Node> neighbours = node.getNeighbours();
		node.color = true;
		for (int i = 0; i < neighbours.size(); i++) {
			Node n = neighbours.get(i);
			if (n != null && !n.color) {
				dfs(n);
			}
		}
	}

	// Iterative DFS using stack
	public void dfsUsingStack(Node node) {
		Stack<Node> stack = new Stack<Node>();
		stack.add(node);
		node.color = true;
		while (!stack.isEmpty()) {
			Node element = stack.pop();
			System.out.print(element.data + " ");

			List<Node> neighbours = element.getNeighbours();
			for (int i = 0; i < neighbours.size(); i++) {
				Node n = neighbours.get(i);
				if (n != null && !n.color) {
					stack.add(n);
					n.color = true;

				}
			}
		}
	}
*/
	
}