package br.com.alura.forum.controller;

import br.com.alura.forum.dto.DetalhesDoTopicoDto;
import br.com.alura.forum.dto.TopicoDto;
import br.com.alura.forum.form.AtualizacaoTopicoForm;
import br.com.alura.forum.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController{
	
	//Injeção de dependências
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;

    @GetMapping
	@Cacheable(value="listaDeTopicos")
    public Page<TopicoDto> lista(@RequestParam(required = false) String nomeCurso,
								 @PageableDefault(sort="id", direction= Sort.Direction.DESC, page=0, size=10)
								 Pageable paginacao) {

    	if(nomeCurso == null) {
        	Page<Topico> topicos = topicoRepository.findAll(paginacao);
            return TopicoDto.converter(topicos);
    	} else {
    		Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
            return TopicoDto.converter(topicos);
    	}
    }
    
    //Recebe os dados que estão vindo na requisição
    @PostMapping
	@Transactional //Update no banco de dados
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form,
											   UriComponentsBuilder uriBuilder) {
    	Topico topico = form.converter(cursoRepository);
    	topicoRepository.save(topico);

		//URI do recurso (topico) que acabou de ser criado
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

		//Quando eu crio um recurso, preciso retornar a uri e o corpo do que foi criado
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

	//Devolver os detalhes de um tópico específico
	//O nome da variável no parâmetro deve ser igual ao mapeado no GetMapping
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesDoTopicoDto> detalharTopico(@PathVariable Long id) {
		Optional<Topico> topico = topicoRepository.findById(id);
		if(topico.isPresent()) {
			return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
		}

		return ResponseEntity.notFound().build(); //Build é pra montar o objeto ResponseEntity
	}

	@PutMapping("/{id}")
	@Transactional //Update no banco de dados
	public ResponseEntity<TopicoDto> atualizarTopico(@PathVariable Long id,
													 @RequestBody @Valid AtualizacaoTopicoForm form) {
		Optional<Topico> topico = topicoRepository.findById(id);
		if(topico.isPresent()) {
			Topico topicoAtualizado = form.atualizarTopico(id, topicoRepository);
			return ResponseEntity.ok(new TopicoDto(topicoAtualizado));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional //Update no banco de dados
	public ResponseEntity<?> removerTopico(@PathVariable Long id) {
		Optional<Topico> topico = topicoRepository.findById(id);
		if(topico.isPresent()) {
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
