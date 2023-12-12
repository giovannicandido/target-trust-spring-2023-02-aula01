package br.com.targettrust.spring.aula.infraestructure.repository.cliente;

import br.com.targettrust.spring.aula.model.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findClienteByNome(String nome);

    Cliente findClienteByNomeLikeIgnoreCase(String nome);
}
