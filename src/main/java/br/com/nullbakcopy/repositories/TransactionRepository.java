package br.com.nullbakcopy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nullbakcopy.models.Transaction;
import br.com.nullbakcopy.models.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	List<Object> findByUserId(Long id);
	
	void deleteAllByUserId(Long id);


}
