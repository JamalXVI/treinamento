package br.com.socin.treinamento.segundo.model;

import java.math.BigDecimal;

public class OutrosImpostos extends Imposto {

  @Override
  public BigDecimal calculaValorDoImposto(Orcamento orcamento) {
    return orcamento.getValor().multiply(this.getValor());
  }

  public OutrosImpostos(String nome, BigDecimal valor) {
    super(nome, valor);
  }
}
