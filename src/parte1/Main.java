package parte1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Main {

//	public static void main(String[] args) throws IOException {
//		ArrayList<String> listaVertices = new ArrayList<String>();
//		String partLinha = "";
//		int numeroArestas = -1;
//		FileReader arq = new FileReader("C:/temp/entrada.txt");
//		BufferedReader lerArq = new BufferedReader(arq);
//		String linha = lerArq.readLine();
//		System.out.println("Número de vértices: " + linha.toString());
//		while (linha != null) {
//
//			if (linha.contains(" ")) {
//				partLinha = linha.substring(0, linha.indexOf(" "));
//				partLinha = partLinha.trim();
//				listaVertices.add(partLinha);
//				partLinha = linha.substring(linha.indexOf(" "), linha.length());
//				partLinha = partLinha.trim();
//				listaVertices.add(partLinha);
//
//			}
//
//			numeroArestas++;
//
//			linha = lerArq.readLine();
//			continue;
//		}
//		System.out.println("Número de arestas: " + numeroArestas);
//		System.out.println("Vértices: " + listaVertices);
//
//		Map<String, Integer> contagem = new HashMap<String, Integer>();
//		//Passo 1: Montar um mapa que associa o valor a quantas vezes ele pareceu
//		for (String valor : listaVertices) {
//		   if (!contagem.containsValue(valor)) {
//		       contagem.put(valor, 0);
//		   }
//		   contagem.put(valor, contagem.get(valor)+1);
//		}   
//		//Passo 2: Criar um TreeSet que ordene pela contagem
//		Set<Map.Entry<String, Integer>> valores = new TreeSet<Map.Entry<String, Integer>>(
//		   new Comparator<Map.Entry<String, Integer>>() {
//		      public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//		          return o1.getValue().compareTo(o2.getValue());
//		      }
//		   });
//		valores.addAll(contagem.entrySet());
//		//Passo 3: Exibir os 10 maiores:
//		int exibidos = 0;
//		for (Map.Entry<String, Integer> valor : valores) {
////		   System.out.printf("Número: %d   Vezes: %d", valor.getKey(), valor.getValue());
//			System.out.println(valor.getValue());
//		   exibidos++;
//		   if (exibidos == 10) break;
//		}
//		
//	}

	public static void main(String arg[]) throws IOException {

		ArrayList<String> listaVertices = new ArrayList<String>();
		String partLinha = "";
		int numeroArestas = -1;
		FileReader arq = new FileReader("C:/temp/entrada.txt");
		BufferedReader lerArq = new BufferedReader(arq);
		String linha = lerArq.readLine();
		System.out.println("Número de vértices: " + linha.toString());
		while (linha != null) {

			if (linha.contains(" ")) {
				partLinha = linha.substring(0, linha.indexOf(" "));
				Integer.parseInt(partLinha);
				partLinha = linha.substring(linha.indexOf(" "), linha.length());
				partLinha = partLinha.trim();
				listaVertices.add(partLinha);

			}

			numeroArestas++;

			linha = lerArq.readLine();
			continue;
		}
		System.out.println("Número de arestas: " + numeroArestas);
		System.out.println("Vértices: " + listaVertices);
		
		Node node40 = new Node(40);
		Node node10 = new Node(10);
		Node node20 = new Node(20);
		Node node30 = new Node(30);
		Node node60 = new Node(60);
		Node node50 = new Node(50);
		Node node70 = new Node(70);
  
		node40.addneighbours(node10);
		node40.addneighbours(node20);
		node10.addneighbours(node30);
		node20.addneighbours(node10);
		node20.addneighbours(node30);
		node20.addneighbours(node60);
		node20.addneighbours(node50);
		node30.addneighbours(node60);
		node60.addneighbours(node70);
		node50.addneighbours(node70);

		BuscaDFS dfsExample = new BuscaDFS();

		System.out.println("The DFS traversal of the graph using stack ");
		dfsExample.dfsUsingStack(node40);

		System.out.println();

		// Resetting the visited flag for nodes
		node40.visited = false;
		node10.visited = false;
		node20.visited = false;
		node30.visited = false;
		node60.visited = false;
		node50.visited = false;
		node70.visited = false;

		System.out.println("The DFS traversal of the graph using recursion ");
		dfsExample.dfs(node40);
	}
}
