package br.edu.cs.poo.ac.ordem.entidades;

import java.time.LocalDate;

public class Cliente {
	private String cpfCnpj;
	private String nome;
	private Contato contato;
	private LocalDate dataCadastro;
	
	public Cliente(String cpfCnpj, String nome, Contato contato, LocalDate dataCadastro) {
		this.cpfCnpj = cpfCnpj;
		this.nome = nome;
		this.contato = contato;
		this.dataCadastro = dataCadastro;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	public Contato getContato() {
		return this.contato;
	}
	
	public String getCpfCnpj() {
		return this.cpfCnpj;
	}
	
	public LocalDate getDataCadastro() {
		return this.dataCadastro;
	}
	
	public int getIdadeCadastro() {
		return LocalDate.now().getYear() - dataCadastro.getYear();
	}
	
	
	
	
}
