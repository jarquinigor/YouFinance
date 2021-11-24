package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.EstablishmentType;

public interface IEstablishmentTypeService {
	public boolean save(EstablishmentType EstablishmentType);
	public void delete(int idEstablishmentType);
	public List<EstablishmentType> findAllSortNameAsc();
	public List<EstablishmentType> findAllSortIdAsc();
	public Optional<EstablishmentType>findById(int idEstablishmentType);
	public List<EstablishmentType> findByName(String nameEstablishmentType);
}
