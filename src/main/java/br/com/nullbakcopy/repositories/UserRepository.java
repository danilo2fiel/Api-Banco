package br.com.nullbakcopy.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nullbakcopy.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	boolean existsById(Long idUser);
	
	boolean existsByCpf(String cpf);
	
	User findByCpf(String cpf);

	
	
	
}
