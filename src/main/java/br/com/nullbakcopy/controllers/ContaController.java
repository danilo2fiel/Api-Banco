package br.com.nullbakcopy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nullbakcopy.dtos.ContaDto;
import br.com.nullbakcopy.dtos.ContaDtoTran;
import br.com.nullbakcopy.models.Conta;
import br.com.nullbakcopy.repositories.ContaRepository;
import br.com.nullbakcopy.services.ContaService;
import br.com.nullbakcopy.services.UserService;

@RestController
@CrossOrigin (origins = "*", maxAge = 3600)
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	ContaRepository contaRepository;
	@Autowired
	ContaService contaService;
	
	@GetMapping
	public ResponseEntity<List<Conta>> listAllContas(){
		return ResponseEntity.status(HttpStatus.OK).body(contaRepository.findAll());
	}
	
	@PutMapping("/deposito")
	public ResponseEntity<Object> deposit(@RequestBody ContaDto dto){
		return contaService.deposit(dto);
	}
	
	@PutMapping("/sacar")
	public ResponseEntity<Object> withdraw(@RequestBody ContaDto dto){
		return contaService.withdraw(dto);
	}
	
	@PutMapping("/transferir")
	public ResponseEntity<Object> transfer(@RequestBody ContaDtoTran dto){
		return contaService.transfer(dto);
	}
}
