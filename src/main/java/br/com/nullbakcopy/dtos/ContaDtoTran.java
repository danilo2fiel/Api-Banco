package br.com.nullbakcopy.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaDtoTran {

	private Long NumeroContaOrigem;
	private Double valor;
	private Long NumeroContaDestino;
}
