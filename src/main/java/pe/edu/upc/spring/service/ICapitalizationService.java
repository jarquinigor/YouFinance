package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Capitalization;

public interface ICapitalizationService {
	public boolean save(Capitalization capitalization);
	public void delete(int idCapitalization);
	public List<Capitalization> findAllSortNameAsc();
	public List<Capitalization> findAllSortIdAsc();
	public Optional<Capitalization>findById(int idCapitalization);
	public List<Capitalization> findByName(String nameCapitalization);
}
