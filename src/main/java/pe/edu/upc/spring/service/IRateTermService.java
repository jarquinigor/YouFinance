package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.RateTerm;

public interface IRateTermService {
	public boolean save(RateTerm rateTerm);
	public void delete(int idRateTerm);
	public List<RateTerm> findAllSortNameAsc();
	public List<RateTerm> findAllSortIdAsc();
	public Optional<RateTerm>findById(int idRateTerm);
	public List<RateTerm> findByName(String nameRateTerm);
}
