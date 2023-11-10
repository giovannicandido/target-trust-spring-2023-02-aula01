package br.com.targettrust.spring.aula.repository;

import br.com.targettrust.spring.aula.model.Pessoa;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Exemplo de implementação da mesma interface, mas agora salvando e listando pessoas de um arquivo json no disco rigido
 * Não se atente aos detalhes, mas na forma como pode ser trocada a implementação caso seja necessário
 * Um exemplo de caso de uso real pode ser:
 * * Um aplicativo desktop que salva em disco sem um banco de dados, mas que pode ser configurado para salvar em banco também
 * * Fazer implementações distintas de pagamento dado a escolha do usuário (boleto, cartão de credito) sem alterar as camadas que dependem disso
 * * Mudar a estratégia de algum algoritmo (busca binária vs força bruta, etc..)
 * * Mudar o calculo de algum dado por exemplo impostos, ou descontos (mais complexo porém é um começo pensar em interfaces)
 */
@Repository // Declara um bean do spring a ser injetado mas agora do tipo repository, notar que isso é só uma marcação, o spring não diferencia um @Service de um @Repository
@ConditionalOnProperty(name = "pessoas.salvar-em-disco", havingValue = "true") // Esse bean só existira se essa condição for verdadeira. verifique o arquivo application.properties
public class PessoaFileRepositoryImpl implements PessoaRepository {

    // localização do arquivo, mude para sua maquina local para funcionar
    private String local = "C:\\Users\\giova\\pessoas.json";
    private final ObjectMapper objectMapper;

    public PessoaFileRepositoryImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void deleteById(Integer id) {
        throw new IllegalArgumentException("Metodo não suportado");
    }

    /**
     * Lista todas as pessoas do arquivo json em disco. O arquivo tem que exisitir não programamos para criar o arquivo caso não exista.
     * @return
     */
    @Override
    public List<Pessoa> listAll() {
        try {
            return objectMapper.readValue(new File(local), new TypeReference<List<Pessoa>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Vamos gravar uma lista estática de pessoas em json no disco
     * Notar que essa implementação tem varios problemas, a começar por sempre sobrescrever a pessoa.
     * @param pessoa
     */
    @Override
    public void save(Pessoa pessoa) {
        try {

            List<Pessoa> pessoas = List.of(pessoa);
            String json = objectMapper.writeValueAsString(pessoas);

            try (OutputStreamWriter writer =
                         new OutputStreamWriter(new FileOutputStream(new File(local)), StandardCharsets.UTF_8)) {

                writer.write(json);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editPessoa(Pessoa pessoaNova, Integer id) {
        throw new IllegalArgumentException("Metodo não suportado");
    }
}
