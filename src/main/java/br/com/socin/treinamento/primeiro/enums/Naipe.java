package br.com.socin.treinamento.primeiro.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Naipe {
  COPAS("Vermelho"), OUROS("Vermelho"), PAUS("Preto"), ESPADAS("Preto");

  @Getter
  private String cor;
}
