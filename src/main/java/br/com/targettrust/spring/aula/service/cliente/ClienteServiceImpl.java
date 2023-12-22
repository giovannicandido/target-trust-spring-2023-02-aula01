package br.com.targettrust.spring.aula.service.cliente;

import br.com.targettrust.spring.aula.infraestructure.repository.cliente.ClienteRepository;
import br.com.targettrust.spring.aula.infraestructure.repository.cliente.EnderecoRepository;
import br.com.targettrust.spring.aula.infraestructure.repository.pagamento.PagamentoRepository;
import br.com.targettrust.spring.aula.model.cliente.Cliente;
import br.com.targettrust.spring.aula.model.cliente.ClienteSearchParams;
import br.com.targettrust.spring.aula.model.cliente.Endereco;
import br.com.targettrust.spring.aula.model.error.EnderecoNaoLocalizadoException;
import br.com.targettrust.spring.aula.model.error.NotFoundException;
import br.com.targettrust.spring.aula.model.pagamento.IniciarPagamento;
import br.com.targettrust.spring.aula.model.pagamento.Pagamento;
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
 * Implementação do serviço de clientes
 */
@Service
@RequiredArgsConstructor
// Cria um bean do tipo Service, não há diferença entre outros beans (com exceção de Controladores e Configurações)
public class ClienteServiceImpl implements ClienteService {
    private final Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);

    private final ClienteRepository clienteRepository;

    private final CepExternalService cepExternalService;

    private final EnderecoRepository enderecoServiceRepository;

    private final CartaoService cartaoService;

    private final PagamentoRepository pagamentoRepository;

    private final EmailExternalService emailExternalService;


    @PreDestroy // So executa antes de remover o bean ClienteServiceImpl da memória ram
    public void destroy() {
        logger.info("PreDestroy ClienteService");
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public List<Cliente> listAll() {
        return clienteRepository.findAll();
    }

    @Override
    public void simularError() {
        throw new RuntimeException("error generico");

    }

    @Transactional
    @Override
    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Transactional
    @Override
    public void editCliente(Cliente clienteNova, Integer id) {

        Cliente clienteExistente = clienteRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Cliente", id.toString()));

        clienteNova.setId(id);

        clienteRepository.save(clienteNova);
    }

    @Override
    public List<Cliente> filtrarPeloNome(String name) {
        return clienteRepository.findAll()
                .stream()
            .filter(cliente -> cliente.getNome().startsWith(name))
                .toList();
    }

    @Override
    public List<Cliente> filtrar(ClienteSearchParams params) {

        // A logica de filtro abaixo seria melhor executada em um banco de dados
        // Podemos refatorar quando tivermos introduzido banco.
        // Reparar que o numero de combinações validas de filtro é gigantesca e cresce exponencialmente a medida que se adiciona mais filtros.
        // Existem formas de se montar a query no banco de dados de forma mais dinamica e resolver parcialmente o problem de combinações possíveis.

        Stream<Cliente> clienteStream = clienteRepository.findAll()
                .stream();

        if (params.getNome() != null && params.getIdade() != null) {
            clienteStream = clienteStream.filter(cliente -> {
                return cliente.getNome().startsWith(params.getNome()) || idadeMenorQue(params.getIdade(), cliente.getDataNascimento());
            });
            if (!params.getIds().isEmpty()) {
                clienteStream = clienteStream.filter(cliente -> params.getIds().contains(cliente.getId()));
            }
        } else {
            if (params.getNome() != null) {
                clienteStream = clienteStream.filter(cliente -> cliente.getNome().startsWith(params.getNome()));
            }

            if (!params.getIds().isEmpty()) {
                clienteStream = clienteStream.filter(cliente -> params.getIds().contains(cliente.getId()));
            }

            if (params.getIdade() != null) {
                clienteStream = clienteStream.filter(cliente -> idadeMenorQue(params.getIdade(), cliente.getDataNascimento()));
            }
        }

        return clienteStream.toList();

    }

    @Transactional
    @Override
    public Integer realizarPagamento(Integer idCliente, IniciarPagamento pagamentoRequest) {

        Cliente cliente = clienteRepository.findById(idCliente)
            .orElseThrow(() -> new NotFoundException("Pesssoa", idCliente.toString()));

        // pesquisa endereco pelo cep
        // se enderco não localizado retorna not found

        Endereco endereco = cepExternalService.searchEnderecoByCep(pagamentoRequest.getCep())
                .orElseThrow(() -> new EnderecoNaoLocalizadoException(pagamentoRequest.getCep()));

        endereco.setNumero(pagamentoRequest.getNumero());
        List<Endereco> enderecos = cliente.getEnderecos();

        if (enderecos != null) {
            enderecos.add(endereco);
        }

        // salvar o endereco no banco
        enderecoServiceRepository.save(endereco);
        //
        clienteRepository.save(cliente);

        // salvar o cartao de credito da cliente no banco
        cartaoService.salvarEvalidarCartao(pagamentoRequest.getNumeroCartao(), cliente);

        // simular o pagamento (salvar o pagamento)

        Pagamento pagamento = Pagamento.builder()
                .dataPagamento(LocalDateTime.now())
            .pagador(cliente)
                .build();

        pagamento = pagamentoRepository.save(pagamento);

        // simular envio de email

        emailExternalService.enviarEmail(pagamentoRequest.getEmail(), "Pagamento realizado com sucesso", "Novo pagamento criado");

        // retornar o id do pagamento

        return Math.toIntExact(pagamento.getId());
    }

    @Override
    public Cliente getById(Integer idCliente) {
        return clienteRepository.findById(idCliente)
            .orElseThrow(() -> new NotFoundException("Cliente", idCliente.toString()));
    }

    private boolean idadeMenorQue(Integer idade, LocalDate dataNascimento) {
        LocalDate now = LocalDate.now();

        long years = ChronoUnit.YEARS.between(dataNascimento, now);

        return years < idade;

    }
}
