package br.ufma.pesquisaOperacional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Individuo {
	private ArrayList<Integer> _cromossomo = new ArrayList<Integer>(); //um vetor com as cores usadas cada indice representa um vertice
	private double   _fitness = 0;
	private double   _cores = 0;
	private int   _maxColors = 0; 
	private int _numVert = 0;

	//construtores
	public Individuo(int numVert, int numCor){
		_numVert = numVert;
		for (int i = 0; i < numVert; i++){
			this._cromossomo.add(Integer.MIN_VALUE);
		}
		//this._maxColors = GerenteDeGrafo.numCores();
		this._maxColors = numCor;
	}

	public Individuo(ArrayList<Integer> cromossomo){
		this._cromossomo = cromossomo;
	}	

	//metodos
	//gerar individuo irar preencher o cromossomo
	public void gerarIndividuo(){
		Random geradorDeCores = new Random();
		for(int i = 0; i < this._cromossomo.size(); i++){
			int corRand = geradorDeCores.nextInt(this._maxColors);
			setCorCromossomica(i, corRand);
		}
		//isso significa dizer que o cromossomo sera
		//o mais aleatorio prossivel na geracao
		//contudo o cromossomo ainda deve ser corrigido.
		Collections.shuffle(this._cromossomo);
	}

	//corrigir genoma, é uma funcao que tem como objetivo
	//ajustar as restricoes da funcao objetivo
	/*
		sao eles:
		*um vertice colorido, nao pode ter seus adjacentes
		com cor semelhante a sua
		*se necessario uma cor dentro do range max deve ser 
		adicionada
		*se isso nao puder acontecer nenhum idividuo dessa geracao
		o fara entao nossa populacao nao pode melhorar a solucao do problema
	*/
	public void corrigirGenoma(Individuo individuo, int[][] gArestas){
		//int [][] gArestas = GerenteDeGrafo.arestas();
		for (int i = 0; i < _numVert; i++){
			for (int j = 0; j < _numVert; j++){
				if(gArestas[i][j] == 1){ 
					int cor1 = individuo.getCorCromossomica(i);
					int cor2 = individuo.getCorCromossomica(j);
					if(cor1 == cor2){
						int maiorCor = cor2; 
						int menorCor = cor2;
						//ArrayList<Integer> coresAdj = new ArrayList<Integer>();
						for(int k = 0; k < _numVert; k++){
							if(gArestas[j][k] == 1){
								if(maiorCor < individuo.getCorCromossomica(k)){
									int tmp = maiorCor;
									maiorCor = individuo.getCorCromossomica(k);
									menorCor = tmp;
								}
							}
						}

						if(menorCor > 0){
							cor2 = menorCor - 1;
							individuo.setCorCromossomica(j, cor2);
						}else{
							cor2 = maiorCor + 1;
							individuo.setCorCromossomica(j, cor2);
						}
					}
				}
			}

		}
	}

	//getters e setters
	//pega um dos genes do cromossomo
	public int getCorCromossomica(int indice){
		return this._cromossomo.get(indice);
	}

	public void setCorCromossomica(int indice, int cor){
		this._cromossomo.set(indice, cor);
		_fitness = 0;
		_cores = 0;
	}

	//calculo da apitidao do cromossomo, fitness.
	//o fitness para o nosso problema é um em que as cores,
	//sejam as minimas possiveis, entao o fitness sera maior
	public double getFitness(){
		//se o fitness ainda nao foi calculado
		if(this._fitness == 0){
			//descobrindo quantas cores nossa solucao tem
			this._fitness = 1/getCores();
		}
		return this._fitness;
	}

	//metodo para retornar a quantidade de cores usadas no 
	//cromossomo dessa solucao
	public double getCores(){
		ArrayList<Integer> tempCores = new ArrayList<Integer>();
		int leght = 0;

		leght = tamCromossomo();
		tempCores.add(getCorCromossomica(0));

		for(int i = 1; i < leght; i++){
			boolean flag = true;
			for (int j = 0; j < tempCores.size(); j++ ) {
				if(tempCores.get(j) == getCorCromossomica(i)){
					flag = false;
					break;
				}	
			}
			if(flag){
				tempCores.add(getCorCromossomica(i));
			}
		}
		this._cores = tempCores.size();

	

		return this._cores;
	}

	public boolean corContida(int cor){
		return this._cromossomo.contains(cor);
	}

	public int tamCromossomo(){
		return this._cromossomo.size();
	}

	
}
