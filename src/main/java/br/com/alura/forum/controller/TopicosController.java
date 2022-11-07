package br.com.alura.forum.controller;

import br.com.alura.forum.dto.DetalhesDoTopicoDto;
import br.com.alura.forum.dto.TopicoDto;
import br.com.alura.forum.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController{
	
	//Injeção de dependências
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso) {
    	if(nomeCurso == null) {
        	List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
    	} else {
    		List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
            return TopicoDto.converter(topicos);
    	}
    }
    
    //Recebe os dados que estão vindo na requisição
    @PostMapping
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
	public DetalhesDoTopicoDto detalharTopico(@PathVariable Long id) {
		Topico topico = topicoRepository.getReferenceById(id);
		return new DetalhesDoTopicoDto(topico);
	}

}
