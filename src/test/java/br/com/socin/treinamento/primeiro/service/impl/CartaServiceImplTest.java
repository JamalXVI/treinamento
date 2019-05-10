package br.com.socin.treinamento.primeiro.service.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.socin.treinamento.primeiro.enums.Naipe;
import br.com.socin.treinamento.primeiro.enums.TipoCarta;
import br.com.socin.treinamento.primeiro.enums.TipoDeJogo;
import br.com.socin.treinamento.primeiro.model.Carta;
import br.com.socin.treinamento.primeiro.model.Jogador;
import br.com.socin.treinamento.primeiro.model.Jogo;
import br.com.socin.treinamento.primeiro.repository.CartaRepository;
import br.com.socin.treinamento.primeiro.service.CartaService;

@RunWith(SpringRunner.class)
public class CartaServiceImplTest {
  @Mock
  CartaRepository cartaRepository;


  @Spy
  CartaService cartaService;

  @Before
  public void setUp() {
    cartaService = Mockito.spy(new CartaServiceImpl(cartaRepository));

  }


  @Test
  public void ordernarCartasPorNaipe_TudoValido_Ok() {

    when(cartaRepository.findAll()).thenReturn(CartaRepository.gerarCartas());

    Map<Naipe, List<Carta>> cartasPorNaipe = cartaService.ordernarCartasPorNaipe();
    for (Naipe naipe : Naipe.values()) {
      assertTrue("Deve possuir o Naipe", cartasPorNaipe.containsKey(naipe));
      assertTrue("O tamanho deve ser 13", cartasPorNaipe.get(naipe).size() == 13);
      for (TipoCarta tipoCarta : TipoCarta.values()) {
        if (tipoCarta != TipoCarta.JOKER) {
          assertTrue("Deve conter o tipo de carta",
              cartasPorNaipe.get(naipe).contains(new Carta(naipe, tipoCarta)));
        }
      }
    }
  }

  @Test
  public void entregaCartasParaJogador_TudoValido_Ok() {

    final TreeMap<Integer, Jogador> jogadores = new TreeMap<>();
    jogadores.put(2, criarJogador(3));
    jogadores.put(1, criarJogador(4));

    final Jogo jogo = criarJogo(1, TipoDeJogo.TRUCO, false, jogadores);

    when(cartaRepository.findAll()).thenReturn(CartaRepository.gerarCartas());

    for (int i = 0;i < 1000;i++){
      jogo.getJogadores().get(1).setCartas(new ArrayList<>());

      List<Carta> cartas = this.cartaService.entregaCartasParaJogador(jogadores.get(1), jogo);

      jogo.getJogadores().get(1).setCartas(cartas);

      List<Carta> cartasJogadorDois =
          this.cartaService.entregaCartasParaJogador(jogadores.get(2), jogo);

      System.out.println(cartas.toString());

      System.out.println(cartasJogadorDois.toString());

      assertTrue("As cartas não devem ser iguais",
          cartas.stream().noneMatch(carta -> cartasJogadorDois.contains(carta)));
    }
  }

  private Jogador criarJogador(Integer id) {
    return Jogador.builder().id(id).nome("Jogador " + id).cartas(new ArrayList<>()).pontos(0)
        .build();
  }

  private Jogador criarJogador(Integer id, List<Carta> cartas, Integer pontos) {
    return Jogador.builder().id(id).nome("Jogador " + id).cartas(cartas).pontos(pontos).build();
  }

  private Jogo criarJogo(Integer id, TipoDeJogo tipoDeJogo, Boolean encerrou,
      TreeMap<Integer, Jogador> jogadores) {
    return Jogo.builder().id(id).jogo(tipoDeJogo).encerrou(encerrou).jogadores(jogadores).build();
  }
}
