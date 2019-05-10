package br.com.socin.treinamento.primeiro.model;

import java.util.Map;

import br.com.socin.treinamento.primeiro.enums.TipoDeJogo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Jogo {
  private Integer id;
  private TipoDeJogo jogo;
  private Map<Integer, Jogador> jogadores;
  private Boolean encerrou;
}
