package br.com.socin.treinamento.primeiro.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TipoDeJogo {
  BLACK_JACK(2), TRUCO(7), OITO_MALUCO(8);

  @Getter(AccessLevel.NONE)
  private Integer numeroDeCartas;
}
