package br.com.socin.treinamento.terceiro.servico;

import br.com.socin.treinamento.terceiro.interfaces.Entidade;
import br.com.socin.treinamento.terceiro.modelo.Pessoa;
import br.com.socin.treinamento.terceiro.modelo.Produto;
import br.com.socin.treinamento.terceiro.repository.PessoaRepository;
import br.com.socin.treinamento.terceiro.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

@Service
public class LeitorDeRegexService {
    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    InterpretadorDeEntidade interpretadorDeEntidade;

    /***
     * Tipo: Pessoa / Produto
     * ACAO: CADASTRA / ATUALIZA
     * PARAM: PARAMETRO
     * VLR: VALOR
     */
    //TIPO ACAO PARAM VLR
    @Scheduled(cron = "*/2 * * * * *")
    public void ler() throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        String regex = " Pessoa 1 0 1 1 João 2 Silva";
        String[] valores = regex.replaceFirst(" ", "")
                .split(" ");

        if (valores[0].equals("Pessoa")) {
            //Cadastra Pessoa
            if (valores[1].equals("0")) {
                Class<Pessoa> pessoaClass = Pessoa.class;
                Pessoa pessoa = pessoaClass.newInstance();
                for (int i = 2; i < valores.length; i += 2) {
                    //...Como vamos pegar os parâmetros?
                    if (valores[i].equals("1")) {
                        pessoa.setNome(valores[i + 1]);
                    } else {
                        pessoa.setSobrenome(valores[i + 1]);
                    }
                }
                pessoaRepository.cadastra(pessoa);
                //Atualiza Pessoa
            } else {
                Class<Pessoa> classPessoa = (Class<Pessoa>) Class.forName("br.com.socin.treinamento.terceiro.modelo." + valores[0]);
                Constructor<Pessoa> constructor = classPessoa.getDeclaredConstructor();
                Pessoa pessoa1 = constructor.newInstance(1L, "a", "b");
                //Métodos
                //Get Declared Methods
                //Set Acessible
                //Invoke
                Pessoa pessoa = classPessoa.newInstance();
                for (int i = 2; i < valores.length; i += 2) {
                    pessoa = (Pessoa) interpretadorDeEntidade.preenche(pessoa, valores, i);
                    if (valores[i].equals("2")) {
                        pessoa.setSobrenome(valores[i + 1]);
                    }
                }
                pessoaRepository.atualiza(pessoa);
            }
        } else if (valores[0].equals("Produto")) {
            //Cadastra Produto
            if (valores[1].equals("0")) {
                Produto produto = new Produto();
                for (int i = 2; i < valores.length; i += 2) {
                    //...Como vamos pegar os parâmetros?
                    if (valores[i].equals("1")) {
                        produto.setNome(valores[i + 1]);
                    } else if (valores[i].equals("2")) {
                        produto.setPreco(BigDecimal.valueOf(Double.valueOf(valores[i + 1])));
                    }
                }
                produtoRepository.cadastra(produto);
                //Atualiza Produto
            } else {
                Produto produto = new Produto();
                for (int i = 2; i < valores.length; i += 2) {
                    //...Como vamos pegar os parâmetros?
                    if (valores[i].equals("1")) {
                        produto.setNome(valores[i + 1]);
                    } else if (valores[i].equals("2")) {
                        produto.setPreco(BigDecimal.valueOf(Double.valueOf(valores[i + 1])));
                    } else {
                        produto.setId(Long.valueOf(valores[i + 1]));
                    }
                }
                produtoRepository.atualiza(produto);

            }
        }
    }
}
