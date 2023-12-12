package br.com.targettrust.spring.aula.service.cliente;

import br.com.targettrust.spring.aula.model.cliente.Cliente;
import br.com.targettrust.spring.aula.model.cliente.ClienteSearchParams;
import br.com.targettrust.spring.aula.model.pagamento.IniciarPagamento;
import br.com.targettrust.spring.aula.service.CommonCrudService;

import java.util.List;

/**
 * Operações do serviço de cliente
 */
public interface ClienteService extends CommonCrudService<Cliente, Integer> {

    void simularError();

    void save(Cliente cliente);

    void editCliente(Cliente clienteNova, Integer id);

    List<Cliente> filtrarPeloNome(String name);

    List<Cliente> filtrar(ClienteSearchParams params);

    // todo isso deveria estar em pagamento
    Integer realizarPagamento(Integer idCliente, IniciarPagamento pagamentoRequest);

    Cliente getById(Integer idCliente);
}
