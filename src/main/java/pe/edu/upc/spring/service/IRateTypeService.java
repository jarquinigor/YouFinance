package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.RateType;

public interface IRateTypeService {
	public boolean save(RateType rateType);
	public void delete(int idRateType);
	public List<RateType> findAllSortNameAsc();
	public List<RateType> findAllSortIdAsc();
	public Optional<RateType>findById(int idRateType);
	public List<RateType> findByName(String nameRateType);
}
