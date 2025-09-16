package br.edu.cs.poo.ac.ordem.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
		LocalDate dataAbertura = this.dataHoraAbertura.toLocalDate();
		
		return dataAbertura.plusDays(this.prazoEmDias);
	}
	
	public String getNumero() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMyyyyddHHmm");
		
		String dataFormatada = this.dataHoraAbertura.format(dateTimeFormatter);
		
		if (cliente.getCpfCnpj().length() == 15) {
			return dataFormatada + this.cliente.getCpfCnpj();
		} else {
			return dataFormatada + "000" + this.cliente.getCpfCnpj();
		}
	}
}
