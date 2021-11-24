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
@Table(name = "Movement")
public class Movement implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//(1)
	private int idMovement;
	
	@ManyToOne
	@JoinColumn(name = "idPortfolio", nullable = false)//(1)
	private Portfolio portfolio;
	
	@ManyToOne
	@JoinColumn(name = "idEstablishment", nullable = false)//(1)
	private Establishment establishment;
	
	@Temporal(TemporalType.DATE)
	@Column(name="emisionMovement")
	@DateTimeFormat(pattern="yyyy-MM-dd")//(1)
	private Date emisionMovement;
	
	@Temporal(TemporalType.DATE)
	@Column(name="vencimientoMovement")
	@DateTimeFormat(pattern="yyyy-MM-dd")//(1)
	private Date vencimientoMovement;
	
	@Column(name="valorNominalMovement", nullable = false)//(1)
	private double valorNominalMovement;
	
	@Column(name="retencionMovement", nullable = false)//(1)
	private double retencionMovement;
	
	@Column(name="diasAnioMovement", nullable = true)//(2)
	private int diasAnioMovement;
	
	@ManyToOne
	@JoinColumn(name = "idRateTerm", nullable = true)//(2)
	private RateTerm rateTerm;
	
	@Column(name="diasPlazoTasaMovement", nullable = true)//(2)   ESPECIAL
	private int diasPlazoTasaMovement;
	
	@ManyToOne
	@JoinColumn(name = "idRateType", nullable = true)//(2)
	private RateType rateType;
	
	@ManyToOne
	@JoinColumn(name = "idCapitalization", nullable = true)//(2)
	private Capitalization capitalization;
	
	@Column(name="diasCapitalizacionTasaMovement", nullable = true)//(2)  ESPECIAL
	private int diasCapitalizacionTasaMovement;
	
	@Column(name="percentTasaMovement", nullable = true)//(2)
	private double percentTasaMovement;
	
	
	//RESULTADOS  (3)
	@Column(name="cantidadDiasMovement", nullable = true)//(3) 
	private int cantidadDiasMovement;
	
	@Column(name="tasaEfectivaPeriodoMovement", nullable = true)//(3) 
	private double tasaEfectivaPeriodoMovement;
	
	@Column(name="tasaDescuentoPeriodoMovement", nullable = true)//(3) 
	private double tasaDescuentoPeriodoMovement;
	
	@Column(name="costoInicialMovement", nullable = true)//(3) 
	private double costoInicialMovement;
	
	@Column(name="costoFinalMovement", nullable = true)//(3) 
	private double costoFinalMovement;
	
	@Column(name="descuentoMovement", nullable = true)//(3) 
	private double descuentoMovement;
	
	@Column(name="valorNetoMovement", nullable = true)//(3) 
	private double valorNetoMovement;
	
	@Column(name="valorRecibido", nullable = true)//(3) 
	private double valorRecibidoMovement;
	
	@Column(name="valorEntregado", nullable = true)//(3) 
	private double valorEntregadoMovement;
	
	@Column(name="tceaMovement", nullable = true)//(3) 
	private double tceaMovement;

	public Movement() {
		super();
	}

	public Movement(int idMovement, Portfolio portfolio, Establishment establishment, Date emisionMovement,
			Date vencimientoMovement, double valorNominalMovement, double retencionMovement, int diasAnioMovement,
			RateTerm rateTerm, int diasPlazoTasaMovement, RateType rateType, Capitalization capitalization,
			int diasCapitalizacionTasaMovement, double percentTasaMovement, int cantidadDiasMovement,
			double tasaEfectivaPeriodoMovement, double tasaDescuentoPeriodoMovement, double costoInicialMovement,
			double costoFinalMovement, double descuentoMovement, double valorNetoMovement, double valorRecibidoMovement,
			double valorEntregadoMovement, double tceaMovement) {
		super();
		this.idMovement = idMovement;
		this.portfolio = portfolio;
		this.establishment = establishment;
		this.emisionMovement = emisionMovement;
		this.vencimientoMovement = vencimientoMovement;
		this.valorNominalMovement = valorNominalMovement;
		this.retencionMovement = retencionMovement;
		this.diasAnioMovement = diasAnioMovement;
		this.rateTerm = rateTerm;
		this.diasPlazoTasaMovement = diasPlazoTasaMovement;
		this.rateType = rateType;
		this.capitalization = capitalization;
		this.diasCapitalizacionTasaMovement = diasCapitalizacionTasaMovement;
		this.percentTasaMovement = percentTasaMovement;
		this.cantidadDiasMovement = cantidadDiasMovement;
		this.tasaEfectivaPeriodoMovement = tasaEfectivaPeriodoMovement;
		this.tasaDescuentoPeriodoMovement = tasaDescuentoPeriodoMovement;
		this.costoInicialMovement = costoInicialMovement;
		this.costoFinalMovement = costoFinalMovement;
		this.descuentoMovement = descuentoMovement;
		this.valorNetoMovement = valorNetoMovement;
		this.valorRecibidoMovement = valorRecibidoMovement;
		this.valorEntregadoMovement = valorEntregadoMovement;
		this.tceaMovement = tceaMovement;
	}

	public int getIdMovement() {
		return idMovement;
	}

	public void setIdMovement(int idMovement) {
		this.idMovement = idMovement;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public Establishment getEstablishment() {
		return establishment;
	}

	public void setEstablishment(Establishment establishment) {
		this.establishment = establishment;
	}

	public Date getEmisionMovement() {
		return emisionMovement;
	}

	public void setEmisionMovement(Date emisionMovement) {
		this.emisionMovement = emisionMovement;
	}

	public Date getVencimientoMovement() {
		return vencimientoMovement;
	}

	public void setVencimientoMovement(Date vencimientoMovement) {
		this.vencimientoMovement = vencimientoMovement;
	}

	public double getValorNominalMovement() {
		return valorNominalMovement;
	}

	public void setValorNominalMovement(double valorNominalMovement) {
		this.valorNominalMovement = valorNominalMovement;
	}

	public double getRetencionMovement() {
		return retencionMovement;
	}

	public void setRetencionMovement(double retencionMovement) {
		this.retencionMovement = retencionMovement;
	}

	public int getDiasAnioMovement() {
		return diasAnioMovement;
	}

	public void setDiasAnioMovement(int diasAnioMovement) {
		this.diasAnioMovement = diasAnioMovement;
	}

	public RateTerm getRateTerm() {
		return rateTerm;
	}

	public void setRateTerm(RateTerm rateTerm) {
		this.rateTerm = rateTerm;
	}

	public int getDiasPlazoTasaMovement() {
		return diasPlazoTasaMovement;
	}

	public void setDiasPlazoTasaMovement(int diasPlazoTasaMovement) {
		this.diasPlazoTasaMovement = diasPlazoTasaMovement;
	}

	public RateType getRateType() {
		return rateType;
	}

	public void setRateType(RateType rateType) {
		this.rateType = rateType;
	}

	public Capitalization getCapitalization() {
		return capitalization;
	}

	public void setCapitalization(Capitalization capitalization) {
		this.capitalization = capitalization;
	}

	public int getDiasCapitalizacionTasaMovement() {
		return diasCapitalizacionTasaMovement;
	}

	public void setDiasCapitalizacionTasaMovement(int diasCapitalizacionTasaMovement) {
		this.diasCapitalizacionTasaMovement = diasCapitalizacionTasaMovement;
	}

	public double getPercentTasaMovement() {
		return percentTasaMovement;
	}

	public void setPercentTasaMovement(double percentTasaMovement) {
		this.percentTasaMovement = percentTasaMovement;
	}

	public int getCantidadDiasMovement() {
		return cantidadDiasMovement;
	}

	public void setCantidadDiasMovement(int cantidadDiasMovement) {
		this.cantidadDiasMovement = cantidadDiasMovement;
	}

	public double getTasaEfectivaPeriodoMovement() {
		return tasaEfectivaPeriodoMovement;
	}

	public void setTasaEfectivaPeriodoMovement(double tasaEfectivaPeriodoMovement) {
		this.tasaEfectivaPeriodoMovement = tasaEfectivaPeriodoMovement;
	}

	public double getTasaDescuentoPeriodoMovement() {
		return tasaDescuentoPeriodoMovement;
	}

	public void setTasaDescuentoPeriodoMovement(double tasaDescuentoPeriodoMovement) {
		this.tasaDescuentoPeriodoMovement = tasaDescuentoPeriodoMovement;
	}

	public double getCostoInicialMovement() {
		return costoInicialMovement;
	}

	public void setCostoInicialMovement(double costoInicialMovement) {
		this.costoInicialMovement = costoInicialMovement;
	}

	public double getCostoFinalMovement() {
		return costoFinalMovement;
	}

	public void setCostoFinalMovement(double costoFinalMovement) {
		this.costoFinalMovement = costoFinalMovement;
	}

	public double getDescuentoMovement() {
		return descuentoMovement;
	}

	public void setDescuentoMovement(double descuentoMovement) {
		this.descuentoMovement = descuentoMovement;
	}

	public double getValorNetoMovement() {
		return valorNetoMovement;
	}

	public void setValorNetoMovement(double valorNetoMovement) {
		this.valorNetoMovement = valorNetoMovement;
	}

	public double getValorRecibidoMovement() {
		return valorRecibidoMovement;
	}

	public void setValorRecibidoMovement(double valorRecibidoMovement) {
		this.valorRecibidoMovement = valorRecibidoMovement;
	}

	public double getValorEntregadoMovement() {
		return valorEntregadoMovement;
	}

	public void setValorEntregadoMovement(double valorEntregadoMovement) {
		this.valorEntregadoMovement = valorEntregadoMovement;
	}

	public double getTceaMovement() {
		return tceaMovement;
	}

	public void setTceaMovement(double tceaMovement) {
		this.tceaMovement = tceaMovement;
	}
}
