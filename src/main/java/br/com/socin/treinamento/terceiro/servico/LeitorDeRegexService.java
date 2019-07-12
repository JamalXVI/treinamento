package br.com.socin.treinamento.terceiro.servico;

import org.springframework.stereotype.Service;

@Service
public class LeitorDeRegexService {
    /***
     * Tipo: Pessoa / Produto
     * ACAO: CADASTRA / ATUALIZA
     * PARAM: PARAMETRO
     * VLR: VALOR
     */
    //TIPO ACAO PARAM VLR
    public void ler() {
        String regex = " 0 1 1 João 2 Da Silva";
        String[] valores = regex.replaceFirst(" ", "")
                .split(" ");

        if (valores[0] == "0") {
            //Cadastra Pessoa
            if (valores[1] == "0") {
                for (int i=2;i < valores.length;i += 2){
                    //...Como vamos pegar os parâmetros?
                }
            //Atualiza Pessoa
            }else{

            }
        } else if (valores[0] == "1") {
            //Cadastra Produto
            if (valores[1] == "0") {
                for (int i=2;i < valores.length;i += 2){
                    //...Como vamos pegar os parâmetros?
                }
                //Atualiza Produto
            }else{

            }
        }
    }
}
