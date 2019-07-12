package br.com.socin.treinamento.segundo.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public abstract class Imposto {
  @Getter(AccessLevel.PUBLIC)
  private String nome;
  @Getter(AccessLevel.PUBLIC)
  private BigDecimal valor;

  public abstract BigDecimal calculaValorDoImposto(Orcamento orcamento);
}
