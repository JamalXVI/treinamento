package br.com.socin.treinamento.primeiro.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.socin.treinamento.primeiro.model.Jogo;

@Service
public class JogoRepository {
  private static Map<Integer, Jogo> jogos = new HashMap<>();
  private static Integer ultimoId = 0;

  public void resetarBase() {
    jogos = new HashMap<>();
    ultimoId = 0;
  }

  public List<Jogo> findAll() {
    return this.jogos.values().stream().collect(Collectors.toList());
  }


  public Optional<Jogo> findById(Integer idJogo) {
    return this.jogos.values().stream().filter(jogo -> jogo.getId().equals(idJogo)).findFirst();
  }

  public Jogo save(Jogo jogo) {
    if (jogo.getId() != null) {
      if (jogos.containsKey(jogo.getId())) {
        jogos.replace(jogo.getId(), jogo);
      } else {
        ultimoId = Math.max(ultimoId, jogo.getId());
      }
    } else {
      jogo.setId(++ultimoId);
    }
    jogos.put(jogo.getId(), jogo);
    return jogo;
  }
}
