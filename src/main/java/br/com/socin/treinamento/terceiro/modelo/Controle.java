package br.com.socin.treinamento.terceiro.modelo;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class Controle {
    private List<String> itens = Arrays.asList("Item1","Item2","Item3");
}
