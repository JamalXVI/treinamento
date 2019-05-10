package br.com.socin.treinamento.primeiro.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.socin.treinamento.primeiro.enums.Naipe;
import br.com.socin.treinamento.primeiro.model.Carta;
import br.com.socin.treinamento.primeiro.model.Jogador;
import br.com.socin.treinamento.primeiro.model.Jogo;
import br.com.socin.treinamento.primeiro.repository.CartaRepository;
import br.com.socin.treinamento.primeiro.service.CartaService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CartaServiceImpl implements CartaService {
  @Autowired
  CartaRepository cartaRepository;

  @Override
  public Map<Naipe, List<Carta>> ordernarCartasPorNaipe() {
    return null;
  }

  @Override
  public List<Carta> entregaCartasParaJogador(Jogador jogador, Jogo jogo) {
    return null;
  }

}
