package br.com.targettrust.spring.aula.repository;

import br.com.targettrust.spring.aula.model.Pessoa;
import br.com.targettrust.spring.aula.model.error.NotFoundException;
import jakarta.persistence.EntityManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@ConditionalOnProperty(name = "pessoas.salvar-em-banco", havingValue = "true")
public class PessoaBancoRepositoryImpl implements PessoaRepository {

    private final EntityManager entityManager;

    public PessoaBancoRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        Pessoa pessoa = entityManager.find(Pessoa.class, id);
        if (pessoa == null) {
            throw new NotFoundException("Pessoa", id.toString());
        }


        entityManager.remove(pessoa);
    }

    @Override
    public List<Pessoa> listAll() {
        return entityManager.createQuery("select p from Pessoa p", Pessoa.class)
                .getResultList();
    }

    @Transactional
    @Override
    public void save(Pessoa pessoa) {
        entityManager.persist(pessoa);
    }

    @Transactional
    @Override
    public void editPessoa(Pessoa pessoaNova, Integer id) {
        Pessoa pessoa = entityManager.find(Pessoa.class, id);

        if(pessoa == null) {
            throw new NotFoundException("Pessoa", id.toString());
        }

        pessoa.setNome(pessoaNova.getNome());
        pessoa.setDataNascimento(pessoaNova.getDataNascimento());

        entityManager.merge(pessoa);
    }
}
