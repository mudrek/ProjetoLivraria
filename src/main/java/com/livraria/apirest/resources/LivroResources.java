package com.livraria.apirest.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livraria.apirest.models.Livro;
import com.livraria.apirest.repository.LivroRepository;

@RestController
@RequestMapping(value="/api")
public class LivroResources {
	
	@Autowired
	LivroRepository livroRepository;
	
	/* Retorna todos os livros armazenados
	 * http:localhost:8080/livros */
	@GetMapping("/livros")
	public List<Livro> listarLivros() {
		return livroRepository.findAll();
	}
	
	/* Retorna o livro relacionado ao id enviado
	 * http:localhost:8080/livro/1 */
	@GetMapping("/livro/{id}")
	public Optional<Livro> encontrarLivro(@PathVariable(value="id") long id) {
		return livroRepository.findById(id);
	}
	
	/* Adiciona um novo livro
	 * http:localhost:8080/livro */
	@PostMapping("/livro")
	public Livro cadastrarLivro(@RequestBody Livro livro) {
		return livroRepository.save(livro);
	}
	
	/* Deleta um livro 
	 * http:localhost:8080/livro */
	@DeleteMapping("/livro")
	public void deletarLivro(@RequestBody Livro livro) {
		livroRepository.delete(livro);
	}
	
	/* Deleta um livro pelo id
	 * http:localhost:8080/livro/1 */
	@DeleteMapping("/livro/{id}")
	public void deletarLivro(@PathVariable(value="id") long id) {
		livroRepository.deleteById(id);
	}
	
	/* Atualiza um livro 
	 * http:localhost:8080/livro */
	@PutMapping("/livro")
	public Livro atualizarLivro(@RequestBody Livro livro) {
		return livroRepository.save(livro);
	}

}
