package br.com.socin.treinamento.segundo.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.socin.treinamento.segundo.interfaces.ICalculadorDeImposto;
import br.com.socin.treinamento.segundo.model.Imposto;
import br.com.socin.treinamento.segundo.model.Orcamento;
import br.com.socin.treinamento.segundo.model.OutrosImpostos;
import br.com.socin.treinamento.segundo.repository.ImpostoRepository;
import br.com.socin.treinamento.segundo.service.ImpostoService;

@Service
public class ImpostoServiceImpl implements ImpostoService {
  @Autowired
  private ImpostoRepository impostoRepository;

  @Override
  // Tipos: ICMS ISS PIS
  // Valores: 3% 6% 10%
  public BigDecimal calculaImposto(Orcamento orcamento, String nomeDoImposto) {
    Imposto imposto =
        impostoRepository.findByNome(nomeDoImposto).orElse(new OutrosImpostos("", BigDecimal.ZERO));



    return imposto.calculaValorDoImposto(orcamento);
  }
}
