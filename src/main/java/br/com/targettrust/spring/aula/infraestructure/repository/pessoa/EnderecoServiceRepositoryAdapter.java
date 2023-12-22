package br.com.targettrust.spring.aula.infraestructure.repository.pessoa;

import br.com.targettrust.spring.aula.model.error.NotFoundException;
import br.com.targettrust.spring.aula.model.pessoa.Endereco;
import br.com.targettrust.spring.aula.service.pessoa.EnderecoServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Toda class Adapter (no caso o adapter de um ServiceRepository) irá implementar a logica da tecnologia
 * Nesse caso é um banco de dados mas poderia ser uma fila de mensagem ou um arquivo, pouco importa
 * Nosso serviço depende de EnderecoServiceRepository uma classe que está lá na camada dele,
 * Aqui fornecemos a implementação. Notar que usamos um repository que é a tecnologia do spring data!
 * Isso pode ser um pouco confuso mas depois que se acostoma faz sentido, existem projetos que não fazem isso!
 * O MVC puro o service vai direto para o repository da tecnologia (spring) sem esse intermediario.
 * Projetos maiores constumam fazer coisas parecidas
 */
@Repository
// tanto faz se é um @Service ou @Repository na prática da na mesma, uso o @Repository só para ficar mais explicito
@RequiredArgsConstructor // substitui o construtor para itens final
public class EnderecoServiceRepositoryAdapter implements EnderecoServiceRepository {

    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;

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

    @Override
    public List<Endereco> searchByRua(String rua) {
        rua = makeLike(rua);
//        return enderecoRepository.findEnderecoByLogradouro(rua);
        return enderecoRepository.findAllByLogradouroIsLikeWithProjection(rua)
                .stream()
                .map(enderecoProjection -> Endereco.builder()
                        .numero(enderecoProjection.getNumero())
                        .logradouro(enderecoProjection.getLogradouro())
                        .build())
                .toList();
    }

    @Override
    public List<Endereco> searchByNomePessoa(String nomePessoa) {
        nomePessoa = makeLike(nomePessoa);
        return enderecoRepository.findEnderecoByPessoaNome(nomePessoa);

//        Pessoa pessoa = pessoaRepository.findPessoaByNomeLikeIgnoreCase(nomePessoa);
//        if (pessoa == null) {
//            throw new NotFoundException("Pessoa", nomePessoa);
//        }

    }

    private static String makeLike(String param) {
        param = "%" + param + "%";
        return param;
    }

}
