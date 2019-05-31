package br.com.socin.treinamento.primeiro.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.socin.treinamento.primeiro.enums.Naipe;
import br.com.socin.treinamento.primeiro.enums.TipoCarta;
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
    List<Carta> cartas = cartaRepository.findAll();
    return cartas.stream().filter(carta -> !carta.getTipoCarta().equals(TipoCarta.JOKER))
        .collect(Collectors.groupingBy(carta -> Naipe.find(carta.getNaipe()).get()));
  }

  @Override
  public List<Carta> entregaCartasParaJogador(Jogador jogador, Jogo jogo) {
    List<Carta> cartas = cartaRepository.findAll();

    Collection<Jogador> jogadores = jogo.getJogadores().values();
    List<Carta> cartasDosOutrosJogadores = jogadores.stream().filter(jog -> !jog.getId()
        .equals(jogador.getId())).flatMap(jog -> jog.getCartas().stream()).collect(Collectors.toList());

    Collections.shuffle(cartas);
    return cartas.stream().filter(carta -> !cartasDosOutrosJogadores.stream()
        .filter(cartaDoOutroJogador -> cartaDoOutroJogador.getNaipe().equals(carta.getNaipe())
        && cartaDoOutroJogador.getTipoCarta().equals(carta.getTipoCarta())).findFirst().isPresent())
        .limit(jogo.getJogo().getNumeroDeCartas()).collect(Collectors.toList());
  }

}
