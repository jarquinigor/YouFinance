package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MovementType")
public class MovementType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMovementType;
	
	@Column(name="nameMovementType", nullable=false, length=100)
	private String nameMovementType;

	public MovementType() {
		super();
	}

	public MovementType(int idMovementType, String nameMovementType) {
		super();
		this.idMovementType = idMovementType;
		this.nameMovementType = nameMovementType;
	}

	public int getIdMovementType() {
		return idMovementType;
	}

	public void setIdMovementType(int idMovementType) {
		this.idMovementType = idMovementType;
	}

	public String getNameMovementType() {
		return nameMovementType;
	}

	public void setNameMovementType(String nameMovementType) {
		this.nameMovementType = nameMovementType;
	}
}
