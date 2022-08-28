package br.com.forumCaellum.controller.form;

import br.com.forumCaellum.model.Topico;
import br.com.forumCaellum.repository.TopicoRepository;
import lombok.Data;

@Data
public class UpdateTopicForm {
	
	private String titulo; 
	private String mensagem;

	public Topico atualizar(Long id, TopicoRepository topicoRepository) {
		Topico topico = topicoRepository.getOne(id); 
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		return topico;
	}	

}
