package br.com.socin.treinamento.primeiro.service.impl;

import br.com.socin.treinamento.primeiro.enums.TipoDeJogo;
import br.com.socin.treinamento.primeiro.repository.JogoRepository;
import br.com.socin.treinamento.primeiro.service.CartaService;
import br.com.socin.treinamento.primeiro.service.JogadorService;
import br.com.socin.treinamento.primeiro.service.JogoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class JogoServiceImpl implements JogoService {
  @Autowired
  private JogoRepository jogoRepository;
  @Autowired
  private JogadorService jogadorService;
  @Autowired
  private CartaService cartaService;

  @Override
  public Integer iniciarJogo(List<Integer> jogadores, TipoDeJogo jogo) {

    return null;
  }

  @Override
  public Boolean distribuiCartas(Integer idJogo) {
    return null;
  }
}
