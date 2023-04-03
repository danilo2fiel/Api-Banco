package br.com.nullbakcopy.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	@NotBlank(message = "Nome é obrigatório.")
	@Size(max = 130, message = "Máximo de 130 caracteres.")
	@NotNull
	private String name;
	
	
	@NotBlank(message = "CPF é obrigatório.")
    @Size(max = 11, min = 11, message = "O CPF deve conter no mínimo 11 e máximo 11 caracteres, digite apenas números.")
    @NotNull
    @CPF(message = "CPF inválido")
	private String cpf;
	
	
}
