package br.com.treinaweb.jpa.ui;

import java.util.List;
import java.util.Scanner;

import br.com.treinaweb.jpa.models.Pessoa;
import br.com.treinaweb.jpa.services.impl.PessoaService;
import br.com.treinaweb.jpa.services.interfaces.CrudService;

public class Main {

	public static void main(String[] args) {
		int opcao = 0;

		System.out.println("*** Gerenciamento de pessoas ***");

		listarPessoas();

		while (opcao != 6) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("\nEscolha uma ação:");
			System.out.println("1. Listar pessoas");
			System.out.println("2. Inserir pessoa");
			System.out.println("3. Atualizar pessoa");
			System.out.println("4. Excluir pessoa");
			System.out.println("5. Pesquisar pessoa por nome");
			System.out.println("6. Sair");

			opcao = scanner.nextInt();
			switch (opcao) {
			case 1:
				listarPessoas();
				break;
			case 2:
				inserirPessoa();
				break;
			default:
				break;
			}
		}
		System.out.println("Tchau :)");
	}

	private static void inserirPessoa() {
		System.out.println("Cadastro de pessoa");
		Scanner scanner = new Scanner(System.in);
		
		Pessoa novaPessoa = new Pessoa();
		System.out.println("Nome: ");
		novaPessoa.setNome(scanner.nextLine());
		System.out.println("Sobrenome: ");
		novaPessoa.setSobrenome(scanner.nextLine());
		System.out.println("Idade: ");
		novaPessoa.setIdade(scanner.nextInt());
		
		scanner.close();		

		CrudService<Pessoa, Integer> pessoaService = new PessoaService();
		pessoaService.insert(novaPessoa);
		System.out.println("Cadastro realizado com sucesso :)");

	}

	private static void listarPessoas() {
		CrudService<Pessoa, Integer> pessoaService = new PessoaService();

		System.out.println("Lista de pessoas cadastradas.\n");

		try {
			List<Pessoa> pessoas = pessoaService.all();
			pessoas.forEach(pessoa -> {
				System.out.println(String.format(" - (%d) %s %s - %d ano(s)", pessoa.getId(), pessoa.getNome(),
						pessoa.getSobrenome(), pessoa.getIdade()));
			});

			if (pessoas.isEmpty()) {
				System.out.println("Não existe(m) pessoa(s) cadastrada(s).");
			}
		} catch (Exception e) {
			System.out.println("Houve um erro ao utilizar a JPA: " + e.getMessage());
		}
	}

}
