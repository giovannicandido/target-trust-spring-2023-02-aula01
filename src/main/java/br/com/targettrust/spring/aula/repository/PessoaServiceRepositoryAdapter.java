package br.com.targettrust.spring.aula.repository;

import br.com.targettrust.spring.aula.model.Pessoa;
import br.com.targettrust.spring.aula.model.error.NotFoundException;
import br.com.targettrust.spring.aula.service.PessoaServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Olhar os comentários de {@link EnderecoServiceRepositoryAdapter} o mesmo se aplica aqui
 */
@Repository
@RequiredArgsConstructor // substitui o construtor para itens final
public class PessoaServiceRepositoryAdapter implements PessoaServiceRepository {

    private final PessoaRepository repository;

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Pessoa> listAll() {
        return repository.findAll();
    }

    @Override
    public void save(Pessoa pessoa) {
        repository.save(pessoa);
    }

    @Override
    public void edit(Pessoa pessoaNova, Integer id) {
        Optional<Pessoa> pessoaExistente = repository.findById(id);

        if (pessoaExistente.isEmpty()) {
            throw new NotFoundException("Pessoa", id.toString());
        }

        pessoaNova.setId(id);

        repository.save(pessoaNova);
    }

    @Override
    public Pessoa findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Pessoa", id.toString()));
    }
}
