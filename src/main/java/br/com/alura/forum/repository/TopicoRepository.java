package br.com.alura.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.modelo.Topico;

//Por ser uma interface, não é necessário anotar nada (@..)
//O JpaRepository já tem os métodos CRUD implementados
public interface TopicoRepository extends JpaRepository<Topico, Long>{

}
