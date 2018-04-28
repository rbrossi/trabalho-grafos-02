package parte1;

import java.util.LinkedHashSet;

public class MySet<T> extends LinkedHashSet<T> {
	
	public T addNode(T node) {
		boolean added = super.add(node);
		if (added) {
			return node;
		}
		
		T originalNode = super.stream().filter(n-> n.equals(node)).findFirst().get();		
		return originalNode;		
	}
}
