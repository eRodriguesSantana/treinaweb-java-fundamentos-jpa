package br.com.treinaweb.jpa.services.interfaces;

import java.util.List;

import br.com.treinaweb.jpa.models.Pessoa;

public interface PessoaBuscaPorNome extends CrudService<Pessoa, Integer>{

	// Utiizando JPQL -> exige maior digitação SQL
	List<Pessoa> searchByName(String name);
	
	// Utiizando CRITERIA -> Instrução SQL escrita de forma mais declarativa
	List<Pessoa> searchByName2(String name);
}
