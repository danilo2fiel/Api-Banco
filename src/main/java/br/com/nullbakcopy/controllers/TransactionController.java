package br.com.nullbakcopy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nullbakcopy.models.Transaction;
import br.com.nullbakcopy.models.User;
import br.com.nullbakcopy.repositories.TransactionRepository;
import br.com.nullbakcopy.repositories.UserRepository;
import br.com.nullbakcopy.services.TransactionService;
import br.com.nullbakcopy.services.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	TransactionService transactionService;
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	UserService userService;
	
	@GetMapping
	public List<Transaction> allTransactions(){
		return transactionRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> allTransactionsByUser(@PathVariable Long id){
		return transactionService.allTransactionsByUser(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable Long id){
		return transactionService.deleteTransacao(id);
	}
	
	@DeleteMapping("/all{id}")
	public ResponseEntity<Object> deleteTransacoesUsuario(@PathVariable Long id){
		return transactionService.deleteTransacoesUsuario(id);
	}
	
//	@GetMapping("/{id}")
//	public ResponseEntity<Object> AllTransactionsByUser(@PathVariable Long id){
//		return userService.findUserById(id);
//	}
}
