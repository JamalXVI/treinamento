package br.com.socin.treinamento.primeiro.service;

import br.com.socin.treinamento.primeiro.enums.TipoDeJogo;

import java.util.List;

public interface JogoService {

  Integer iniciarJogo(List<Integer> jogadores, TipoDeJogo jogo);

  Boolean distribuiCartas(Integer idJogo);


}
