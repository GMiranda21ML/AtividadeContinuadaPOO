package br.edu.cs.poo.ac.ordem.entidades;

public enum Dificuldade {
	NORMAL(1, "Normal"),
	DIFICIL(2, "Difícil");
	
	private int codigo;
	private String nome;
	
	private Dificuldade(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public static Dificuldade getDificuldade(int codigo) {
		for (Dificuldade dificuldade : Dificuldade.values()) {
			if (dificuldade.codigo == codigo) {
				return dificuldade;
			}
		}
		
		return null;
	}
}
