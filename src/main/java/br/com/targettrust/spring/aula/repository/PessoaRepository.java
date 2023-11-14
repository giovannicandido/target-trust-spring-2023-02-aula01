package br.com.targettrust.spring.aula.repository;

import br.com.targettrust.spring.aula.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    List<Pessoa> findPessoaByNome(String nome);
}
