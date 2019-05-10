package br.com.socin.treinamento.primeiro.repository;

import br.com.socin.treinamento.primeiro.enums.Naipe;
import br.com.socin.treinamento.primeiro.enums.TipoCarta;
import br.com.socin.treinamento.primeiro.model.Carta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CartaRepository {
  private static List<Carta> cartas = gerarCartas();

  public static List<Carta> gerarCartas() {
    return Stream.of(TipoCarta.values()).map(tipoCarta -> {
      return Stream.of(Naipe.values()).map(naipe -> new Carta(naipe, tipoCarta))
          .collect(Collectors.toList());
    }).flatMap(cartasPorNaipe -> cartasPorNaipe.stream()).distinct().collect(Collectors.toList());
  }

  public List<Carta> findAll(){
    return cartas;
  }
}
