package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RateType")
public class RateType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRateType;
	
	@Column(name="nameRateType", nullable=false, length=100)
	private String nameRateType;

	public RateType() {
		super();
	}

	public RateType(int idRateType, String nameRateType) {
		super();
		this.idRateType = idRateType;
		this.nameRateType = nameRateType;
	}

	public int getIdRateType() {
		return idRateType;
	}

	public void setIdRateType(int idRateType) {
		this.idRateType = idRateType;
	}

	public String getNameRateType() {
		return nameRateType;
	}

	public void setNameRateType(String nameRateType) {
		this.nameRateType = nameRateType;
	}
}
