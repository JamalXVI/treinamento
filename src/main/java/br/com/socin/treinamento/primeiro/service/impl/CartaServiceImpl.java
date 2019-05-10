package br.com.socin.treinamento.primeiro.service.impl;

import br.com.socin.treinamento.primeiro.enums.Naipe;
import br.com.socin.treinamento.primeiro.model.Carta;
import br.com.socin.treinamento.primeiro.repository.CartaRepository;
import br.com.socin.treinamento.primeiro.repository.JogadorRepository;
import br.com.socin.treinamento.primeiro.service.CartaService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CartaServiceImpl implements CartaService {
  @Autowired
  CartaRepository cartaRepository;

  @Autowired
  JogadorRepository jogadorRepository;

  @Override
  public Map<Naipe, List<Carta>> ordernarCartasPorNaipe() {
    return null;
  }

}
