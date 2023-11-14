package br.com.targettrust.spring.aula.repository;

import br.com.targettrust.spring.aula.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaSpringRepository extends JpaRepository<Pessoa, Integer> {
}
