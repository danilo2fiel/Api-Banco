package br.com.nullbakcopy.repositories;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nullbakcopy.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

	boolean existsById(UUID id);
	
	boolean existsByCpf(String cpf);
	
	boolean existsByName(String name);


}
