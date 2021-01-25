package br.com.treinaweb.jpa.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import br.com.treinaweb.jpa.models.Pessoa;
import br.com.treinaweb.jpa.services.interfaces.PessoaBuscaPorNome;
import br.com.treinaweb.jpa.utils.JpaUtils;

public class PessoaService implements PessoaBuscaPorNome {

	@Override
	public List<Pessoa> all() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManager();

			// Equivale a: SELECT * FROM pessoa; em linguagem SQL. 'Pessoa' escrita da forma
			// que foi criada a model
			pessoas = em.createQuery("FROM Pessoa", Pessoa.class).getResultList();
			return pessoas;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public Pessoa byId(Integer id) {
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManager();
			return em.find(Pessoa.class, id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public Pessoa insert(Pessoa entity) {
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManager();
			em.getTransaction().begin(); // Inicia transação no Banco de Dados
			em.persist(entity); // Persiste o objeto no Banco de Dados
			em.getTransaction().commit(); // Comita o objeto no Banco de Dados
			return entity; // Retorna o objeto gravado em Banco de Dados
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	// Utilização métodos JPA
	@Override
	public Pessoa update(Pessoa entity) {
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManager();
			em.getTransaction().begin();

			// Pega os valores da proprieda da entidade buscada pelo ID (processo do JPA) e
			// passa para uma entidade
			// anexada ao contexto da JPA
			// Faz SELECET antes de fazer a atualização
			em.merge(entity);

			em.getTransaction().commit();
			return entity;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	// Utilização métodos Hibernate
	@Override
	public Pessoa update2(Pessoa entity) {
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManager();
			em.getTransaction().begin();

			// Provê acesso direto a API do provider que está sendo usado
			// Precisa informar a classe a ser manipulada
			// Não faz SELECET antes de fazer a atualização
			em.unwrap(Session.class).update(entity);

			em.getTransaction().commit();
			return entity;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public void delete(Pessoa entity) {
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManager();
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManager();
			Pessoa pessoaASerDeletada = em.find(Pessoa.class, id);

			if (pessoaASerDeletada != null) {
				em.getTransaction().begin();
				em.remove(pessoaASerDeletada);
				em.getTransaction().commit();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	// Utiizando API JPQL -> exige maior digitação SQL
	@Override
	public List<Pessoa> searchByName(String name) {
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManager();

			// Passar o nome da classe na consulta SQL (escrever igual a forma como a classe
			// foi escrita)
			// O atributo a ser passado no WHERE é o mesmo declarado na classe Pessoa
			// :nome -> parametro a ser passado na consulta SQL
			List<Pessoa> pessoas = em
					.createQuery("FROM Pessoa p WHERE lower(p.nome) LIKE lower(concat('%', :nome, '%'))", Pessoa.class)
					.setParameter("nome", name).getResultList();
			return pessoas;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	// Utiizando CRITERIA -> Instrução SQL escrita de forma mais declarativa
	// Sem risco de errar a sintaxe na hora de escrever
	@Override
	public List<Pessoa> searchByName2(String name) {
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManager();

			// Construtor CRITERIA
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

			// Representa a consulta a ser realizadae e a classe a ser manipulada
			CriteriaQuery<Pessoa> buscaPorNomeCriteria = criteriaBuilder.createQuery(Pessoa.class);

			// Raiz da consulta
			Root<Pessoa> raiz = buscaPorNomeCriteria.from(Pessoa.class);
			
			buscaPorNomeCriteria.where(criteriaBuilder.like(criteriaBuilder.lower(raiz.get("nome")), "%" + name.toLowerCase() + "%"));

			List<Pessoa> pessoas = em.createQuery(buscaPorNomeCriteria).getResultList();
			
			return pessoas;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
}
