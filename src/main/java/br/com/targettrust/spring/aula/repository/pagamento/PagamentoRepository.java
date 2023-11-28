package br.com.targettrust.spring.aula.repository.pagamento;

import br.com.targettrust.spring.aula.model.pagamento.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
