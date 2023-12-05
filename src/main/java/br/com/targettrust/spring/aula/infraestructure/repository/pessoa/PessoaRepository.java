package br.com.targettrust.spring.aula.infraestructure.repository.pessoa;

import br.com.targettrust.spring.aula.model.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findClienteByNome(String nome);

    Cliente findClienteByNomeLikeIgnoreCase(String nome);
}
