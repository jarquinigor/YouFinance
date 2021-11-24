package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Portfolio")
public class Portfolio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPortfolio;
	
	@Column(name="namePortfolio", nullable=false, length=100)
	private String namePortfolio;
	
	@Column(name="valorTotalRecibidoPortfolio", nullable=true, length=100) //NULLABLE
	private double valorTotalRecibidoPortfolio;
	
	@Column(name="tceaPortfolio", nullable=true, length=100) //NULLABLE
	private double tceaPortfolio;
	
	@Temporal(TemporalType.DATE)
	@Column(name="descuentoPortfolio")
	@DateTimeFormat(pattern="yyyy-MM-dd")//(1)
	private Date descuentoPortfolio;
	
	@ManyToOne
	@JoinColumn(name = "idUser", nullable = false)
	private Users user;
	
	@ManyToOne
	@JoinColumn(name = "idMovementType", nullable = false)
	private MovementType movementType;
	
	@ManyToOne
	@JoinColumn(name = "idCurrency", nullable = false)
	private Currency currency;
	
	public Portfolio() {
		super();
		tceaPortfolio = 0.0;
		valorTotalRecibidoPortfolio = 0.0;
	}

	public Portfolio(int idPortfolio, String namePortfolio, double valorTotalRecibidoPortfolio, double tceaPortfolio,
			Date descuentoPortfolio, Users user, MovementType movementType, Currency currency) {
		super();
		this.idPortfolio = idPortfolio;
		this.namePortfolio = namePortfolio;
		this.valorTotalRecibidoPortfolio = valorTotalRecibidoPortfolio;
		this.tceaPortfolio = tceaPortfolio;
		this.descuentoPortfolio = descuentoPortfolio;
		this.user = user;
		this.movementType = movementType;
		this.currency = currency;
	}

	public int getIdPortfolio() {
		return idPortfolio;
	}

	public void setIdPortfolio(int idPortfolio) {
		this.idPortfolio = idPortfolio;
	}

	public String getNamePortfolio() {
		return namePortfolio;
	}

	public void setNamePortfolio(String namePortfolio) {
		this.namePortfolio = namePortfolio;
	}

	public double getValorTotalRecibidoPortfolio() {
		return valorTotalRecibidoPortfolio;
	}

	public void setValorTotalRecibidoPortfolio(double valorTotalRecibidoPortfolio) {
		this.valorTotalRecibidoPortfolio = valorTotalRecibidoPortfolio;
	}

	public double getTceaPortfolio() {
		return tceaPortfolio;
	}

	public void setTceaPortfolio(double tceaPortfolio) {
		this.tceaPortfolio = tceaPortfolio;
	}

	public Date getDescuentoPortfolio() {
		return descuentoPortfolio;
	}

	public void setDescuentoPortfolio(Date descuentoPortfolio) {
		this.descuentoPortfolio = descuentoPortfolio;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public MovementType getMovementType() {
		return movementType;
	}

	public void setMovementType(MovementType movementType) {
		this.movementType = movementType;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
