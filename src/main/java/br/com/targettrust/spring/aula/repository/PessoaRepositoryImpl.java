package br.com.targettrust.spring.aula.repository;

import br.com.targettrust.spring.aula.model.Pessoa;
import br.com.targettrust.spring.aula.model.error.NotFoundException;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementação padrão de uma lista em memória
 */
@Repository // Diz ao spring que esse é um bean do tipo Repository (notar que não há diferenciação entre @Repository e @Service)
@ConditionalOnProperty(name = "pessoas.salvar-em-disco", havingValue = "false") // Esse bean so existirá se essa condição for true, verifique o arquivo application.properties
public class PessoaRepositoryImpl implements PessoaRepository {

    private final Logger logger = LoggerFactory.getLogger(PessoaRepositoryImpl.class);
    private List<Pessoa> pessoas;
    @PostConstruct // Executa após o bean ser criado, parace um construtor, mas aqui se garante que o spring já fez o que tinha que fazer injetando outras classes
    public void setup() {
        logger.info("Setup PessoaService");
        this.pessoas = new ArrayList<>(List.of(
                new Pessoa(1, "Maria", LocalDate.of(1990, 3,1)),
                new Pessoa(1, "Marcos", LocalDate.of(1995, 3,1)),
                new Pessoa(2, "Joao", LocalDate.of(1988, 4,1)),
                new Pessoa(3, "Antonio", LocalDate.of(1970, 6,10))
        ));
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Pessoa> pessoaLocalizada = this.pessoas.stream()
                .filter(pessoa -> Objects.equals(pessoa.getId(), id))
                .findFirst();

        if (pessoaLocalizada.isPresent()) {
            Pessoa pessoa = pessoaLocalizada.get();
            this.pessoas.remove(pessoa);
        }else {
            throw new NotFoundException("Pessoa", id.toString());
        }

    }

    @Override
    public List<Pessoa> listAll() {
        return pessoas;
    }

    @Override
    public void save(Pessoa pessoa) {
        this.pessoas.add(pessoa);
    }

    @Override
    public void editPessoa(Pessoa pessoaNova, Integer id) {
        Optional<Pessoa> pessoaLocalizada = this.pessoas.stream()
                .filter(pessoa -> Objects.equals(pessoa.getId(), id))
                .findFirst();

        if (pessoaLocalizada.isPresent()) {
            Pessoa pessoa = pessoaLocalizada.get();
            pessoaNova.setId(pessoa.getId());
            this.pessoas.remove(pessoa);
            this.pessoas.add(pessoaNova);

        } else {
            throw new NotFoundException("Pessoa", id.toString());
        }
    }
}
