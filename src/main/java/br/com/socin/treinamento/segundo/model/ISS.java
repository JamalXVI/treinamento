package br.com.socin.treinamento.segundo.model;

import java.math.BigDecimal;

public class ISS extends Imposto {

  @Override
  public BigDecimal calculaValorDoImposto(Orcamento orcamento) {
    if (orcamento.getValor().compareTo(BigDecimal.valueOf(300)) > 0){
      return orcamento.getValor().multiply(BigDecimal.valueOf(0.08));
    }
    return orcamento.getValor().multiply(this.getValor());
  }

  public ISS(String nome, BigDecimal valor) {
    super(nome, valor);
  }
}
