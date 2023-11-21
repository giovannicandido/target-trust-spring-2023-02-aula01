package br.com.targettrust.spring.aula.repository;

import br.com.targettrust.spring.aula.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
