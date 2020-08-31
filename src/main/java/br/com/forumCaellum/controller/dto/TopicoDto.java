package br.com.forumCaellum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.forumCaellum.model.Curso;
import br.com.forumCaellum.model.Topico;

public class TopicoDto {
	
	private Long id; 
	private String titulo; 
	private String mensagem; 
	private LocalDateTime dataCriacao;
	private Curso curso; 
	
	public TopicoDto(Topico topico){
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.curso = topico.getCurso(); 
	}
	
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public LocalDateTime getDataCricao() {
		return dataCriacao;
	}
	
	public Curso getCurso() {
		return curso;
	}

	public static List<TopicoDto> convert(List<Topico> topicos) {
		return topicos.stream().map(TopicoDto::new).collect(Collectors.toList()); 
	} 

}
