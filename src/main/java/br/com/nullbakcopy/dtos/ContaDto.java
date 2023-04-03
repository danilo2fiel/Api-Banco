package br.com.nullbakcopy.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaDto {

	private Double valor;
	private Long numeroDaConta;
//	private UserDto user;
	
	
}
