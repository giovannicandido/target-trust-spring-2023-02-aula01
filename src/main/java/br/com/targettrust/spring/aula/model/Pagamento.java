package br.com.targettrust.spring.aula.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "pagador")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pagador_id", foreignKey = @ForeignKey(name = "fk_pagamento_pessoa"))
    private Pessoa pagador;

    @Column(nullable = false)
    private LocalDateTime dataPagamento;
}
