package br.com.targettrust.spring.aula.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartaoCredito {

    @Id
    private Long id;
    private String numero;
}
