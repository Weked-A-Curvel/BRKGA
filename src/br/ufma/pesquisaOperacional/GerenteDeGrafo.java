package br.ufma.pesquisaOperacional;

public class GerenteDeGrafo {
	private static Grafo _grafo = new Grafo();

	public static int numVertices(){
		return _grafo.numVertices;
	}

	public static int numCores(){
		return _grafo.numDeCores;
	}

	/*public static HashMap<Integer, Integer> arestas(){
		return _grafo.arestas;
	}*/

	public static int[][] arestas(){
		return _grafo.arestas;
	}
}