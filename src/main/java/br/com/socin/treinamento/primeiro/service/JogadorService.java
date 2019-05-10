package br.com.socin.treinamento.primeiro.service;

import br.com.socin.treinamento.primeiro.exceptions.JogoCartaException;
import br.com.socin.treinamento.primeiro.model.Jogador;

public interface JogadorService {

  /**
   * Adiciona Jogador na Base
   * @param nome Nome do jogador
   * @return o id do Jogador
   */
  Integer adicionarJogador(String nome) throws JogoCartaException;

  /**
   * Busca o jogador na Base
   * @param idJogador o id do jogador
   * @return o jogador
   */
  Jogador buscarJogador(Integer idJogador) throws JogoCartaException;

  /**
   * Salva o estado do jogador
   * @param jogador
   * @return
   */
  Jogador salvar(Jogador jogador);

}
