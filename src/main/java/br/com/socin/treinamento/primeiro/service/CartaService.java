package br.com.socin.treinamento.primeiro.service;

import br.com.socin.treinamento.primeiro.enums.Naipe;
import br.com.socin.treinamento.primeiro.model.Carta;

import java.util.List;
import java.util.Map;

public interface CartaService {
  /**
   * Deve fazer o ordenamento das cartas pelo Naipe
   * @return
   */
  Map<Naipe, List<Carta>> ordernarCartasPorNaipe();

}
