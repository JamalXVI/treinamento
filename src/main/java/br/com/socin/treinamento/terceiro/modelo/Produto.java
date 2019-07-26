package br.com.socin.treinamento.terceiro.modelo;

import br.com.socin.treinamento.terceiro.anotacoes.Analise;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Setter(onMethod = @__({@Analise("0")}))
    private Long id;
    @Setter(onMethod = @__({@Analise("1")}))
    private String nome;
    @Setter(onMethod = @__({@Analise("2")}))
    private BigDecimal preco;
}
