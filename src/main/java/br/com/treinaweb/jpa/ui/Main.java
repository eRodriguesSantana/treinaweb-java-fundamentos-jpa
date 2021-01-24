package br.com.treinaweb.jpa.ui;

import java.util.List;

import br.com.treinaweb.jpa.models.Pessoa;
import br.com.treinaweb.jpa.services.impl.PessoaService;
import br.com.treinaweb.jpa.services.interfaces.CrudService;

public class Main {

	public static void main(String[] args) {
		CrudService<Pessoa, Integer> pessoaService = new PessoaService();

		System.out.println("*** Gerenciamento de pessoas ***");
		System.out.println("Lista de pessoas cadastradas.\n");

		try {
			List<Pessoa> pessoas = pessoaService.all();
			pessoas.forEach(pessoa -> {
				System.out.println(String.format(" - (%d) %s %s - %d ano(s)", pessoa.getId(), pessoa.getNome(),
						pessoa.getSobrenome(), pessoa.getIdade()));
			});
			
			if(pessoas.isEmpty()) {
				System.out.println("Não existe(m) pessoa(s) cadastrada(s).");
			}
		} catch (Exception e) {
			System.out.println("Houve um erro ao utilizar a JPA: " + e.getMessage());
		}

	}

}
