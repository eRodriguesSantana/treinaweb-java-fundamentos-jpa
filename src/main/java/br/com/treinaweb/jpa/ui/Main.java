package br.com.treinaweb.jpa.ui;

import java.util.List;
import java.util.Scanner;

import br.com.treinaweb.jpa.models.Pessoa;
import br.com.treinaweb.jpa.services.impl.PessoaService;
import br.com.treinaweb.jpa.services.interfaces.CrudService;

public class Main {

	private static Scanner SCANNER = new Scanner(System.in);

	public static void main(String[] args) {
		int opcao = 0;

		System.out.println("*** Gerenciamento de pessoas ***");

		listarPessoas();

		while (opcao != 6) {
			System.out.println("\nEscolha uma ação:");
			System.out.println("1. Listar pessoas");
			System.out.println("2. Inserir pessoa");
			System.out.println("3. Atualizar pessoa");
			System.out.println("4. Excluir pessoa");
			System.out.println("5. Pesquisar pessoa por nome");
			System.out.println("6. Sair");
			System.out.print("Sua opção: ");
			opcao = SCANNER.nextInt();
			SCANNER.nextLine();
			switch (opcao) {
			case 1:
				listarPessoas();
				break;
			case 2:
				inserirPessoa();
				break;
			default:
				System.out.println("*** Opção inválida ***");
				break;
			}
		}
		System.out.println("Tchau :)");
	}

	private static void inserirPessoa() {
		System.out.println("Cadastro de pessoa");

		Pessoa novaPessoa = new Pessoa();
		System.out.print("Nome: ");
		novaPessoa.setNome(SCANNER.nextLine());
		System.out.print("Sobrenome: ");
		novaPessoa.setSobrenome(SCANNER.nextLine());
		System.out.print("Idade: ");
		novaPessoa.setIdade(SCANNER.nextInt());
		
		CrudService<Pessoa, Integer> pessoaService = new PessoaService();
		Pessoa pessoa = pessoaService.insert(novaPessoa);
		System.out.println("ID -> " + pessoa.getId());
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
