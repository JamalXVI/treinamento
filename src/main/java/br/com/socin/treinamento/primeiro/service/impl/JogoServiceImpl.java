package br.com.socin.treinamento.primeiro.service.impl;

import static br.com.socin.treinamento.util.LambdaExceptionUtil.rethrowFunction;

import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.socin.treinamento.primeiro.enums.TipoDeJogo;
import br.com.socin.treinamento.primeiro.model.Jogador;
import br.com.socin.treinamento.primeiro.model.Jogo;
import br.com.socin.treinamento.primeiro.repository.JogoRepository;
import br.com.socin.treinamento.primeiro.service.CartaService;
import br.com.socin.treinamento.primeiro.service.JogadorService;
import br.com.socin.treinamento.primeiro.service.JogoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class JogoServiceImpl implements JogoService {
  public static final String TAMANHO_LISTA_IGUAL = "O tamanho da lista deve ser o mesmo";
  public static final String O_LIMITE_DO_TIPO_JOGO_DEVE_SER_MAIOR_OU_IGUAL =
      "O limite do tipoJogo deve ser maior ou igual";
  public static final String O_JOGO_NÃO_PODE_ESTAR_ENCERRADO = "O jogo não pode estar encerrado";
  public static final String O_JOGO_DEVE_EXISTIR = "O jogo deve existir";
  @Autowired
  private JogoRepository jogoRepository;
  @Autowired
  private JogadorService jogadorService;
  @Autowired
  private CartaService cartaService;

  @Override
  public Jogo iniciarJogo(List<Integer> jogadores, List<Integer> ordem, TipoDeJogo tipoJogo) {
    final List<Integer> jogadoresProibidos =
        jogoRepository.findAll().stream().filter(jogo -> !jogo.getEncerrou())
            .flatMap(jogo -> jogo.getJogadores().values().stream().map(Jogador::getId))
            .collect(Collectors.toList());

    final List<Jogador> jogadoresMapeados =
        jogadores.stream().filter(jogador -> !jogadoresProibidos.contains(jogador))
            .map(rethrowFunction(idDoJogador -> jogadorService.buscarJogador(idDoJogador)))
            .collect(Collectors.toList());

    Assert.isTrue(jogadoresMapeados.size() == ordem.size(), TAMANHO_LISTA_IGUAL);

    Assert.isTrue(jogadoresMapeados.size() <= tipoJogo.getLimiteJogadores(),
        O_LIMITE_DO_TIPO_JOGO_DEVE_SER_MAIOR_OU_IGUAL);

    final TreeMap<Integer, Jogador> ordemDosJogadores = IntStream.range(0, jogadoresMapeados.size()).boxed()
        .collect(Collectors.toMap(ordem::get, jogadoresMapeados::get, (chave1, chave2) -> {
          throw new IllegalStateException(String.format("Chave Duplicada! %s", chave1));
        }, TreeMap::new));

    Jogo jogo =
        Jogo.builder().jogadores(ordemDosJogadores).encerrou(Boolean.FALSE).jogo(tipoJogo).build();

    jogo = salvar(jogo);
    return jogo;
  }

  @Override
  public Jogo distribuiCartas(Integer idJogo) {

    Optional<Jogo> jogo = jogoRepository.findById(idJogo);
    Assert.isTrue(jogo.isPresent(), O_JOGO_DEVE_EXISTIR);
    Jogo jogoEncontrado = jogo.get();
    Assert.isTrue(!jogoEncontrado.getEncerrou(), O_JOGO_NÃO_PODE_ESTAR_ENCERRADO);
    jogoEncontrado.getJogadores().values().forEach(jogador -> jogador
        .setCartas(cartaService.entregaCartasParaJogador(jogador, jogoEncontrado)));
    salvar(jogoEncontrado);
    return jogoEncontrado;
  }

  @Override
  public Jogo salvar(Jogo jogo) {
    return jogoRepository.save(jogo);
  }
}
