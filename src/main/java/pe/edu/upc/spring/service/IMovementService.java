package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Movement;

public interface IMovementService {
	public boolean save(Movement movement);
	public void delete(int idMovement);
	public List<Movement> findAllSortIdAsc();
	public Optional<Movement>findById(int idMovement);
	public List<Movement> findByMovementId(int idMovement);
	public List<Movement> findByPortfolioId(int idPortfolio);
}
