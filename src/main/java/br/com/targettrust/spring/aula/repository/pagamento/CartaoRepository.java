package br.com.targettrust.spring.aula.repository.pagamento;

import br.com.targettrust.spring.aula.model.pagamento.CartaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartaoRepository extends JpaRepository<CartaoCredito, Long> {

    CartaoCredito findFirstByNumero(String numero);

    @Query("select count(c) > 0 from Pessoa p join p.cartaoCredito c where c.numero = :numero and p.id != :pessoaId")
    boolean existsByNumeroAndPessoaIdNotIn(String numero, Integer pessoaId);

}
