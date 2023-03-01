package br.com.nullbakcopy.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	@NotBlank(message = "Nome é obrigatório")
	@Size(max = 130, message = "máximo de 130 caracteres")
	@NotNull
	private String name;
	
	@NotBlank(message = "Nome é obrigatório")
	@Size(max = 11, min = 11, message = "O cpf deve conter 11 carcteres, digite apenas números")
	@NotNull
	@CPF(message = "CPF inválido")
	private String cpf;
	
}
