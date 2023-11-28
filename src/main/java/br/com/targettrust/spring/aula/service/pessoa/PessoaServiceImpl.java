package br.com.targettrust.spring.aula.service.pessoa;

import br.com.targettrust.spring.aula.infraestructure.repository.pagamento.PagamentoRepository;
import br.com.targettrust.spring.aula.model.error.EnderecoNaoLocalizadoException;
import br.com.targettrust.spring.aula.model.pagamento.IniciarPagamento;
import br.com.targettrust.spring.aula.model.pagamento.Pagamento;
import br.com.targettrust.spring.aula.model.pessoa.Endereco;
import br.com.targettrust.spring.aula.model.pessoa.Pessoa;
import br.com.targettrust.spring.aula.model.pessoa.PessoaSearchParams;
import br.com.targettrust.spring.aula.service.external.CepExternalService;
import br.com.targettrust.spring.aula.service.external.EmailExternalService;
import br.com.targettrust.spring.aula.service.pagamento.CartaoService;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

/**
 * Implementação do serviço de pessoas
 */
@Service
@RequiredArgsConstructor
// Cria um bean do tipo Service, não há diferença entre outros beans (com exceção de Controladores e Configurações)
public class PessoaServiceImpl implements PessoaService {
    private final Logger logger = LoggerFactory.getLogger(PessoaServiceImpl.class);

    private final PessoaServiceRepository pessoaRepository;

    private final CepExternalService cepExternalService;

    private final EnderecoServiceRepository enderecoServiceRepository;

    private final CartaoService cartaoService;

    private final PagamentoRepository pagamentoRepository;

    private final EmailExternalService emailExternalService;


    @PreDestroy // So executa antes de remover o bean PessoaServiceImpl da memória ram
    public void destroy() {
        logger.info("PreDestroy PessoaService");
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        pessoaRepository.deleteById(id);
    }

    @Override
    public List<Pessoa> listAll() {
        return pessoaRepository.listAll();
    }

    @Override
    public void simularError() {
        throw new RuntimeException("error generico");

    }

    @Transactional
    @Override
    public void save(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

    @Transactional
    @Override
    public void editPessoa(Pessoa pessoaNova, Integer id) {
        pessoaRepository.edit(pessoaNova, id);
    }

    @Override
    public List<Pessoa> filtrarPeloNome(String name) {
        return pessoaRepository.listAll()
                .stream()
                .filter(pessoa -> pessoa.getNome().startsWith(name))
                .toList();
    }

    @Override
    public List<Pessoa> filtrar(PessoaSearchParams params) {

        // A logica de filtro abaixo seria melhor executada em um banco de dados
        // Podemos refatorar quando tivermos introduzido banco.
        // Reparar que o numero de combinações validas de filtro é gigantesca e cresce exponencialmente a medida que se adiciona mais filtros.
        // Existem formas de se montar a query no banco de dados de forma mais dinamica e resolver parcialmente o problem de combinações possíveis.

        Stream<Pessoa> pessoaStream = pessoaRepository.listAll()
                .stream();

        if (params.getNome() != null && params.getIdade() != null) {
            pessoaStream = pessoaStream.filter(pessoa -> {
                return pessoa.getNome().startsWith(params.getNome()) || idadeMenorQue(params.getIdade(), pessoa.getDataNascimento());
            });
            if (!params.getIds().isEmpty()) {
                pessoaStream = pessoaStream.filter(pessoa -> params.getIds().contains(pessoa.getId()));
            }
        } else {
            if (params.getNome() != null) {
                pessoaStream = pessoaStream.filter(pessoa -> pessoa.getNome().startsWith(params.getNome()));
            }

            if (!params.getIds().isEmpty()) {
                pessoaStream = pessoaStream.filter(pessoa -> params.getIds().contains(pessoa.getId()));
            }

            if (params.getIdade() != null) {
                pessoaStream = pessoaStream.filter(pessoa -> idadeMenorQue(params.getIdade(), pessoa.getDataNascimento()));
            }
        }

        return pessoaStream.toList();

    }

    @Transactional
    @Override
    public Integer realizarPagamento(Integer idPessoa, IniciarPagamento pagamentoRequest) {

        Pessoa pessoa = pessoaRepository.findById(idPessoa);

        // pesquisa endereco pelo cep
        // se enderco não localizado retorna not found

        Endereco endereco = cepExternalService.searchEnderecoByCep(pagamentoRequest.getCep())
                .orElseThrow(() -> new EnderecoNaoLocalizadoException(pagamentoRequest.getCep()));

        endereco.setNumero(pagamentoRequest.getNumero());
        endereco.setPessoa(pessoa);

        // salvar o endereco no banco
        enderecoServiceRepository.save(endereco);

        // salvar o cartao de credito da pessoa no banco
        cartaoService.salvarEvalidarCartao(pagamentoRequest.getNumeroCartao(), pessoa);

        // simular o pagamento (salvar o pagamento)

        Pagamento pagamento = Pagamento.builder()
                .dataPagamento(LocalDateTime.now())
                .pagador(pessoa)
                .build();

        pagamento = pagamentoRepository.save(pagamento);

        // simular envio de email

        emailExternalService.enviarEmail(pagamentoRequest.getEmail(), "Pagamento realizado com sucesso", "Novo pagamento criado");

        // retornar o id do pagamento

        return Math.toIntExact(pagamento.getId());
    }

    private boolean idadeMenorQue(Integer idade, LocalDate dataNascimento) {
        LocalDate now = LocalDate.now();

        long years = ChronoUnit.YEARS.between(dataNascimento, now);

        return years < idade;

    }
}
