package br.com.targettrust.spring.aula.service;

import br.com.targettrust.spring.aula.model.Endereco;
import br.com.targettrust.spring.aula.model.Pessoa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoServiceRepository enderecoServiceRepository;
    private final PessoaServiceRepository pessoaServiceRepository;

    public EnderecoServiceImpl(EnderecoServiceRepository enderecoServiceRepository, PessoaServiceRepository pessoaServiceRepository) {
        this.enderecoServiceRepository = enderecoServiceRepository;
        this.pessoaServiceRepository = pessoaServiceRepository;
    }

    @Override
    public List<Endereco> listAll() {
        return enderecoServiceRepository.listAll();
    }

    @Transactional
    @Override
    public void save(Endereco endereco, Integer pessoaId) {
        Pessoa pessoaById = pessoaServiceRepository.findById(pessoaId);
        endereco.setPessoa(pessoaById);
        enderecoServiceRepository.save(endereco);
    }
}
