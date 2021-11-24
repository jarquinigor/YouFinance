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
@Table(name = "Establishment")
public class Establishment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEstablishment;
	
	@Column(name="nameEstablishment", nullable=false, length=100)
	private String nameEstablishment;
	
	@ManyToOne
	@JoinColumn(name = "idEstablishmentType", nullable = false)
	private EstablishmentType establishmentType;
	
	@ManyToOne
	@JoinColumn(name = "idUser", nullable = false)
	private Users user;

	public Establishment() {
		super();
	}

	public Establishment(int idEstablishment, String nameEstablishment, EstablishmentType establishmentType,
			Users user) {
		super();
		this.idEstablishment = idEstablishment;
		this.nameEstablishment = nameEstablishment;
		this.establishmentType = establishmentType;
		this.user = user;
	}

	public int getIdEstablishment() {
		return idEstablishment;
	}

	public void setIdEstablishment(int idEstablishment) {
		this.idEstablishment = idEstablishment;
	}

	public String getNameEstablishment() {
		return nameEstablishment;
	}

	public void setNameEstablishment(String nameEstablishment) {
		this.nameEstablishment = nameEstablishment;
	}

	public EstablishmentType getEstablishmentType() {
		return establishmentType;
	}

	public void setEstablishmentType(EstablishmentType establishmentType) {
		this.establishmentType = establishmentType;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
}
