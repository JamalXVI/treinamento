package br.com.socin.treinamento.primeiro.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Jogador {
  private Integer id;
  private String nome;
  private List<Carta> cartas;
  private Integer pontos;
}
