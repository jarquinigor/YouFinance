package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CurrencyExchange")
public class CurrencyExchange implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCurrencyExchange;
	
	@ManyToOne
	@JoinColumn(name = "idCurrencyForeign", nullable = false)
	private Currency currencyForeign;
	
	@ManyToOne
	@JoinColumn(name = "idCurrencyLocal", nullable = false)
	private Currency currencyLocal;
	
	@Column(name="nameEstablishment", nullable=false, length=100)
	private double amountCurrencyExchange;

	public CurrencyExchange() {
		super();
	}

	public CurrencyExchange(int idCurrencyExchange, Currency currencyForeign, Currency currencyLocal,
			double amountCurrencyExchange) {
		super();
		this.idCurrencyExchange = idCurrencyExchange;
		this.currencyForeign = currencyForeign;
		this.currencyLocal = currencyLocal;
		this.amountCurrencyExchange = amountCurrencyExchange;
	}

	public int getIdCurrencyExchange() {
		return idCurrencyExchange;
	}

	public void setIdCurrencyExchange(int idCurrencyExchange) {
		this.idCurrencyExchange = idCurrencyExchange;
	}

	public Currency getCurrencyForeign() {
		return currencyForeign;
	}

	public void setCurrencyForeign(Currency currencyForeign) {
		this.currencyForeign = currencyForeign;
	}

	public Currency getCurrencyLocal() {
		return currencyLocal;
	}

	public void setCurrencyLocal(Currency currencyLocal) {
		this.currencyLocal = currencyLocal;
	}

	public double getAmountCurrencyExchange() {
		return amountCurrencyExchange;
	}

	public void setAmountCurrencyExchange(double amountCurrencyExchange) {
		this.amountCurrencyExchange = amountCurrencyExchange;
	}
}
