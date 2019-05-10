package br.com.socin.treinamento.primeiro.service.impl;

import br.com.socin.treinamento.primeiro.exceptions.JogoCartaException;
import br.com.socin.treinamento.primeiro.model.Jogador;
import br.com.socin.treinamento.primeiro.repository.JogadorRepository;
import br.com.socin.treinamento.primeiro.service.JogadorService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class JogadorServiceImpl implements JogadorService {
  public static final String ERRO_NOME_INVALIDO = "Nome Inválido";
  public static final String ERRO_JOGADOR_NAO_EXISTE = "O Jogador não existe";
  @Autowired
  private JogadorRepository jogadorRepository;

  @Override
  public Integer adicionarJogador(String nome) throws JogoCartaException {
    if (nome == null || nome.isEmpty()){
      throw new JogoCartaException(ERRO_NOME_INVALIDO);
    }
    Jogador jogador = Jogador.builder().nome(nome).build();
    jogador = jogadorRepository.save(jogador);
    return jogador.getId();
  }

  @Override
  public Jogador buscarJogador(Integer idJogador) throws JogoCartaException {
    Optional<Jogador> jogador = jogadorRepository.findById(idJogador);
    if (jogador.isPresent()){
      return jogador.get();
    }
    throw new JogoCartaException(ERRO_JOGADOR_NAO_EXISTE);
  }
}
