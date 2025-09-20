  package br.edu.cs.poo.ac.ordem.entidades;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FechamentoOrdemServico implements Serializable {
	private String numeroOrdemServico;
	private LocalDate dataFechamento;
	private boolean pago;
	private String relatorioFinal;
}
