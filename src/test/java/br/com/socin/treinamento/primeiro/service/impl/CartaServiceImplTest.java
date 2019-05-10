package br.com.socin.treinamento.primeiro.service.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import br.com.socin.treinamento.primeiro.repository.JogadorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import br.com.socin.treinamento.primeiro.enums.Naipe;
import br.com.socin.treinamento.primeiro.enums.TipoCarta;
import br.com.socin.treinamento.primeiro.model.Carta;
import br.com.socin.treinamento.primeiro.repository.CartaRepository;
import br.com.socin.treinamento.primeiro.service.CartaService;

public class CartaServiceImplTest {
  @Mock
  CartaRepository cartaRepository;

  @Spy
  JogadorRepository jogadorRepository;

  @Spy
  CartaService cartaService;

  @Before
  public void setUp() {
    cartaService = Mockito.spy(new CartaServiceImpl(cartaRepository, jogadorRepository));

  }


  @Test
  public void ordernarCartasPorNaipe_TudoValido_Ok() {
    Map<Naipe, List<Carta>> cartasPorNaipe = cartaService.ordernarCartasPorNaipe();
    for (Naipe naipe : Naipe.values()) {
      assertTrue("Deve possuir o Naipe", cartasPorNaipe.containsKey(naipe));
      assertTrue("O tamanho deve ser 13", cartasPorNaipe.size() == 13);
      for (TipoCarta tipoCarta : TipoCarta.values()) {
        if (tipoCarta != TipoCarta.JOKER) {
          assertTrue("Deve conter o tipo de carta", cartasPorNaipe.get(naipe).contains(tipoCarta));
        }
      }
    }
  }

}
