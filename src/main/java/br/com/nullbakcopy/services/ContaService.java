package br.com.nullbakcopy.services;

import java.util.Objects;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.nullbakcopy.dtos.ContaDto;
import br.com.nullbakcopy.dtos.ContaDtoTran;
import br.com.nullbakcopy.enums.ActionType;
import br.com.nullbakcopy.enums.TransactionType;
import br.com.nullbakcopy.models.Conta;
import br.com.nullbakcopy.models.User;
import br.com.nullbakcopy.repositories.ContaRepository;

@Service
public class ContaService {

	@Autowired
	ContaRepository contaRepository;
	@Autowired
	TransactionService transactionService;

	/**
	 * Cria nova conta
	 * @param user
	 */
	@Transactional
	public void createConta (User user) {
		var conta = new Conta();
		conta.setUser(user);
		conta.setSaldo(0.00);
		contaRepository.save(conta);
	}

	/**
	 * 
	 * @param contaDto
	 * @return
	 */
	@Transactional
	public ResponseEntity<Object> deposit(@Valid ContaDto contaDto) {
		var conta = contaRepository.findByNumero(contaDto.getNumeroDaConta());
		
		if(Objects.nonNull(conta)) {
			conta.setSaldo(conta.getSaldo()+contaDto.getValor());
			conta = contaRepository.save(conta);
			transactionService.createTransactionValue(conta.getUser(), ActionType.UPDATE, TransactionType.DEPOSIT);
			
			return ResponseEntity.status(HttpStatus.OK).body(conta);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Conta não registrado no sistema");
		}
	}

	/**
	 * Saca valores pegando como referência o número da conta, observando se a conta existe e tem saldo suficiente.
	 * @param dto
	 * @return conta
	 */
	@Transactional
	public ResponseEntity<Object> withdraw(ContaDto contaDto) {
		var conta = contaRepository.findByNumero(contaDto.getNumeroDaConta());
		
		if(Objects.nonNull(conta)) {
			if(conta.getSaldo()>contaDto.getValor()) {
			conta.setSaldo(conta.getSaldo()-contaDto.getValor());
			transactionService
			.createTransactionValue(conta.getUser(), ActionType.UPDATE, TransactionType.WITHDRAW);
			return ResponseEntity.status(HttpStatus.OK).body(conta);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Saldo insuficiente. Operação não realizada");
			}
		} else {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("conta não registrada no sistema");
		}
	}

	/**
	 * Transfere valores entre conta 1 e conta 2 já existentes, observando se a conta existe e tem saldo suficiente.
	 * @param dto
	 * @return conta com saldo atualizado.
	 */
	
	@Transactional
	public ResponseEntity<Object> transfer(ContaDtoTran dto) {
		var conta = contaRepository.findByNumero(dto.getNumeroContaOrigem());
		var conta2 = contaRepository.findByNumero(dto.getNumeroContaDestino());
		
		if(Objects.nonNull(conta)) {
			if(conta.getSaldo()>dto.getValor()) {
				conta.setSaldo(conta.getSaldo()-dto.getValor());
				conta2.setSaldo(conta2.getSaldo()+dto.getValor());
				transactionService
				.createTransactionValue(conta.getUser(), ActionType.UPDATE, TransactionType.TRANSFER);
				transactionService
				.createTransactionValue(conta2.getUser(), ActionType.UPDATE, TransactionType.TRANSFER);
				return ResponseEntity.status(HttpStatus.OK).body(conta);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Saldo insuficiente. Operação não realizada");
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("conta não registrada no sistema");
		}
	}
	
	/**
	 * Deleta conta tendo como parâmetro o id do usuário.
	 * @param id
	 */
	@Transactional
	public void deleteContaByUser(Long id){
				contaRepository.deleteByUserId(id);
	}

}
