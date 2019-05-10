package br.com.socin.treinamento.primeiro.service.impl;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import br.com.socin.treinamento.primeiro.enums.TipoDeJogo;
import br.com.socin.treinamento.primeiro.repository.JogoRepository;
import br.com.socin.treinamento.primeiro.service.JogoService;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

public class JogoServiceImplTest {
  @Spy
  private JogoRepository jogoRepository;

  @Spy
  private JogoService jogoService;

  @Before
  public void setUp() {
    jogoRepository = Mockito.spy(new JogoRepository());
    jogoService = Mockito.spy(new JogoServiceImpl(jogoRepository));
  }

  @Test
  public void iniciarJogo_TudoValido_Ok() {
    Integer idJogo =
        this.jogoService.iniciarJogo(Arrays.asList(new Integer[] {1, 2, 3, 4}), TipoDeJogo.TRUCO);

    assertTrue("O id do jogo deve ser 1", idJogo == 1);
  }

  @Test
  public void distribuiCartas_TudoValido_Ok() {
    Boolean distribuiCartas = this.jogoService.distribuiCartas(1);

    assertTrue("As cartas foram distribuídas", distribuiCartas);
  }
}
