package br.com.socin.treinamento.primeiro.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.socin.treinamento.primeiro.model.Jogador;
import org.springframework.stereotype.Service;


@Service
public class JogadorRepository {
  private static Map<Integer, Jogador> jogadores = new HashMap<>();
  private static Integer ultimoId = 0;

  public void resetarBase(){
    jogadores = new HashMap<>();
    ultimoId = 0;
  }

  public List<Jogador> findAll() {
    return this.jogadores.values().stream().collect(Collectors.toList());
  }

  public Optional<Jogador> findById(Integer idJogador){
    return this.jogadores.values().stream().filter(jogador -> jogador.getId().equals(idJogador))
        .findFirst();
  }

  public Jogador save(Jogador jogador) {
    if (jogador.getId() != null) {
      if (jogadores.containsKey(jogador.getId())) {
        jogadores.replace(jogador.getId(), jogador);
      }else{
        ultimoId = Math.max(ultimoId, jogador.getId());
      }
    } else {
      jogador.setId(++ultimoId);
    }
    jogadores.put(jogador.getId(), jogador);
    return jogador;
  }
}
