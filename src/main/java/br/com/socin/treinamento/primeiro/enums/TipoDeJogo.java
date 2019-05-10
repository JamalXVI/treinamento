package br.com.socin.treinamento.primeiro.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TipoDeJogo {
  BLACK_JACK(2, 10), TRUCO(3, 4), OITO_MALUCO(8, 6);

  @Getter
  private Integer numeroDeCartas;
  @Getter
  private Integer limiteJogadores;
}
