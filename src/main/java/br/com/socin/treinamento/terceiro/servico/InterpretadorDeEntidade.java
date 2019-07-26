package br.com.socin.treinamento.terceiro.servico;

import br.com.socin.treinamento.terceiro.anotacoes.Analise;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class InterpretadorDeEntidade {
    public <K> K preenche(K entidade, String[] valores, Integer posicao) {
        Method[] metodos = entidade.getClass().getDeclaredMethods();
        Stream.of(metodos).filter(metodo -> Optional.ofNullable(metodo.getAnnotation(Analise.class)).isPresent()
                && metodo.getParameterCount() == 1 &&
                valores[posicao].equals(metodo.getAnnotation(Analise.class).value()))
                .forEach(metodo -> {
                    try {
                        Class<?> parametro = metodo.getParameterTypes()[0];
                        if (parametro.equals(Long.class)) {

                            metodo.invoke(entidade, Long.valueOf(valores[posicao + 1]));
                        } else if (parametro.equals(BigDecimal.class)){
                            metodo.invoke(entidade, new BigDecimal(valores[posicao + 1]));
                        } else {
                            metodo.invoke(entidade, valores[posicao + 1]);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
        return entidade;
    }
}
