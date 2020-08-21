package br.com.forumCaellum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forumCaellum.model.Curso;
import br.com.alura.forumCaellum.model.Topico;
import br.com.forumCaellum.controller.dto.TopicoDto;

@RestController
public class TopicosController {
	
	@RequestMapping("/topicos")
	public List<TopicoDto> lista() {
		Topico topico = new Topico("Duvida","Duvida com spring", new Curso("Spring","Programacao")); 
		return TopicoDto.convert(Arrays.asList(topico, topico, topico, topico)); 
	}	
}
