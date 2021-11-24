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
@Table(name = "Expense")
public class Expense implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idExpense;
	
	@ManyToOne
	@JoinColumn(name = "idExpenseType", nullable = false)
	private ExpenseType expenseType;
	
	@ManyToOne
	@JoinColumn(name = "idMovement", nullable = false)
	private Movement movement;
	
	@Column(name="amountExpense", nullable=false, length=100)
	private double amountExpense;

	public Expense() {
		super();
	}

	public Expense(int idExpense, ExpenseType expenseType, Movement movement, double amountExpense) {
		super();
		this.idExpense = idExpense;
		this.expenseType = expenseType;
		this.movement = movement;
		this.amountExpense = amountExpense;
	}

	public int getIdExpense() {
		return idExpense;
	}

	public void setIdExpense(int idExpense) {
		this.idExpense = idExpense;
	}

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}

	public Movement getMovement() {
		return movement;
	}

	public void setMovement(Movement movement) {
		this.movement = movement;
	}

	public double getAmountExpense() {
		return amountExpense;
	}

	public void setAmountExpense(double amountExpense) {
		this.amountExpense = amountExpense;
	}
}
