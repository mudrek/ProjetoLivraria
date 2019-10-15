package com.questoes;

public class Questao1 {

	public static void main(String[] args) {

		String[] palavras = {"Pedro", "Maria", "Joana", "André", 
								"Carlos", "anna", "augusto", "Henrique"};
		
		System.out.println("Palavras que começam com a letra 'a' sendo maiúscula ou não\n");
		for(String palavra : palavras) {
			if(palavra.toLowerCase().startsWith("a")) {
				System.out.println(palavra);
			}
		}
	}

}
