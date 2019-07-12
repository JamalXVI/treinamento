package br.com.socin.treinamento.terceiro.servico;

import br.com.socin.treinamento.terceiro.interfaces.Entidade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterpretadorDeEntidade {
    public Entidade preenche(Entidade entidade, String[] valores, Integer posicao){
        if (valores[posicao].equals("1")) {
            entidade.setNome(valores[posicao + 1]);
        } else if (valores[posicao].equals("0")){
            entidade.setId(Long.valueOf(valores[posicao + 1]));
        }
        return entidade;
    }
}
