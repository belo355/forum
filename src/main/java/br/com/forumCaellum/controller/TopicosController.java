package br.com.forumCaellum.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import br.com.forumCaellum.controller.form.UpdateTopicForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.forumCaellum.controller.dto.DetalheTopicoDto;
import br.com.forumCaellum.controller.dto.TopicoDto;
import br.com.forumCaellum.controller.form.TopicoForm;
import br.com.forumCaellum.model.Topico;
import br.com.forumCaellum.repository.CursoRepository;
import br.com.forumCaellum.repository.TopicoRepository;

/**
 * Controller for manager operations Topics.
 *
 * @author Edilson Belo
 */
@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

	private static final Logger logger = LoggerFactory.getLogger(TopicosController.class);

    @GetMapping
    @Cacheable(value = "listaTopicos")
    public Page<TopicoDto> findAll(@RequestParam(required = true) String nomeCurso, Pageable paginacao) {
        try {
            Page<Topico> topicos = topicoRepository.findAll(paginacao);
            return TopicoDto.convert(topicos);
        } catch (IllegalArgumentException e ) {
            logger.info(e.getMessage());
        }
        return null;
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaTopicos", allEntries = true)
    public ResponseEntity<TopicoDto> save(@RequestBody TopicoForm form, UriComponentsBuilder uriBuilder) throws IllegalArgumentException {
        Topico topico = form.convert(cursoRepository);
        topicoRepository.save(topico);
        URI uri = uriBuilder.path("/topicos/{}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    @CacheEvict(value = "listaTopicos", allEntries = true)
    public ResponseEntity<DetalheTopicoDto> findDetails(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        return topico.map(value -> ResponseEntity.ok(new DetalheTopicoDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/dates")
    @CacheEvict(value = "listaTopicos", allEntries = true)
    public ResponseEntity<DetalheTopicoDto> findBetweenDates(@PathVariable LocalDateTime dateInital, LocalDateTime dateFinal) {
        try {
            List<Topico> topics = topicoRepository.findAll();
            List<Topico> topicsFinal = topics.stream()
                    .filter(topic -> topic.getDataCriacao().isAfter(dateInital))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new DetalheTopicoDto(topicsFinal));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaTopicos", allEntries = true)
    public ResponseEntity<TopicoDto> update(@PathVariable Long id, @RequestBody UpdateTopicForm form) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            Topico topico = form.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDto(topico));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaTopicos", allEntries = true)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
