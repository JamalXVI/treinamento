package br.com.socin.treinamento.primeiro.model;

import br.com.socin.treinamento.primeiro.enums.Naipe;
import br.com.socin.treinamento.primeiro.enums.TipoCarta;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Carta {
  @Getter(AccessLevel.NONE)
  private Naipe naipe;
  private TipoCarta tipoCarta;

  public String getNaipe(){
    if (tipoCarta.equals(TipoCarta.JOKER)){
      return naipe.getCor();
    }
    return naipe.name();
  }

  @Override
  public String toString() {
    return getTipoCarta()+"  "+getNaipe();
  }
}
