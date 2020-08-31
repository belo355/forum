package br.com.forumCaellum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.forumCaellum.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);

}
