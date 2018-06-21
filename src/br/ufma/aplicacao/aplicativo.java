package br.ufma.aplicacao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import br.ufma.pesquisaOperacional.Brkga;
import br.ufma.pesquisaOperacional.Populacao;

public class aplicativo {
	public static void main(String[] args) throws IOException {
		ArrayList<String>arquivos;
		
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
		
		iniciarArquivos();
	}
	
	public static ArrayList<String> iniciarArquivos() throws IOException {
		ArrayList<String>arquivos;
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
		
		
		try {
			FileReader file = new FileReader("teste1.txt");
			
			Scanner scanner2 = new Scanner(file);
			Scanner scanner = scanner2;
			String p = scanner.next();
			System.out.println(p);
			String edge = scanner.next();
			System.out.println(edge);
			int num = scanner.nextInt();
			
			//int numVertices = scanner.nextInt();
			//int numArestas = scanner.nextInt();
			System.out.println("Abri o Arquivo");
			scanner.close();
			scanner2.close();
			file.close();
		}catch(Exception e){
			System.out.println("Imposivel Abrir Arquivo!");
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
