package br.com.socin.treinamento.terceiro.anotacoes;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Analise {
    String value();
}
