package br.com.treinaweb.jpa.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "PES_PESSOAS")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PES_ID")
	private int id;
	
	@Column(name = "PES_NOME", nullable = false, length = 20)
	private String nome;
	
	@Column(name = "PES_SOBRENOME", nullable = false, length = 30)
	private String sobrenome;
	
	@Column(name = "PES_IDADE", nullable = false)
	private int idade;
}
