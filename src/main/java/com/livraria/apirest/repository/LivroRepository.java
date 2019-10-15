package com.livraria.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livraria.apirest.models.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
