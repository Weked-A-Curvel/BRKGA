package br.ufma.aplicacao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import br.ufma.pesquisaOperacional.Brkga;
import br.ufma.pesquisaOperacional.Grafo;
import br.ufma.pesquisaOperacional.Individuo;
import br.ufma.pesquisaOperacional.Populacao;

public class aplicativo {
	public static void main(String[] args) throws IOException {
		Grafo _grafo = null;
		//ArrayList<String>arquivos;

		
		//arquivos = iniciarArquivos();
		
		/*
		while(arquivos.size() > 0) {
			
		}

		long tempoInicial = System.currentTimeMillis();

		Populacao populacao = new Populacao(500, true);

		for (int i = 0; i < 1500; i++) {
			populacao = Brkga.evoluirPopulacao(populacao);
		}

		System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicial));

		
		*/
		
		montandoExecucao();
		//long tempoInicial = System.currentTimeMillis();
		//System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicial));
	}
	
	public static ArrayList<String> montandoExecucao() throws IOException {
		ArrayList<String>arquivos;
		Grafo grafo = null;

		arquivos = new ArrayList<String>();
		
		if(arquivos != null) {
			arquivos.add("myciel5.txt");
			arquivos.add("queen6-6.txt");
			arquivos.add("miles750.txt");
			arquivos.add("1-Fulllns_5.txt");
			arquivos.add("frb30-15-1.clq");
			arquivos.add("frb30-15-2.clq");
			arquivos.add("frb30-15-3.clq");
			arquivos.add("frb30-15-4.clq");
			arquivos.add("frb30-15-5.clq");
			arquivos.add("frb45-21-1.clq");
			arquivos.add("frb45-21-2.clq");
			arquivos.add("frb45-21-3.clq");
			arquivos.add("frb45-21-4.clq");
			arquivos.add("frb45-21-5.clq");
		}

		int cont = 0;
		
		while(cont < arquivos.size()){
			try {
				FileReader file = new FileReader("teste1.txt");
				
				Scanner scanner2 = new Scanner(file);
				Scanner scanner = scanner2;
				String p = scanner.next();
				System.out.println(p);
				String edge = scanner.next();
				System.out.println(edge);
				int numVert = scanner.nextInt();
				System.out.println("numero Total De Vertices: " + numVert);
				int numArst = scanner.nextInt();
				System.out.println("numero Total De Arestas: " + numArst);

				grafo = new Grafo(numVert);

				while(scanner.hasNext()){
					String ignore = scanner.next();
					int v1 = scanner.nextInt();
					int v2 = scanner.nextInt();
					grafo.addAresta(v1, v2);
				}
				
				//int numVertices = scanner.nextInt();
				//int numArestas = scanner.nextInt();
				//System.out.println("Abri o Arquivo");
				scanner.close();
				scanner2.close();
				file.close();
			}catch(Exception e){
				System.out.println("Imposivel Abrir Arquivo!");
			}

			long tempoInicial = System.currentTimeMillis();

			Populacao populacao = new Populacao(500, true, grafo.getNumVert(), grafo.getNumCores(), grafo.getArestas());

			for (int i = 0; i < 1500; i++) {
				populacao = Brkga.evoluirPopulacao(populacao, grafo.getNumVert(), grafo.getNumCores(), grafo.getArestas());
			}

			Individuo[] ind = populacao.getEliteAtravesDoFitness();

			//pintando solucao
			for(int i = 0; i < ind[0].tamCromossomo(); i++){
				System.out.println("Vertice "+ i + ":"+ "cor "+ind[0].getCorCromossomica(i));
			}
			System.out.println("Cores Usadas: " + ind[0].getCores());

			System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicial));

			cont++;
		}
       /* scanner.next();
        scanner.next();
		while (scanner.hasNext()) {
			  String vert = scanner.nextInt();
			  String ed = scanner.next();
			  String compras = scanner.next();
			  System.out.println(nome);
			  System.out.println(cidade);
			  System.out.println(compras);
		}*/
		return null;
	}
}
