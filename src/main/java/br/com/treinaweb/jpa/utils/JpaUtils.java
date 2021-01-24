package br.com.treinaweb.jpa.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtils {

	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = null;
	
	public static EntityManager getEntityManager() {
		if(ENTITY_MANAGER_FACTORY == null) {
			
			// É passado para createEntityManagerFactory() o nome da unidade de persistencia (persistence-unit)
			// configurado no arquivo persistence.xml
			ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("treinaweb-jpa-fundamentos");
		}
		
		// Retorna uma criação de acesso ao Banco de Dados
		return ENTITY_MANAGER_FACTORY.createEntityManager();
	}
}
