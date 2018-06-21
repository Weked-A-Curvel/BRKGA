package br.ufma.pesquisaOperacional;

import java.util.Random;

public class Brkga {
	/*int   populacao;
	float taxaDeCruzamento;
	float taxaDeMutacao;*/
	//parametros fixos
	private static final double _taxaDeCruzamento = 0.4;
	private static final double _taxaDeMutacao = 0.0015;
	//private static final int _tamTorneio = 5;
	private static final boolean _elitismo = true;
	//private static int mutacoes = 0;
	private static Individuo[] elite;
	private static int _numVert = 0;
	private static int _numCor = 0;

	/*Brkga(int populacao, float taxaDeCruzamento, float taxaDeMutacao){
		this.populacao = populacao;
		this.taxaDeCruzamento = taxaDeCruzamento;
		this.taxaDeMutacao = taxaDeMutacao;
	}*/

	//funcao geral do nosso algoritmo sera evoluir as gen
	//afim  de obtermos as melhores solucoes possiveis
	public static Populacao evoluirPopulacao(Populacao populacao, int numVert, int numCor, int[][] arestas){
		//uma nova populacao deve ser iniciada, essa por sua vez
		//contera a elite da gen anterior, mas os filhos e mutantes advindos dela
		_numVert = numVert;
		_numCor =  numCor;
		Populacao novaPopulacao = new Populacao(populacao.tamPopulacao(), true, numVert, numCor, arestas);
		//o elitismo setado como true nos da a possibilidade de sempre
		//pegar os melhores da gen aterior como progenitores da prox
		//aqui sao dois
		int corteDaElite = 0;
		if(_elitismo){
			elite = populacao.getEliteAtravesDoFitness();
			//vetor elite tera os dois melhores individuos
			//a seguir cascateamos esses melhores para a proxima gen
			novaPopulacao.salvarColoracao(0, elite[0]);
			novaPopulacao.salvarColoracao(1, elite[1]);
			corteDaElite = 2;
			//o corte da elite é uma tecnica vista no codigo que 
			//serviu de metodologia, onde ele limita nosso crossover
			//a partir desse indice no vetor populacional
		}

		//aplicando o crossover para formalziar a nova gen, aqui 
		//a principal tecnica do brkga sera aplicada o vicio de chaves
		//onde esse vicio é denotado atraves da selecao aleatoria entre
		//os progenitores da elite.
		for(int i = corteDaElite; i < novaPopulacao.tamPopulacao(); i++){
			Random gerador = new Random();
			if(Math.random() < _taxaDeCruzamento) {
				//a selecao dos pais aleatorios, de forma a vicialos dentro 
				//de uma mesma chave aleatoria anterior e feita aqui;
				int positPai1 = gerador.nextInt(2);
				//int positPai2 = i;
				Individuo filho = crossover(elite[positPai1], populacao.getColoracao(positPai1));			
				novaPopulacao.salvarColoracao(i, filho);
			}
		}

		//aplicacao da mutacao
		//mutacoes = 0;
		//tambem exclui a elite, porem pega seus filhos
		for (int i = corteDaElite; i < novaPopulacao.tamPopulacao(); i++) {
			if(Math.random() < _taxaDeMutacao){
				horaDeMutar(novaPopulacao.getColoracao(i));
			}
		}
		return novaPopulacao;
	}
	//funcao de crossover
	public static Individuo crossover(Individuo pai1, Individuo pai2){
		//criando uma nova instancia filha
		Individuo filho = new Individuo(_numVert, _numCor);

		//pegando a posicao de inicio e de fim dos genes a serem copiados
		//para o filho
		int posicaoInicial = (int)(Math.random()*pai1.tamCromossomo());
		int posicaoFinal = (int)(Math.random()*pai1.tamCromossomo());

		//copiando genoma para o filho a partir do pai1
		for(int i = 0; i < filho.tamCromossomo(); i++){
			//veficando se a posicao inicial ainda é menor que a final
			if(posicaoInicial < posicaoFinal && i >posicaoInicial
				&& i < posicaoFinal){
				filho.setCorCromossomica(i, pai1.getCorCromossomica(i));
			}
			else if(posicaoInicial > posicaoFinal){
				if(!(i < posicaoInicial && i > posicaoFinal)){
					filho.setCorCromossomica(i, pai1.getCorCromossomica(i));
				}
			}
		}

		//copletando o cromossomo do filho com os genomas restantes
		//do pai2
		for(int i = 0; i < pai2.tamCromossomo(); i++){
			for (int j = 0; j < filho.tamCromossomo(); j++ ) {
				if(filho.getCorCromossomica(j) < 0){
					filho.setCorCromossomica(j, pai2.getCorCromossomica(i));
					break;
				}
			}
		}
		return filho;
	}
	//Apenas entenda a piada
	private static void horaDeMutar(Individuo individuo){
		Random gerador = new Random();
		for (int i = 0; i < individuo.tamCromossomo(); i++) {
			if (Math.random() < _taxaDeMutacao) {
				//mutacoes++;
				int corCromossomica = gerador.nextInt(GerenteDeGrafo.numCores()) /*Math.random(GerenteDeGrafo.numCores())*/;
				individuo.setCorCromossomica(i, corCromossomica);
			}
		}
	}
	
}
