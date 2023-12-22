package br.com.targettrust.spring.aula.infraestructure.repository.pagamento;

import br.com.targettrust.spring.aula.model.error.NotFoundException;
import br.com.targettrust.spring.aula.model.pagamento.CartaoCredito;
import br.com.targettrust.spring.aula.service.pagamento.CartaoServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class CartaoServiceRepositoryAdapter implements CartaoServiceRepository {

    private final CartaoRepository cartaoRepository;

    @Override
    public void deleteById(Long id) {
        cartaoRepository.deleteById(id);
    }

    @Override
    public List<CartaoCredito> listAll() {
        return cartaoRepository.findAll();
    }

    @Override
    public void save(CartaoCredito cartaoCredito) {
        cartaoRepository.save(cartaoCredito);
    }

    @Override
    public void edit(CartaoCredito entity, Long id) {
        cartaoRepository.findById(id)
                .map(cartaoExistente -> {
                    entity.setId(cartaoExistente.getId());
                    return entity;
                })
                .map(cartaoRepository::save)
                .orElseThrow(() -> new NotFoundException("CartaoCredito", id.toString()));
    }

    @Override
    public CartaoCredito findById(Long id) {
        return cartaoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("CartaoCredito", id.toString()));
    }

    @Override
    public CartaoCredito findFirstByNumero(String numero) {
        return cartaoRepository.findFirstByNumero(numero);
    }

    @Override
    public boolean existOutraPessoaComCartaoIgual(Integer idPessoa, String numeroCartao) {
        return cartaoRepository.existsByNumeroAndPessoaIdNotIn(numeroCartao, idPessoa);
    }
}
