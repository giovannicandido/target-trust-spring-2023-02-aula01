package br.com.targettrust.spring.aula.service.unidade;

import br.com.targettrust.spring.aula.infraestructure.repository.pessoa.EnderecoRepository;
import br.com.targettrust.spring.aula.infraestructure.repository.unidade.UnidadeRepository;
import br.com.targettrust.spring.aula.model.unidade.Unidade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UnidadeServiceImpl implements UnidadeService {

    private final UnidadeRepository repository;
    private final EnderecoRepository enderecoRepository;

    @Transactional
    @Override
    public Unidade create(Unidade unidade) {
        enderecoRepository.save(unidade.getEndereco());
        return repository.save(unidade);
    }

    @Override
    public Page<Unidade> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
