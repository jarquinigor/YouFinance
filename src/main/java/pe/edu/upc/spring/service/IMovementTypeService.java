package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.MovementType;

public interface IMovementTypeService {
	public boolean save(MovementType movementType);
	public void delete(int idMovementType);
	public List<MovementType> findAllSortNameAsc();
	public List<MovementType> findAllSortIdAsc();
	public Optional<MovementType>findById(int idMovementType);
	public List<MovementType> findByName(String nameMovementType);
}
