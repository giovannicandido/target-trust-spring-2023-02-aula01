package br.com.targettrust.spring.aula.external;

import br.com.targettrust.spring.aula.model.Endereco;
import br.com.targettrust.spring.aula.model.Estado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CepDTO {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    public Endereco toModel() {
        return Endereco.builder()
                .cep(this.cep)
                .logradouro(this.logradouro)
                .cidade(localidade)
                .bairro(this.bairro)
                .estado(Estado.valueOf(this.uf))
                .build();
    }
}
