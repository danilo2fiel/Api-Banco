package br.com.nullbakcopy.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import br.com.nullbakcopy.enums.ActionType;
import br.com.nullbakcopy.enums.TransactionType;
import br.com.nullbakcopy.models.Transaction;
import br.com.nullbakcopy.models.User;
import br.com.nullbakcopy.repositories.TransactionRepository;
import br.com.nullbakcopy.repositories.UserRepository;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;
	
	@Transactional
	public void createTransaction(User user, ActionType type) {
		var transaction = new Transaction();
		transaction.setDhTransaction(LocalDateTime.now(ZoneId.of("UTC-3")));
		transaction.setUser(user);
		transaction.setActionType(type);
		transactionRepository.save(transaction);
	}
	
	@Transactional
	public void createTransactionValue(User user, ActionType type, TransactionType tran) {
		var transaction = new Transaction();
		transaction.setDhTransaction(LocalDateTime.now(ZoneId.of("UTC-3")));
		transaction.setUser(user);
		transaction.setActionType(type);
		transaction.setTransactionType(tran);
		transactionRepository.save(transaction);
	}
	
	public ResponseEntity<Object> allTransactionsByUser(Long id){
		var tran = transactionRepository.findByUserId(id);
		if(tran.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id incorreto");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(tran);
		}
	}
	
	@Transactional
	public ResponseEntity<Object> deleteTransacao(Long id){
		var tran = transactionRepository.findById(id);
		if(tran.isPresent()) {
			transactionRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("deletado");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("transação não existe nos registros");
		}
	}
	
	@Transactional
	public ResponseEntity<Object> deleteTransacoesUsuario(Long id){
		var tran = transactionRepository.findByUserId(id);
		if(tran.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("transação não existe nos registros");
		} else {
			
			transactionRepository.deleteAllByUserId(id);
			return ResponseEntity.status(HttpStatus.OK).body("Transações do usuário deletadas");
			
		}
	}
	
}
