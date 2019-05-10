package br.com.socin.treinamento.primeiro.service;

import br.com.socin.treinamento.primeiro.enums.TipoDeJogo;
import br.com.socin.treinamento.primeiro.model.Jogador;
import br.com.socin.treinamento.primeiro.model.Jogo;

import java.util.List;

public interface JogoService {

  Jogo iniciarJogo(List<Integer> jogadores, List<Integer> ordem, TipoDeJogo jogo);

  Jogo distribuiCartas(Integer idJogo);

  Jogo salvar(Jogo jogo);
}
