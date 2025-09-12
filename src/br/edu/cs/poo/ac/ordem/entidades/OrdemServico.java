package br.edu.cs.poo.ac.ordem.entidades;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdemServico {
	private Cliente cliente;
	private PrecoBase precoBase;
	private Notebook notebook;
	private Desktop desktop;
	private LocalDateTime dataHoraAbertura;
	private int prazoEmDias;
	private double valor;

	public LocalDate getDataEstimadaEntrega() {
		return dataHoraAbertura.getMinute() + dataHoraAbertura.getSecond() + prazoEmDias;
	}
}
