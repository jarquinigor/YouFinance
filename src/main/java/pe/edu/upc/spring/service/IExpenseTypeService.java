package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.ExpenseType;

public interface IExpenseTypeService {
	public boolean save(ExpenseType expenseType);
	public void delete(int idExpenseType);
	public List<ExpenseType> findAllSortNameAsc();
	public List<ExpenseType> findAllSortIdAsc();
	public Optional<ExpenseType>findById(int idExpenseType);
	public List<ExpenseType> findByName(String nameExpenseType);
	public List<ExpenseType> findByMoment(int momentExpenseType);
}
