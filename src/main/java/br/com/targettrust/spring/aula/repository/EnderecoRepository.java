package br.com.targettrust.spring.aula.repository;

import br.com.targettrust.spring.aula.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Toda interface que extende JpaRepository virará um repository do spring data
 * De modo que não precisa de implementação e fornecerá acesso aos dados daquela entidade (tabela)
 * É sempre feito um repository por entidade
 */
public interface EnderecoRepository extends JpaRepository<Endereco, Long> { // tipo entidade, tipo id entidade
}
