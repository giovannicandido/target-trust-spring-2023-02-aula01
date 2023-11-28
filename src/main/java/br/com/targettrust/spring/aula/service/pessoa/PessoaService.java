package br.com.targettrust.spring.aula.service.pessoa;

import br.com.targettrust.spring.aula.model.pagamento.IniciarPagamento;
import br.com.targettrust.spring.aula.model.pessoa.Pessoa;
import br.com.targettrust.spring.aula.model.pessoa.PessoaSearchParams;
import br.com.targettrust.spring.aula.service.CommonCrudService;

import java.util.List;

/**
 * Operações do serviço de pessoa
 */
public interface PessoaService extends CommonCrudService<Pessoa, Integer> {

    void simularError();

    void save(Pessoa pessoa);

    void editPessoa(Pessoa pessoaNova, Integer id);

    List<Pessoa> filtrarPeloNome(String name);

    List<Pessoa> filtrar(PessoaSearchParams params);

    // todo isso deveria estar em pagamento
    Integer realizarPagamento(Integer idPessoa, IniciarPagamento pagamentoRequest);
}
