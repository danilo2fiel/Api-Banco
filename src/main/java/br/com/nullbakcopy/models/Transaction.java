package br.com.nullbakcopy.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.DynamicInsert;

import br.com.nullbakcopy.enums.ActionType;
import br.com.nullbakcopy.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TRANSACAO")
public class Transaction implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(nullable = false, name = "tra_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false, name = "tra_dh_transacao")
	private LocalDateTime dhTransaction;
	
	@Digits(integer = 10, fraction = 2)
    @Column(name = "tra_valor")
	private BigDecimal value;
	
	@OneToOne
	@JoinColumn(name = "tra_use_id", nullable = false)
	private User user;
	
	@Column(nullable = false, name = "tra_tipo_acao")
    @Enumerated(EnumType.STRING)
	private ActionType actionType;
	
	@Column(name = "tra_tipo_transacao")
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;

}
