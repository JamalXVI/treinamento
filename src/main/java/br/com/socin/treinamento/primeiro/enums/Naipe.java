package br.com.socin.treinamento.primeiro.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
public enum Naipe {
  COPAS("Vermelho"), OUROS("Vermelho"), PAUS("Preto"), ESPADAS("Preto");

  @Getter
  private String cor;

  public static Optional<Naipe> find(String naipeEscolhido){
    return Stream.of(values()).filter(naipe -> naipe.name().equals(naipeEscolhido)).findFirst();
  }
}
