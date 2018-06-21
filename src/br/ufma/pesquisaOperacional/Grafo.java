package br.ufma.pesquisaOperacional;

import java.util.ArrayList;


public class Grafo {
	int   qtdDeVertices;
	int   qtdDeArestas;
	int   numVertices;
	int   numArestas;
	int   numDeCores;
	ArrayList<Integer> _cores;
	//HashMap<Integer, Integer> arestas;
	int [][] arestas;

	public Grafo(int numVert){
		this.numDeCores = numVert;
		this.arestas = new int[numVert][numVert];
		this._cores = new ArrayList<Integer>();
		for (int i = 0; i < numVert; i++) {
			this._cores.add(i);
			for (int j = 0; j < numVert; j++){
				arestas[i][j] = 0;
			}
		}
	}

	public void addAresta(int v1, int v2){
		if(v1 >= 0 && v2 >= 0){
			if(v1 != v2){
				arestas[v1][v2] = 1;
			}
		}
	}
	public int[][] getArestas() {
		return arestas;
	}
	
	public int getNumVert() {
		return numVertices;
	}
	
	public int getNumCores(){
		return numDeCores;
	}

	public void setNumCores(int numCores){
		this.numDeCores = numCores;
	}
	
	public ArrayList<Integer> getCores(){
		return _cores;
	}

	public void setCores(ArrayList<Integer> cores){
		this._cores = cores;
	}
}
