package br.com.socin.treinamento.primeiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.socin.treinamento.primeiro.enums.TipoDeJogo;
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
    return null;
  }

  @Override
  public Jogo distribuiCartas(Integer idJogo) {
    return null;
  }

  @Override
  public Jogo salvar(Jogo jogo) {
    return jogoRepository.save(jogo);
  }
}
