package br.com.socin.treinamento.primeiro.service;

import br.com.socin.treinamento.primeiro.enums.Naipe;
import br.com.socin.treinamento.primeiro.model.Carta;
import br.com.socin.treinamento.primeiro.model.Jogador;
import br.com.socin.treinamento.primeiro.model.Jogo;

import java.util.List;
import java.util.Map;

public interface CartaService {
  /**
   * Deve fazer o ordenamento das cartas pelo Naipe
   * @return
   */
  Map<Naipe, List<Carta>> ordernarCartasPorNaipe();

  /**
   * Entrega a carta para o jogador, desde que eles estejam em algum jogo
   * @param jogador o jogador
   * @return retorna as cartas para o jogadro
   */
  List<Carta> entregaCartasParaJogador(Jogador jogador, Jogo jogo);
}
