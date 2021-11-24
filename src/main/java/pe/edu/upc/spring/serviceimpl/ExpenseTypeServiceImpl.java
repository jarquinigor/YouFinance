package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.ExpenseType;
import pe.edu.upc.spring.repository.IExpenseTypeRepository;
import pe.edu.upc.spring.service.IExpenseTypeService;

@Service
public class ExpenseTypeServiceImpl implements IExpenseTypeService {

	@Autowired
	private IExpenseTypeRepository dExpenseType;

	@Override
	@Transactional
	public boolean save(ExpenseType expenseType) {
		ExpenseType objExpenseType = dExpenseType.save(expenseType);
		if (objExpenseType == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void delete(int i) {
		dExpenseType.deleteById(i);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ExpenseType> findAllSortNameAsc() {
		return dExpenseType.findAll(Sort.by(Sort.Direction.ASC,"nameExpenseType"));//AGREGADO
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ExpenseType> findAllSortIdAsc() {
		return dExpenseType.findAll(Sort.by(Sort.Direction.ASC,"idExpenseType"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<ExpenseType> findById(int idExpenseType) {
		return dExpenseType.findById(idExpenseType);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ExpenseType> findByName(String nameExpenseType) {
		return dExpenseType.findByName(nameExpenseType);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ExpenseType> findByMoment(int momentExpenseType){
		return dExpenseType.findByMoment(momentExpenseType);
	}
}
