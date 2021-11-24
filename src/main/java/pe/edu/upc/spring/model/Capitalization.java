package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Capitalization")
public class Capitalization implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCapitalization;
	
	@Column(name="nameCurrency", nullable=false, length=100)
	private String nameCapitalization;
	
	@Column(name="daysCapitalization", nullable=false)
	private int daysCapitalization;

	public Capitalization() {
		super();
	}

	public Capitalization(int idCapitalization, String nameCapitalization, int daysCapitalization) {
		super();
		this.idCapitalization = idCapitalization;
		this.nameCapitalization = nameCapitalization;
		this.daysCapitalization = daysCapitalization;
	}

	public int getIdCapitalization() {
		return idCapitalization;
	}

	public void setIdCapitalization(int idCapitalization) {
		this.idCapitalization = idCapitalization;
	}

	public String getNameCapitalization() {
		return nameCapitalization;
	}

	public void setNameCapitalization(String nameCapitalization) {
		this.nameCapitalization = nameCapitalization;
	}

	public int getDaysCapitalization() {
		return daysCapitalization;
	}

	public void setDaysCapitalization(int daysCapitalization) {
		this.daysCapitalization = daysCapitalization;
	}
}
