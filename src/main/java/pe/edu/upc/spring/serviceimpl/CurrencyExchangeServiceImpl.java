package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.CurrencyExchange;
import pe.edu.upc.spring.repository.ICurrencyExchangeRepository;
import pe.edu.upc.spring.service.ICurrencyExchangeService;

@Service
public class CurrencyExchangeServiceImpl implements ICurrencyExchangeService {

	@Autowired
	private ICurrencyExchangeRepository dCurrencyExchange;

	@Override
	@Transactional
	public boolean save(CurrencyExchange currency) {
		CurrencyExchange objCurrency = dCurrencyExchange.save(currency);
		if (objCurrency == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void delete(int i) {
		dCurrencyExchange.deleteById(i);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<CurrencyExchange> findAllSortIdAsc() {
		return dCurrencyExchange.findAll(Sort.by(Sort.Direction.ASC,"idCurrencyExchange"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<CurrencyExchange> findById(int idCurrencyExchange) {
		return dCurrencyExchange.findById(idCurrencyExchange);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<CurrencyExchange> findCurrencyExchange(int idCurrencyForeign, int idCurrencyLocal) {
		return dCurrencyExchange.findCurrencyExchange(idCurrencyForeign, idCurrencyLocal);
	}
}
