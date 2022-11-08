package br.com.alura.forum.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.modelo.Topico;
import org.springframework.data.domain.Page;

public class TopicoDto {
	
	/**
	 * Aqui nessa classe, eu defino somente os campos que eu quero que a minha API devolva.
	 * NÃO é uma boa prática devolver as classes de domínio (models) no Controller
	 * Por isso é bom implementar os DTO's para definir o que queremos devolver.
	**/
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	
	//Construtor
	public TopicoDto(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
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

	//Transformar Topico (model) para TopicoDto
	public static Page<TopicoDto> converter(Page<Topico> topicos) {
		return topicos.map(TopicoDto::new);
	}
	
}
