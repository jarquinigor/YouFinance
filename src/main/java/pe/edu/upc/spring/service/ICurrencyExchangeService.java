package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.CurrencyExchange;

public interface ICurrencyExchangeService {
	public boolean save(CurrencyExchange currencyExchange);
	public void delete(int idCurrencyExchange);
	public List<CurrencyExchange> findAllSortIdAsc();
	public Optional<CurrencyExchange>findById(int idCurrencyExchange);
	public List<CurrencyExchange> findCurrencyExchange(int idCurrencyForeign, int idCurrencyLocal);
}
