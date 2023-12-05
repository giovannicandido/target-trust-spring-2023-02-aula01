package br.com.targettrust.spring.aula.infraestructure.repository.unidade;

import br.com.targettrust.spring.aula.model.unidade.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeRepository extends JpaRepository<Unidade, Integer> {
}
