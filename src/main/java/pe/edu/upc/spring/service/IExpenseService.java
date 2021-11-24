package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Expense;

public interface IExpenseService {
	public boolean save(Expense expense);
	public void delete(int idExpense);
	public List<Expense> findAllSortIdAsc();
	public Optional<Expense>findById(int idExpense);
	public List<Expense> findByMovementId(int idMovement, int momentExpenseType);
}
