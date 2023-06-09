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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
public class User implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, name = "use_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false, length = 130, name = "use_nome")
	private String name;

	@Column(nullable = false, unique = true, length = 11, name = "use_cpf")
	private String cpf;
	
	@Column(nullable = false, name = "use_dh_criacao" )
    private LocalDateTime dhCreation;
	
    @Column(nullable = false, name = "use_dh_modificacao")
    @Version
    private LocalDateTime dhModification;

	public User(UserDto user) {
		this.name = user.getName();
		this.cpf = user.getCpf();
		
	}
    
   
    
}
