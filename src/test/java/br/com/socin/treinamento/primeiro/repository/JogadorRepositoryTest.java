package br.com.socin.treinamento.primeiro.repository;

import br.com.socin.treinamento.primeiro.model.Jogador;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class JogadorRepositoryTest {
  @Spy
  JogadorRepository jogadorRepository;

  @Before
  public void setUp(){
    jogadorRepository = Mockito.spy(new JogadorRepository());
    jogadorRepository.resetarBase();
  }


  @Test
  public void findAll_TudoValido_Ok() {
    Jogador jogador = jogadorRepository.save(Jogador.builder().nome("a").build());
    List<Jogador> jogadores = jogadorRepository.findAll();
    assertTrue("A lista deve ter tamanho 1", jogadores.size() == 1);
  }

  @Test
  public void save_JaPossuiId_Ok() {
    Jogador jogador = jogadorRepository.save(Jogador.builder().nome("a").id(50).build());
    assertTrue("O id do jogador deve ser 50", jogador.getId().equals(50));
  }

  @Test
  public void save_TudoValido_Ok() {
    Jogador jogador = jogadorRepository.save(Jogador.builder().nome("a").build());
    assertTrue("O id do jogador deve ser 1", jogador.getId().equals(1));
  }

  @Test
  public void save_PrimeiroJogadorIdAltoSegundoNaoExiste_Ok() {
    Jogador jogador = jogadorRepository.save(Jogador.builder().nome("a").id(50).build());
    Jogador jogador2 = jogadorRepository.save(Jogador.builder().nome("b").build());
    assertTrue("O id do jogador deve ser 50", jogador.getId().equals(50));
    assertTrue("O id do jogador 2 deve ser 51", jogador2.getId().equals(51));
  }

}