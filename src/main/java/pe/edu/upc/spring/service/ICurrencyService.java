package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Currency;

public interface ICurrencyService {
	public boolean save(Currency currency);
	public void delete(int idCurrency);
	public List<Currency> findAllSortNameAsc();
	public List<Currency> findAllSortIdAsc();
	public Optional<Currency>findById(int idCurrency);
	public List<Currency> findByName(String nameCurrency);
}
