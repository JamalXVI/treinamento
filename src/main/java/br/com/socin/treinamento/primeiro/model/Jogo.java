package br.com.socin.treinamento.primeiro.model;

import br.com.socin.treinamento.primeiro.enums.TipoDeJogo;
import lombok.Data;

import java.util.List;

@Data
public class Jogo {
  private Integer id;
  private TipoDeJogo jogo;
  private List<Jogador> jogadores;
  private Boolean encerrou;
}
