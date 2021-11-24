package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Establishment;

public interface IEstablishmentService {
	public boolean save(Establishment establishment);
	public void delete(int idEstablishment);
	public List<Establishment> findAllSortNameAsc();
	public List<Establishment> findAllSortIdAsc();
	public Optional<Establishment>findById(int idEstablishment);
	public List<Establishment> findByName(String nameEstablishment, int idUser);
	public List<Establishment> findByUserId(int idUser);
}
