package br.com.socin.treinamento.primeiro.service.impl;

import static br.com.socin.treinamento.primeiro.service.impl.JogadorServiceImpl.ERRO_JOGADOR_NAO_EXISTE;
import static br.com.socin.treinamento.primeiro.service.impl.JogadorServiceImpl.ERRO_NOME_INVALIDO;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import br.com.socin.treinamento.primeiro.BaseTeste;
import br.com.socin.treinamento.primeiro.exceptions.JogoCartaException;
import br.com.socin.treinamento.primeiro.model.Jogador;
import br.com.socin.treinamento.primeiro.repository.JogadorRepository;
import br.com.socin.treinamento.primeiro.service.JogadorService;

public class JogadorServiceImplTest extends BaseTeste {

  @Spy
  JogadorRepository jogadorRepository;

  @Spy
  JogadorService jogadorService;

  @Before
  public void setUp() {
    jogadorRepository = Mockito.spy(new JogadorRepository());
    jogadorService = Mockito.spy(new JogadorServiceImpl(jogadorRepository));
  }

  @Test
  public void adicionarJogador_TudoValido_Ok() throws JogoCartaException {
    mockSalvarJogador();
    Integer idJogador = this.jogadorService.adicionarJogador("Adam");
    assertTrue("O id deve ser 1", idJogador == 1);
  }

  @Test
  public void adicionarJogador_NomeNulo_ErroNomeInvalido() throws JogoCartaException {
    mockSalvarJogador();
    esperarExcecao(ERRO_NOME_INVALIDO);
    Integer idJogador = this.jogadorService.adicionarJogador(null);
  }

  @Test
  public void adicionarJogador_NomeVazop_ErroNomeInvalido() throws JogoCartaException {
    mockSalvarJogador();
    esperarExcecao(ERRO_NOME_INVALIDO);
    Integer idJogador = this.jogadorService.adicionarJogador("");
  }

  private void mockSalvarJogador() {

    doAnswer(new Answer<Jogador>() {
      @Override
      public Jogador answer(InvocationOnMock invocationOnMock) throws Throwable {
        Jogador jogador = (Jogador) invocationOnMock.getArgument(0);
        jogador.setId(1);
        return jogador;
      }
    }).when(jogadorRepository).save(any(Jogador.class));
  }

  @Test
  public void buscarJogador_TudoValido_Ok() throws JogoCartaException {
    doReturn(Optional.ofNullable(Jogador.builder().build())).when(jogadorRepository)
        .findById(anyInt());

    Jogador jogador = jogadorService.buscarJogador(1);

    assertTrue("O jogador não deve ser nulo", jogador != null);
  }

  @Test
  public void buscarJogador_NaoEncontrado_ErroJogadorNaoExiste() throws JogoCartaException {
    doReturn(Optional.ofNullable(null)).when(jogadorRepository)
        .findById(anyInt());

    esperarExcecao(ERRO_JOGADOR_NAO_EXISTE);

    Jogador jogador = jogadorService.buscarJogador(1);
  }

  @Test
  public void buscarJogador_EntradaNula_ErroJogadorNaoExiste() throws JogoCartaException {
    doReturn(Optional.ofNullable(null)).when(jogadorRepository)
        .findById(anyInt());

    esperarExcecao(ERRO_JOGADOR_NAO_EXISTE);

    Jogador jogador = jogadorService.buscarJogador(null);
  }
}
