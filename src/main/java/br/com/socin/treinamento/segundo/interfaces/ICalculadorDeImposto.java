package br.com.socin.treinamento.segundo.interfaces;

import br.com.socin.treinamento.segundo.model.Imposto;
import br.com.socin.treinamento.segundo.model.Orcamento;

import java.math.BigDecimal;

public interface ICalculadorDeImposto {
  BigDecimal calculaImposto(Orcamento orcamento, Imposto imposto);
}
