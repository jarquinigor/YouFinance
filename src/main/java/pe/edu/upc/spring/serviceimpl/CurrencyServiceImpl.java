package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Currency;
import pe.edu.upc.spring.repository.ICurrencyRepository;
import pe.edu.upc.spring.service.ICurrencyService;

@Service
public class CurrencyServiceImpl implements ICurrencyService {

	@Autowired
	private ICurrencyRepository dCurrency;

	@Override
	@Transactional
	public boolean save(Currency currency) {
		Currency objCurrency = dCurrency.save(currency);
		if (objCurrency == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void delete(int i) {
		dCurrency.deleteById(i);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Currency> findAllSortNameAsc() {
		return dCurrency.findAll(Sort.by(Sort.Direction.ASC,"nameCurrency"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Currency> findAllSortIdAsc() {
		return dCurrency.findAll(Sort.by(Sort.Direction.ASC,"idCurrency"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Currency> findById(int idCurrency) {
		return dCurrency.findById(idCurrency);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Currency> findByName(String nameCurrency) {
		return dCurrency.findByName(nameCurrency);
	}
}
