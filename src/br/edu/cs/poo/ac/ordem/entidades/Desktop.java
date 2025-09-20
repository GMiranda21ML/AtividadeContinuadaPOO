package br.edu.cs.poo.ac.ordem.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Desktop extends Equipamento {
	private boolean ehServidor;

	public Desktop(String serial, String descricao, boolean ehNovo, double valorEstimado, boolean ehServidor) {
		super();
		this.ehServidor = ehServidor;
	}
	
	public String getIdTipo() {
		return "NE";
	}
}
