package br.com.targettrust.spring.aula.service.unidade;

import br.com.targettrust.spring.aula.model.unidade.Unidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UnidadeService {

    Unidade create(Unidade unidade);

    Page<Unidade> findAll(Pageable pageable);
}
