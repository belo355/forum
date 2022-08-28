package br.com.forumCaellum.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.forumCaellum.model.Curso;
import br.com.forumCaellum.model.StatusTopico;
import br.com.forumCaellum.model.Topico;
import lombok.Data;

@Data
public class DetalheTopicoDto {
	
	private Long id; 
	private String titulo; 
	private String mensagem; 
	private LocalDateTime dataCriacao;
	private Curso curso;
	private String nomeAutor; 
	private StatusTopico status; 
	private List<RespostaDto> respostas;  
	
	public DetalheTopicoDto(Topico topico){
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.curso = topico.getCurso();
		this.nomeAutor = topico.getAutor().getNome();
		this.status = topico.getStatus(); 
		this.respostas = new ArrayList<>();
		this.respostas.addAll(topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList()));
	}

	public DetalheTopicoDto(List<Topico> topics){
		topics.forEach(topic -> {
			this.id = topic.getId();
			this.titulo = topic.getTitulo();
			this.mensagem = topic.getMensagem();
			this.dataCriacao = topic.getDataCriacao();
			this.curso = topic.getCurso();
			this.nomeAutor = topic.getAutor().getNome();
			this.status = topic.getStatus();
			this.respostas = new ArrayList<>();
			this.respostas.addAll(topic.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList()));
		});
	}

}
