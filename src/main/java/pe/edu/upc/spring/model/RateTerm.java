package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RateTerm")
public class RateTerm implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRateTerm;
	
	@Column(name="nameRateTerm", nullable=false, length=100)
	private String nameRateTerm;
	
	@Column(name="daysRateTerm", nullable=false)
	private int daysRateTerm;

	public RateTerm() {
		super();
	}

	public RateTerm(int idRateTerm, String nameRateTerm, int daysRateTerm) {
		super();
		this.idRateTerm = idRateTerm;
		this.nameRateTerm = nameRateTerm;
		this.daysRateTerm = daysRateTerm;
	}

	public int getIdRateTerm() {
		return idRateTerm;
	}

	public void setIdRateTerm(int idRateTerm) {
		this.idRateTerm = idRateTerm;
	}

	public String getNameRateTerm() {
		return nameRateTerm;
	}

	public void setNameRateTerm(String nameRateTerm) {
		this.nameRateTerm = nameRateTerm;
	}

	public int getDaysRateTerm() {
		return daysRateTerm;
	}

	public void setDaysRateTerm(int daysRateTerm) {
		this.daysRateTerm = daysRateTerm;
	}
}
