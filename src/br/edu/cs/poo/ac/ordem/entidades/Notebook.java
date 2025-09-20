package br.edu.cs.poo.ac.ordem.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Notebook extends Equipamento {
	private boolean carregaDadosSensiveis;

	public Notebook(boolean carregaDadosSensiveis) {
		super();
		this.carregaDadosSensiveis = carregaDadosSensiveis;
	}
	
	public String getIdTipo() {
		return "NO";
	}
}


