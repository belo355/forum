package br.com.forumCaellum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.forumCaellum.model.Topico;

public class TopicoDto {
	
	private Long id; 
	private String titulo; 
	private String mensagem; 
	private LocalDateTime dataCriacao;
	private String curso;

	public TopicoDto(Topico topico){
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.curso = topico.getCurso().getNome(); 

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
	
	public String getCurso() {
		return curso;
	}

	public static Page<TopicoDto> convert(Page<Topico> topicos) {
		return topicos.map(TopicoDto::new); 
	} 

}
