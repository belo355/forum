package br.com.forumCaellum.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.forumCaellum.model.Curso;
import br.com.forumCaellum.model.StatusTopico;
import br.com.forumCaellum.model.Topico;

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
		this.respostas = new ArrayList<>(); //criando a listagem de resposta 
		this.respostas.addAll(topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList())); // atribuindo e convert pra uma list dto
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

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public Curso getCurso() {
		return curso;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public List<RespostaDto> getRespostas() {
		return respostas;
	}


}
