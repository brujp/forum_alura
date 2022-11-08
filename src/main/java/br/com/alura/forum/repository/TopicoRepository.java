package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.forum.modelo.Topico;

//Por ser uma interface, não é necessário anotar nada (@..)
//O JpaRepository já tem os métodos CRUD implementados
public interface TopicoRepository extends JpaRepository<Topico, Long>{

	//Curso - Entidade de relacionamento + Nome - Atributo dentro da entidade relacionamento
	Page<Topico> findByCursoNome(String nomeCurso, Pageable paginacao);
	
	//Faz a mesma coisa do método de cima que o Spring gera automático por conta
	//da nomenclatura "find.."
	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
	List<Topico> carregarPeloNomeDoCurso(@Param("nomeCurso") String nomeCurso);
}
