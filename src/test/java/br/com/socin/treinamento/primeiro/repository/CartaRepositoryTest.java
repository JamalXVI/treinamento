package br.com.socin.treinamento.primeiro.repository;

import br.com.socin.treinamento.primeiro.enums.TipoCarta;
import br.com.socin.treinamento.primeiro.model.Carta;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CartaRepositoryTest {

  @Before
  public void setUp(){

  }

  @Test
  public void gerarCartas_TudoValido_Ok(){
    List<Carta> cartas = CartaRepository.gerarCartas();

    assertTrue("Devem existir 54 cartas", cartas.size() == 54);

  }
}