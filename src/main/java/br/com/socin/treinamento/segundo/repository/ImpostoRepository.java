package br.com.socin.treinamento.segundo.repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.socin.treinamento.segundo.model.ICMS;
import br.com.socin.treinamento.segundo.model.ISS;
import br.com.socin.treinamento.segundo.model.Imposto;
import br.com.socin.treinamento.segundo.model.OutrosImpostos;

@Service
public class ImpostoRepository {

  private static List<Imposto> impostos = Arrays.asList(new ICMS("ICMS", BigDecimal.valueOf(3)),
      new ISS("ISS", BigDecimal.valueOf(6)), new OutrosImpostos("PIS", BigDecimal.valueOf(10)));


  public List<Imposto> findAll() {
    return impostos;
  }

  public Optional<Imposto> findByNome(String nome) {
    return impostos.stream().filter(imposto -> imposto.getNome().equals(nome)).findFirst();
  }
}
