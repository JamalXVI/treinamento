package br.com.socin.treinamento.terceiro.modelo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Pessoa {
    private Long id;
    private String nome;
    private String sobrenome;
}
