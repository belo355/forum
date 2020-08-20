package br.com.forumCaellum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alura.forumCaellum.model.Curso;
import br.com.alura.forumCaellum.model.Topico;

@Controller
public class TopicosController {
	
	@RequestMapping("/topicos")
	@ResponseBody
	public List<Topico> lista() {
		Topico topico = new Topico("Duvida","Duvida com spring", new Curso("Spring","Programacao")); 
		return Arrays.asList(topico, topico, topico, topico); 
	}
	
}