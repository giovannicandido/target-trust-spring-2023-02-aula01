package br.com.targettrust.spring.aula.service.pessoa;

import br.com.targettrust.spring.aula.model.cliente.Cliente;
import br.com.targettrust.spring.aula.model.cliente.PessoaSearchParams;
import br.com.targettrust.spring.aula.model.pagamento.IniciarPagamento;
import br.com.targettrust.spring.aula.service.CommonCrudService;

import java.util.List;

/**
 * Operações do serviço de pessoa
 */
public interface PessoaService extends CommonCrudService<Cliente, Integer> {

    void simularError();

    void save(Cliente pessoa);

    void editPessoa(Cliente pessoaNova, Integer id);

    List<Cliente> filtrarPeloNome(String name);

    List<Cliente> filtrar(PessoaSearchParams params);

    // todo isso deveria estar em pagamento
    Integer realizarPagamento(Integer idPessoa, IniciarPagamento pagamentoRequest);
}
