package br.com.forumCaellum.controller.form;

import br.com.forumCaellum.model.Curso;
import br.com.forumCaellum.model.Topico;
import br.com.forumCaellum.repository.CursoRepository;
import lombok.Data;

@Data
public class TopicoForm {
	
	private String titulo; 
	private String mensagem; 
	private String nomeCurso;
	
	public Topico convert(CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(nomeCurso); 
		return new Topico(titulo, mensagem, curso); 
	}
}
