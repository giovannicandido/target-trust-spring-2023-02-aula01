package br.com.targettrust.spring.aula.service.pessoa;

import br.com.targettrust.spring.aula.infraestructure.repository.pessoa.EnderecoRepository;
import br.com.targettrust.spring.aula.infraestructure.repository.pessoa.PessoaRepository;
import br.com.targettrust.spring.aula.model.error.NotFoundException;
import br.com.targettrust.spring.aula.model.pessoa.Endereco;
import br.com.targettrust.spring.aula.model.pessoa.Pessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository enderecoServiceRepository;
    private final PessoaRepository pessoaServiceRepository;

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
     * @param pessoaId
     */
    @Transactional
    @Override
    public void save(Endereco endereco, Integer pessoaId) {
        // A pessoa não deve ser criada (new Pessoa()) nem editada, mas precisamos da pessoa existente para fazer a relação
        Pessoa pessoaById = pessoaServiceRepository.findById(pessoaId)
            .orElseThrow(() -> new NotFoundException("Pessoa", pessoaId.toString()));

        endereco.setPessoa(pessoaById); // O JPA entende que existe a relação e quando salvarmos endereço ele vai fazer o insert correto passando o id de pessoa na JoinColum de pessoa_id
        enderecoServiceRepository.save(endereco);
    }

    @Override
    public List<Endereco> searchByRua(String rua) {
        return enderecoServiceRepository.findEnderecoByLogradouro(rua);
    }

    @Override
    public List<Endereco> searchByNomePessoa(String nomePessoa) {
        return enderecoServiceRepository.findEnderecoByPessoaNome(nomePessoa);
    }
}
