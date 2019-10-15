package com.questoes;

import java.util.HashMap;
import java.util.Map;

public class Questao2 {

	public static void main(String[] args) {

		Map<String, String> mapaSenhas = new HashMap<String, String>();
		mapaSenhas.put("Roberto", "Roberto@123");
		mapaSenhas.put("Reginaldo", "Reginaldo@123");
		mapaSenhas.put("Rafaela", "Rafaela@123");
		mapaSenhas.put("Josue", "Josue@123");

		System.out.println(mapaSenhas);
		
		System.out.println("A senha de Josue é: " + mapaSenhas.get("Josue"));
		
	}

}
