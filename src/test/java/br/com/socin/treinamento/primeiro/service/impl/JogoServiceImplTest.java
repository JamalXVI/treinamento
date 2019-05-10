package br.com.socin.treinamento.primeiro.service.impl;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.socin.treinamento.primeiro.BaseTeste;
import br.com.socin.treinamento.primeiro.enums.Naipe;
import br.com.socin.treinamento.primeiro.enums.TipoCarta;
import br.com.socin.treinamento.primeiro.enums.TipoDeJogo;
import br.com.socin.treinamento.primeiro.exceptions.JogoCartaException;
import br.com.socin.treinamento.primeiro.model.Carta;
import br.com.socin.treinamento.primeiro.model.Jogador;
import br.com.socin.treinamento.primeiro.model.Jogo;
import br.com.socin.treinamento.primeiro.repository.JogoRepository;
import br.com.socin.treinamento.primeiro.service.CartaService;
import br.com.socin.treinamento.primeiro.service.JogadorService;
import br.com.socin.treinamento.primeiro.service.JogoService;

@RunWith(SpringRunner.class)
public class JogoServiceImplTest extends BaseTeste {
  @Mock
  private JogoRepository jogoRepository;
  @Mock
  private JogadorService jogadorService;
  @Mock
  private CartaService cartaService;

  @Spy
  private JogoService jogoService;

  @Before
  public void setUp() {
    jogoService = Mockito.spy(new JogoServiceImpl(jogoRepository, jogadorService, cartaService));
  }

  @Test
  public void iniciarJogo_TudoValido_Ok() throws JogoCartaException {

    final TreeMap<Integer, Jogador> jogadores = new TreeMap<>();
    jogadores.put(4, criarJogador(3));
    jogadores.put(3, criarJogador(4));

    final List<Jogo> jogos = Arrays.asList(criarJogo(1, TipoDeJogo.TRUCO, true, jogadores));

    when(jogoRepository.findAll()).thenReturn(jogos);

    mockarSalvarJogo();

    mockarBuscarJogador();

    Jogo jogo = this.jogoService.iniciarJogo(Arrays.asList(1, 2, 3, 4), Arrays.asList(4, 3, 2, 1),
        TipoDeJogo.TRUCO);

    assertTrue("O id do jogo deve ser 1", jogo.getId() == 1);
    List<Integer> ordemDosJogadoresNoJogo = Arrays.asList(4, 3, 2, 1);
    int i = 0;
    for (Jogador jogador : jogo.getJogadores().values()) {
      assertTrue("O id do jogador " + i + "deve ser o mesmo:",
          jogador.getId().equals(ordemDosJogadoresNoJogo.get(i)));
      i++;
    }
  }

  @Test
  public void iniciarJogo_ExisteJogadorEmJogo_TamanhoListaDeveSerIgual() throws JogoCartaException {

    final TreeMap<Integer, Jogador> jogadores = new TreeMap<>();
    jogadores.put(4, criarJogador(3));
    jogadores.put(3, criarJogador(4));

    final List<Jogo> jogos = Arrays.asList(criarJogo(1, TipoDeJogo.TRUCO, false, jogadores));

    when(jogoRepository.findAll()).thenReturn(jogos);

    mockarSalvarJogo();

    mockarBuscarJogador();

    esperarExcecao();

    Jogo jogo = this.jogoService.iniciarJogo(Arrays.asList(1, 2, 3, 4), Arrays.asList(4, 3, 2, 1),
        TipoDeJogo.TRUCO);
  }

  @Test
  public void iniciarJogo_ExitemMaisJogadoresQueOJogoPermite_OLimiteTipoJogoDeveSerMaiorOuIgual()
      throws JogoCartaException {

    final TreeMap<Integer, Jogador> jogadores = new TreeMap<>();
    jogadores.put(4, criarJogador(3));
    jogadores.put(3, criarJogador(4));

    final List<Jogo> jogos = Arrays.asList(criarJogo(1, TipoDeJogo.TRUCO, true, jogadores));

    when(jogoRepository.findAll()).thenReturn(jogos);

    mockarSalvarJogo();

    mockarBuscarJogador();

    esperarExcecao();

    Jogo jogo = this.jogoService.iniciarJogo(Arrays.asList(1, 2, 3, 4, 5),
        Arrays.asList(4, 3, 2, 1, 5), TipoDeJogo.TRUCO);
  }

  @Test
  public void distribuiCartas_TudoValido_Ok() {

    final TreeMap<Integer, Jogador> jogadores = new TreeMap<>();
    jogadores.put(4, criarJogador(3));
    jogadores.put(3, criarJogador(4));

    final Jogo jogoEscolhido = criarJogo(1, TipoDeJogo.TRUCO, false, jogadores);

    when(jogoRepository.findById(anyInt())).thenReturn(Optional.of(jogoEscolhido));

    mockarCartasParaJogador();

    mockarSalvarJogo();

    Jogo jogo = this.jogoService.distribuiCartas(1);

    int i = 0;
    List<Carta[]> cartasParaJogadores =
        Arrays.asList(cartasParaOJogadorUm(), cartasParaOJogadorDois());

    List<Integer> idDosJogadores = Arrays.asList(4, 3);
    for (Jogador jogador : jogo.getJogadores().values()) {
      assertTrue("O id do Jogador deve ser o mesmo", idDosJogadores.get(i).equals(jogador.getId()));

      assertTrue("As cartas devem ser as mesmas", Stream.of(cartasParaJogadores.get(i))
          .allMatch(carta -> jogador.getCartas().contains(carta)));
      i++;
    }

  }

  @Test
  public void distribuiCartas_JogoEncerrado_OJogoNaoPodeEstarEncerrado() {

    final TreeMap<Integer, Jogador> jogadores = new TreeMap<>();
    jogadores.put(4, criarJogador(3));
    jogadores.put(3, criarJogador(4));

    final Jogo jogoEscolhido = criarJogo(1, TipoDeJogo.TRUCO, true, jogadores);

    when(jogoRepository.findById(anyInt())).thenReturn(Optional.of(jogoEscolhido));

    mockarCartasParaJogador();

    mockarSalvarJogo();

    esperarExcecao();

    Jogo jogo = this.jogoService.distribuiCartas(1);

  }

  @Test
  public void distribuiCartas_JogoNaoExiste_JogoDeveExistir() {

    when(jogoRepository.findById(anyInt())).thenReturn(Optional.empty());

    mockarCartasParaJogador();

    mockarSalvarJogo();

    esperarExcecao();

    Jogo jogo = this.jogoService.distribuiCartas(1);

  }

  private void mockarCartasParaJogador() {
    when(cartaService.entregaCartasParaJogador(any(Jogador.class), any(Jogo.class)))
        .thenReturn(Arrays.asList(cartasParaOJogadorUm()))
        .thenReturn(Arrays.asList(cartasParaOJogadorDois()));
  }

  private Carta[] cartasParaOJogadorDois() {
    return new Carta[] {criarCarta(TipoCarta.A), criarCarta(TipoCarta.TRES),
        criarCarta(TipoCarta.Q)};
  }

  private Carta[] cartasParaOJogadorUm() {
    return new Carta[] {criarCarta(TipoCarta.J), criarCarta(TipoCarta.DEZ),
        criarCarta(TipoCarta.DOIS)};
  }

  private Carta criarCarta(TipoCarta carta) {
    return new Carta(Naipe.COPAS, carta);
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

  private void mockarBuscarJogador() throws JogoCartaException {
    when(jogadorService.buscarJogador(anyInt())).thenAnswer(new Answer<Jogador>() {
      @Override
      public Jogador answer(InvocationOnMock invocationOnMock) throws Throwable {
        Integer id = (Integer) invocationOnMock.getArgument(0);
        return criarJogador(id);
      }
    });
  }

  private void mockarSalvarJogo() {
    doAnswer(new Answer<Jogo>() {
      @Override
      public Jogo answer(InvocationOnMock invocationOnMock) throws Throwable {
        Jogo jogo = (Jogo) invocationOnMock.getArgument(0);
        jogo.setId(1);
        return jogo;
      }
    }).when(jogoService).salvar(any(Jogo.class));
  }

}
