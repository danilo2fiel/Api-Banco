package br.com.nullbakcopy.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.nullbakcopy.dtos.UserDto;
import br.com.nullbakcopy.enums.ActionType;
import br.com.nullbakcopy.models.Conta;
import br.com.nullbakcopy.models.User;
import br.com.nullbakcopy.repositories.ContaRepository;
import br.com.nullbakcopy.repositories.TransactionRepository;
import br.com.nullbakcopy.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	TransactionService transactionalservice;
	@Autowired
	ContaService contaService;
	
	/**
	 * Salva um novo usuário já com uma nova conta. Verifica a existência de cpf ja cadastrado.
	 * @param userDto
	 * @return User
	 */
	@Transactional
	public ResponseEntity<Object> saveUserDto(@Valid UserDto userDto) {
		if(!userRepository.existsByCpf(userDto.getCpf())) {
			var user = resolveAndReturnUser(userDto);
			user = userRepository.save(user);
			contaService.createConta(user);
			transactionalservice.createTransaction(user, ActionType.CREATE);
			return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("CPF already registered in use to system!");
			
		}
	}
	
	/**
	 * Deleta todos os dados do usuário, incluindo sua conta e as transações realizadas.
	 * @param id
	 * @return void
	 */
	@Transactional
	public ResponseEntity<Object> deleteUserById(Long id) {
		 var user = userRepository.findById(id);
		 
		 if(userRepository.existsById(id)) {
			 contaService.deleteContaByUser(id);
			 transactionalservice.deleteTransacoesUsuario(id);
			 userRepository.deleteById(id);
			 return ResponseEntity.status(HttpStatus.OK)
						.body("Usuário, conta e transações deletadas!");
		 } else {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Id não encontrado");
		 }
	}
	
	@Transactional
	public ResponseEntity<Object> updateUser(@Valid UserDto userDto) {
		var user = userRepository.findByCpf(userDto.getCpf());
		
		if(userRepository.existsByCpf(userDto.getCpf())) {
			user.setName(userDto.getName().toUpperCase());
			user = userRepository.save(user);
			transactionalservice.createTransaction(user, ActionType.UPDATE);
			return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Cpf não registrado no sistema");
		}
	}

//	public ResponseEntity<Object> findUserById(String id) {
//		Optional<User> user = userRepository.findById(id);
//		this.createTransaction(user.get(), ActionType.VIEW);
//		return Objects.nonNull(user.get()) ? 
//				ResponseEntity.status(HttpStatus.OK).body(user)
//				: 
//				ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não existe!");
//	}

	//-----4 T
	public ResponseEntity<Object> findUserByCpf(String cpf) {
		var user = userRepository.findByCpf(cpf);
		transactionalservice.createTransaction(user,ActionType.VIEW);
		return Objects.nonNull(user) ? 
				ResponseEntity.status(HttpStatus.OK).body(user) 
				: 
				ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não existe!");
	}
	
	/**
	 * Recupera um usuário a partir de um Id
	 * @param id
	 * @return User
	 */
	public ResponseEntity<Object> findUserById(Long id)  {
		var user = userRepository.findById(id);
		
		if(userRepository.existsById(id)) {
			transactionalservice.createTransaction(user.get(),ActionType.VIEW);
			return ResponseEntity.status(HttpStatus.OK).body(user.get());
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id não existe!");
		}
		
	}

	private @NotNull User resolveAndReturnUser(UserDto userDto) {
        var user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setName(user.getName().toUpperCase());
        user.setDhCreation(LocalDateTime.now(ZoneId.of("UTC-3")));
        user.setDhModification(LocalDateTime.now(ZoneId.of("UTC-3")));
        return user;
    }
	
	@Transactional
	public ResponseEntity<Object> createUserTest() {
		var user = new User();
		
		user.setCpf("01923137204");
		user.setName("TESTE");
        user.setDhCreation(LocalDateTime.now(ZoneId.of("UTC-3")));
        user.setDhModification(LocalDateTime.now(ZoneId.of("UTC-3")));
        
        user = userRepository.save(user);
        
        contaService.createConta(user);
        
        transactionalservice.createTransaction(user, ActionType.CREATE);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	
//	private String createUUIDToString() {
//		return UUID.randomUUID().toString();
//	}
	
}
