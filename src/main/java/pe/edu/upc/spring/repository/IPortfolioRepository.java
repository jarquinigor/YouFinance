package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Portfolio;

@Repository
public interface IPortfolioRepository extends JpaRepository<Portfolio, Integer> {
	@Query("from Portfolio p where p.user.idUser = :idUser and p.movementType.idMovementType = :idMovementType order by p.idPortfolio ASC")
	List<Portfolio> findByUserAndMovementTypeId(@Param("idUser") int idUser, @Param("idMovementType") int idMovementType);
	
	@Query("from Portfolio p where p.idPortfolio = :idPortfolio")
	List<Portfolio> findByPortfolioId(@Param("idPortfolio") int idPortfolio);
}
