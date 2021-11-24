package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Portfolio;

public interface IPortfolioService {
	public boolean save(Portfolio portfolio);
	public void delete(int idPortfolio);
	public List<Portfolio> findAllSortIdAsc();
	public Optional<Portfolio>findById(int idPortfolio);
	public List<Portfolio> findByUserAndMovementTypeId(int idUser,int idMovementType);
	List<Portfolio> findByPortfolioId(int idPortfolio);
}
