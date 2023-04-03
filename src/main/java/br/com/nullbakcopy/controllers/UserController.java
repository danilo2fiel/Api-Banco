package br.com.nullbakcopy.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nullbakcopy.dtos.UserDto;
import br.com.nullbakcopy.models.User;
import br.com.nullbakcopy.repositories.UserRepository;
import br.com.nullbakcopy.services.UserService;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
		
	@PostMapping("/createUser")
	public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto){
		return userService.saveUserDto(userDto);
	}
	
	
//	@GetMapping("/allUsers")
//    public ResponseEntity<List<User>> getAllUsers(){
//		 return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
//    }
	
	@GetMapping("/allUsers")
    public ResponseEntity<Page<User>> getAllUsers(@PageableDefault(size=10,sort= {"name"})Pageable paginacao){
		 return ResponseEntity.status(HttpStatus.OK)
				 .body(userRepository.findAll(paginacao));
    }
	
	@PostMapping("/createUserTest")
	public ResponseEntity<Object> createUserTest(){
		return userService.createUserTest();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUserById(@PathVariable Long id){
		return userService.deleteUserById(id);
	}
	
	@PutMapping("/updateUser")
	@ApiOperation(value = "Busca o usuário por cpf, e realiza a edição.")
	public ResponseEntity<Object> updateUser(@RequestBody @Valid UserDto userDto){
		return userService.updateUser(userDto);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> findUserById(@PathVariable Long id){
		return userService.findUserById(id);
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<Object> findUserByCpf(@PathVariable String cpf){
		return userService.findUserByCpf(cpf);
	}
}
