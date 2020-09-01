package br.com.forumCaellum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.forumCaellum.controller.dto.DetalheTopicoDto;
import br.com.forumCaellum.controller.dto.TopicoDto;
import br.com.forumCaellum.controller.form.AtualizaTopicoForm;
import br.com.forumCaellum.controller.form.TopicoForm;
import br.com.forumCaellum.model.Topico;
import br.com.forumCaellum.repository.CursoRepository;
import br.com.forumCaellum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository; 

	@GetMapping
	public List<TopicoDto> lista(String nomeCurso) {
		if (nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDto.convert(topicos);
		}else {
			List<Topico> topicosByCurso = topicoRepository.findByCursoNome(nomeCurso);
			return TopicoDto.convert(topicosByCurso);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TopicoDto> register(@RequestBody TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.convert(cursoRepository); 
		topicoRepository.save(topico); 
		
		URI uri = uriBuilder.path("/topicos/{}").buildAndExpand(topico.getId()).toUri(); 
		return ResponseEntity.created(uri).body(new TopicoDto(topico)); 		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalheTopicoDto> detalhar(@PathVariable Long id) {
		Optional<Topico> topico = topicoRepository.findById(id);
		if (topico.isPresent()) {
			return ResponseEntity.ok(new DetalheTopicoDto(topico.get()));
		}
		return ResponseEntity.notFound().build(); 
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody AtualizaTopicoForm form){
		Optional<Topico> optional = topicoRepository.findById(id);
		if (optional.isPresent()) {
			Topico topico = form.atualizar(id, topicoRepository);
			return ResponseEntity.ok(new TopicoDto(topico));
		}
		 
		return ResponseEntity.notFound().build(); 	
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id){ 
		Optional<Topico> optional = topicoRepository.findById(id);
		if (optional.isPresent()) {
			topicoRepository.deleteById(id); 
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build(); 
		
	}
}
