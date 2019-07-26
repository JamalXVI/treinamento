package br.com.socin.treinamento.terceiro.modelo;

import br.com.socin.treinamento.terceiro.anotacoes.Analise;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
    @Setter(onMethod = @__({@Analise("0")}))
    private Long id;
    @Setter(onMethod = @__({@Analise("1")}))
    private String nome;
    @Setter(onMethod = @__({@Analise("2")}))
    private String sobrenome;
}
