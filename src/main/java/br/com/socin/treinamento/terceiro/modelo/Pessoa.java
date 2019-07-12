package br.com.socin.treinamento.terceiro.modelo;

import br.com.socin.treinamento.terceiro.interfaces.Entidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa implements Entidade {
    private Long id;
    private String nome;
    private String sobrenome;
}
