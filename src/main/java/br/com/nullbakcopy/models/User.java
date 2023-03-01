package br.com.nullbakcopy.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import br.com.nullbakcopy.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USUARIO")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
	private UUID id;
	
	@Column(nullable = false, length = 130, name = "nome")
	private String name;
	
	@Column(nullable = false, unique = true, length = 11, name = "cpf")
	private String cpf;
	
	@Column(nullable = false, name = "dh_criacao")
	private LocalDateTime dhCreation;
	
	@Column(nullable = false, name = "dh_modificacao")
	@Version
	private LocalDateTime dhModification;

//	public User (UserDto userDto) {
//		this.cpf = userDto.getCpf();
//		this.name = userDto.getName();
//		
//	}
}
