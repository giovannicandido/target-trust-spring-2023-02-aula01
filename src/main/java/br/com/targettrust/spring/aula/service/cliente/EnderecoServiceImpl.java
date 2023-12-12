package br.com.targettrust.spring.aula.service.cliente;

import br.com.targettrust.spring.aula.infraestructure.repository.cliente.ClienteRepository;
import br.com.targettrust.spring.aula.infraestructure.repository.cliente.EnderecoRepository;
import br.com.targettrust.spring.aula.model.cliente.Cliente;
import br.com.targettrust.spring.aula.model.cliente.Endereco;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository enderecoServiceRepository;
    private final ClienteRepository clienteServiceRepository;
    private final ClienteService clienteService;

    @Override
    public List<Endereco> listAll() {
        return enderecoServiceRepository.findAll();
    }

    /**
     * Reparar que não abrimos uma transação, mas o repository do spring vai fazer uma
     * É bom ser explicito, nesse caso eu colocaria
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        enderecoServiceRepository.deleteById(id);
    }

    /**
     * É comum que a transação se inicie nos serviços, ou nos casos de uso (pasta application do clean code)
     * Nota: editar, deletar, criar geralmente precisam de transações, se não colocou ou o banco ou o spring estão criando uma
     * Listar items geralmente não precisa de transações (leitura não precisa)
     * No caso do spring criar ele pode criar no repository dele.
     * É sempre bom ser explicito
     * @param endereco
     */
    @Transactional
    @Override
    public void save(Endereco endereco, Integer idCliente) {
//        // A cliente não deve ser criada (new Cliente()) nem editada, mas precisamos da cliente existente para fazer a relação
        Cliente clienteById = clienteService.getById(idCliente);

        endereco = enderecoServiceRepository.save(endereco);

        clienteById.getEnderecos().add(endereco);

        clienteServiceRepository.save(clienteById);

    }

    @Override
    public List<Endereco> searchByRua(String rua) {
        return enderecoServiceRepository.findEnderecoByLogradouro(rua);
    }

    @Override
    public List<Endereco> searchByNomeCliente(String nomeCliente) {
        return enderecoServiceRepository.findEnderecoByClienteNome(nomeCliente);
    }
}
