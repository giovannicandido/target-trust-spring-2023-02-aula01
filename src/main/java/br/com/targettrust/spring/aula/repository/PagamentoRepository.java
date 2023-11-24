package br.com.targettrust.spring.aula.repository;

import br.com.targettrust.spring.aula.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
