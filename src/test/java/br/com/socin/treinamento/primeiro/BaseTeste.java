package br.com.socin.treinamento.primeiro;

import br.com.socin.treinamento.primeiro.exceptions.JogoCartaException;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class BaseTeste {
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  public void esperarExcecao(String mensagem){
    thrown.expectMessage(mensagem);
    thrown.expect(JogoCartaException.class);
  }
  public void esperarExcecao(){
    thrown.expect(RuntimeException.class);
  }
}
