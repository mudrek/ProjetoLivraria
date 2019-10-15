package com.livraria.apirest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livraria.apirest.models.Livro;
import com.livraria.apirest.repository.LivroRepository;

@RestController
@RequestMapping(value="/api")
public class LivroRecourser {
	
	@Autowired
	LivroRepository livroRepository;
	
	@GetMapping("/livros")
	public List<Livro> listaLivros() {
		return livroRepository.findAll();
	}

}
