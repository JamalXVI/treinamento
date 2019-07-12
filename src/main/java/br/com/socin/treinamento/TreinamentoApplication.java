package br.com.socin.treinamento;

import br.com.socin.treinamento.terceiro.servico.LeitorDeRegexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TreinamentoApplication {
	@Autowired
	private LeitorDeRegexService leitorDeRegexService;

	public static void main(String[] args) {
		SpringApplication.run(TreinamentoApplication.class, args);

	}

}
