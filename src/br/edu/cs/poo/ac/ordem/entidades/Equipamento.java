package br.edu.cs.poo.ac.ordem.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Equipamento {
	private String descricao;
	private boolean ehNovo;
	private double valorEstimado;
	
}
