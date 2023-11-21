package br.com.targettrust.spring.aula.repository;

import br.com.targettrust.spring.aula.model.Endereco;
import br.com.targettrust.spring.aula.model.error.NotFoundException;
import br.com.targettrust.spring.aula.service.EnderecoServiceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EnderecoRepositoryAdapter implements EnderecoServiceRepository {
    private final EnderecoRepository enderecoRepository;

    public EnderecoRepositoryAdapter(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public void deleteById(Long id) {
        enderecoRepository.deleteById(id);
    }

    @Override
    public List<Endereco> listAll() {
        return enderecoRepository.findAll();
    }

    @Override
    public void save(Endereco entity) {
        enderecoRepository.save(entity);
    }

    @Override
    public void edit(Endereco enderecoNovo, Long id) {
        Optional<Endereco> enderecoExistente = enderecoRepository.findById(id);

        if (enderecoExistente.isEmpty()) {
            throw new NotFoundException("Endereco", id.toString());
        }

        enderecoNovo.setId(id);

        enderecoRepository.save(enderecoNovo);
    }

    @Override
    public Endereco findById(Long id) {
        return enderecoRepository.findById(id).orElseThrow(() -> new NotFoundException("Endereco", id.toString()));
    }
}
