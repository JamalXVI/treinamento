package br.com.socin.treinamento.segundo.model;

import java.math.BigDecimal;

public class ICMS extends Imposto {

  @Override
  public BigDecimal calculaValorDoImposto(Orcamento orcamento) {
    if (orcamento.getValor().compareTo(BigDecimal.valueOf(50)) > 0){
      return orcamento.getValor().multiply(BigDecimal.valueOf(0.07));
    }
    return orcamento.getValor();
  }

  public ICMS(String nome, BigDecimal valor) {
    super(nome, valor);
  }
}
