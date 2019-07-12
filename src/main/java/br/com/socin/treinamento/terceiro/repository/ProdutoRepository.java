package br.com.socin.treinamento.terceiro.repository;

import br.com.socin.treinamento.terceiro.modelo.Produto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoRepository {
    private List<Produto> produtos = Arrays.asList(Produto.builder().id(1L).nome("Guilhermando").preco(BigDecimal.valueOf(23.9)).build());

    public Optional<Produto> findById(Long id) {
        return produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public void cadastra(Produto p) {
        if (p.getId() > 0 || findById(p.getId()).isPresent()) {
            throw new RuntimeException("Já Existe um cadastro de Pessoa!");
        }
        produtos.add(p);
    }

    public void atualiza(Produto p) {
        Optional<Produto> pessoaOp = findById(p.getId());
        if (p.getId() < 0 || !pessoaOp.isPresent()) {
            throw new RuntimeException("Não existe pessoa com este cadastro");
        }
        Produto produto = pessoaOp.get();
        produto.setNome(p.getNome());
        produto.setPreco(p.getPreco());
        int indice = produtos.indexOf(pessoaOp.get());
        produtos.set(indice, produto);
    }
}
