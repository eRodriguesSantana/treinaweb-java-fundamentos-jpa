package br.com.treinaweb.jpa.services.interfaces;

import java.util.List;

public interface CrudService<T, K> {

	// Métodos CRUD
	
	// Select que retorna uma lista de todos os registros do tipo T (classe)
	List<T> all();
	
	// Select que retorna um elemento de acordo com o K (id)
	T byId(K id);
	
	// Insert de registro
	T insert(T entity);
	
	// Update de registro
	T update(T entity);
	
	// Deleta uma entidade
	void delete(T entity);
	
	// Deleta uma entidade pelo ID (K)
	void deleteById(K id);
}
