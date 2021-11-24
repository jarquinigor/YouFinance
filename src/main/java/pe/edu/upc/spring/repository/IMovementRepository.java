package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Movement;

@Repository
public interface IMovementRepository extends JpaRepository<Movement, Integer> {
	
	@Query("from Movement m where m.idMovement = :idMovement")
	List<Movement> findByMovementId(@Param("idMovement") int idMovement);
	
	@Query("from Movement m where m.establishment.user.idUser = :idUser order by m.idMovement ASC")
	List<Movement> findByUserId(@Param("idUser") int idUser);
	
	@Query("from Movement m where m.portfolio.idPortfolio = :idPortfolio order by m.idMovement ASC")
	List<Movement> findByPortfolioId(@Param("idPortfolio") int idPortfolio);
}
