package br.com.targettrust.spring.aula.external;

import br.com.targettrust.spring.aula.model.pessoa.Endereco;
import br.com.targettrust.spring.aula.service.external.CepExternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CepExternalAdapterImpl implements CepExternalService {

    private static final String CEP_PATH = "/ws/%s/json/";

    @Value("${aula.external-service.cep-url}")
    private String host;


    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Optional<Endereco> searchEnderecoByCep(String cep) {
        String url = host + CEP_PATH.formatted(cep);

        ResponseEntity<CepDTO> cepDTOResponseEntity = null;

        try {
            cepDTOResponseEntity = restTemplate.getForEntity(url, CepDTO.class);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }

//        if(cepDTOResponseEntity.getStatusCode() != HttpStatus.OK) {
//            log.warn("Endereço não localizado para cep " + cep);
//            return Optional.empty();
//        }
//
//        try {
//            CepDTO cepResponse = cepDTOResponseEntity.getBody();
//
//            if (cepResponse != null) {
//                return Optional.of(cepResponse.toModel());
//            }
//
//            return Optional.empty();
//
//
//        } catch (Exception ex) {
//            log.error(ex.getMessage(), ex);
//            log.error("Endereço não localizado para cep " + cep);
//            return Optional.empty();
//        }

        return Optional.ofNullable(cepDTOResponseEntity)
                .filter((response) -> response.getStatusCode() == HttpStatus.OK)
                .map(response -> response.getBody())
                .filter(Objects::nonNull)
                .map(cepDTO -> cepDTO.toModel());
    }
}
