package br.com.nullbakcopy.repositories;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nullbakcopy.models.Conta;
import br.com.nullbakcopy.models.User;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

//	Conta findByNumero(Long numero);
//	boolean existByNumero(Long numero);
	
	Conta findByNumero(@NotNull Long numero);
	
	void deleteByUserId(Long id);
}
