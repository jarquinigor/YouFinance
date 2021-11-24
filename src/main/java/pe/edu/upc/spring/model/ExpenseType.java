package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ExpenseType")
public class ExpenseType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idExpenseType;
	
	@Column(name="nameExpenseType", nullable=false, length=100)
	private String nameExpenseType;
	
	@Column(name="momentExpenseType", nullable=false)
	private int momentExpenseType;

	public ExpenseType() {
		super();
	}

	public ExpenseType(int idExpenseType, String nameExpenseType, int momentExpenseType) {
		super();
		this.idExpenseType = idExpenseType;
		this.nameExpenseType = nameExpenseType;
		this.momentExpenseType = momentExpenseType;
	}

	public int getIdExpenseType() {
		return idExpenseType;
	}

	public void setIdExpenseType(int idExpenseType) {
		this.idExpenseType = idExpenseType;
	}

	public String getNameExpenseType() {
		return nameExpenseType;
	}

	public void setNameExpenseType(String nameExpenseType) {
		this.nameExpenseType = nameExpenseType;
	}

	public int getMomentExpenseType() {
		return momentExpenseType;
	}

	public void setMomentExpenseType(int momentExpenseType) {
		this.momentExpenseType = momentExpenseType;
	}
}
