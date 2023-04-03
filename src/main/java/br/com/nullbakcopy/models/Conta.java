package br.com.nullbakcopy.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="CONTA")
public class Conta {

	@Id
	@Column(name = "con_numero")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long numero;
	
	@Digits(integer = 10, fraction = 2)
    @Column(name = "con_saldo")
	private Double saldo;
	
	@OneToOne
	@JoinColumn(name = "con_use_id", nullable = false)
	private User user;
	
}
