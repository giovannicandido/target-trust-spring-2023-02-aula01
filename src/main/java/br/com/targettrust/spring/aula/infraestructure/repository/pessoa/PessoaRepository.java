package br.com.targettrust.spring.aula.infraestructure.repository.pessoa;

import br.com.targettrust.spring.aula.model.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    List<Pessoa> findPessoaByNome(String nome);

    Pessoa findPessoaByNomeLikeIgnoreCase(String nome);
}
