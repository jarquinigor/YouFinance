package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Expense;
import pe.edu.upc.spring.repository.IExpenseRepository;
import pe.edu.upc.spring.service.IExpenseService;

@Service
public class ExpenseServiceImpl implements IExpenseService {

	@Autowired
	private IExpenseRepository dExpense;

	@Override
	@Transactional
	public boolean save(Expense expense) {
		Expense objExpense = dExpense.save(expense);
		if (objExpense == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void delete(int i) {
		dExpense.deleteById(i);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Expense> findAllSortIdAsc() {
		return dExpense.findAll(Sort.by(Sort.Direction.ASC,"idExpense"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Expense> findById(int idExpense) {
		return dExpense.findById(idExpense);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Expense> findByMovementId(int idMovement, int momentExpenseType){
		return dExpense.findByMovementId(idMovement, momentExpenseType);
	}
}
