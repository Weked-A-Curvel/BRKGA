package br.ufma.pesquisaOperacional;

public class Populacao {
	Individuo[] _coloracao;
	//essa variavel diz respeito a um vetor contendo
	//uma populacao, que carregam em seus cromossomos
	//solucoes para o problema de coloracao

	//contrutor de populacao
	public Populacao(int tamPopulacao, boolean inicializar, int numVert, int numCor, int[][] gArestas){
		this._coloracao = new Individuo[tamPopulacao];
		if (inicializar) {//incializar setada como verdade incia a criacao dos individuos para a populacao
			//loop para a criacao dos individuos
			for(int i = 0; i < tamPopulacao(); i++){
				Individuo individuo = new Individuo(numVert, numCor);
				individuo.gerarIndividuo();
				//corrigir genoma
				individuo.corrigirGenoma(individuo, gArestas);
				salvarColoracao(i, individuo);
			}	
		}
	}

	//metodos da classe

	//funcao que retorna o tamanho do vetor de individuos
	//contendo solucao de coloracao
	public int tamPopulacao(){
		return this._coloracao.length;
	}

	//como ja sugere, responsavel por salvar um individuo
	//passado no indice escolhido
	public void salvarColoracao(int indice, Individuo individuo){
		this._coloracao[indice] = individuo;
	}

	//getters e setters
	//retorna um individuo com uma solucao de coloracao
	public Individuo getColoracao(int indice){
		return this._coloracao[indice];
	}

	//funcao que ira pegar os dois maiores fitness
	//para criamos a nossa geracao com chaves viciadas baseadas
	//nas chaves dos pais, mas sobre o assunto na sessao de crossover
	public Individuo[] getEliteAtravesDoFitness(){
		Individuo[] elite = new Individuo[2];		
		//tomando no inicio as primeiras posicoes como maior fitness
		Individuo tempElite1 = getColoracao(0);
		Individuo tempElite2 = getColoracao(1);
		Individuo temp = null;
		//verificando a assertiva
		if(tempElite1.getFitness() < tempElite2.getFitness()){
			Individuo tmp = tempElite1;
			tempElite1 = tempElite2;
			tempElite2 = tmp;
		}

		//verificando a possibilidade de outros fitness maiores
		for(int i = 2; i < tamPopulacao(); i++){
			if(tempElite1.getFitness() <= getColoracao(i).getFitness()){
				temp = tempElite1;
				tempElite1 = getColoracao(i);
				tempElite2 = temp;
			}
		}

		//vetor colocando os vatores no vetor elite
		elite[0] = tempElite1;
		elite[1] = tempElite2;

		//retornando os vetor elite
		//PS:. espero que funcione
		return elite;
	}

}