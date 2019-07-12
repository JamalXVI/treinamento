package br.com.socin.treinamento.terceiro.repository;

import br.com.socin.treinamento.terceiro.modelo.Pessoa;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaRepository {
    private List<Pessoa> pessoas = Arrays.asList(Pessoa.builder().id(1L).nome("Guilhermando").sobrenome("Júnior").build());
    public Optional<Pessoa> findById(Long id){
        return pessoas.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
    public void cadastra(Pessoa p){
        if (p.getId() > 0 || findById(p.getId()).isPresent()){
            throw new RuntimeException("Já Existe um cadastro de Pessoa!");
        }
        pessoas.add(p);
    }
    public void atualiza(Pessoa p){
        Optional<Pessoa> pessoaOp = findById(p.getId());
        if (p.getId() < 0 || !pessoaOp.isPresent()){
            throw new RuntimeException("Não existe pessoa com este cadastro");
        }
        Pessoa pessoa = pessoaOp.get();
        pessoa.setNome(p.getNome());
        pessoa.setSobrenome(p.getSobrenome());
        int indice = pessoas.indexOf(pessoaOp.get());
        pessoas.set(indice, pessoa);
    }
}
