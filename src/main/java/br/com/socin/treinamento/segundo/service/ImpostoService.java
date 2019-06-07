package br.com.socin.treinamento.segundo.service;

import br.com.socin.treinamento.segundo.model.Orcamento;

import java.math.BigDecimal;

public interface ImpostoService {
  BigDecimal calculaImposto(Orcamento orcamento, String nomeDoImposto);
}
