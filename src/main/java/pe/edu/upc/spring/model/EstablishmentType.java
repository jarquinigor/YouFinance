package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EstablishmentType")
public class EstablishmentType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEstablishmentType;
	
	@Column(name="nameEstablishmentType", nullable=false, length=100)
	private String nameEstablishmentType;

	public EstablishmentType() {
		super();
	}

	public EstablishmentType(int idEstablishmentType, String nameEstablishmentType) {
		super();
		this.idEstablishmentType = idEstablishmentType;
		this.nameEstablishmentType = nameEstablishmentType;
	}

	public int getIdEstablishmentType() {
		return idEstablishmentType;
	}

	public void setIdEstablishmentType(int idEstablishmentType) {
		this.idEstablishmentType = idEstablishmentType;
	}

	public String getNameEstablishmentType() {
		return nameEstablishmentType;
	}

	public void setNameEstablishmentType(String nameEstablishmentType) {
		this.nameEstablishmentType = nameEstablishmentType;
	}
}
