package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.MovementType;

@Repository
public interface IMovementTypeRepository extends JpaRepository<MovementType, Integer> {
	@Query("from MovementType mt where LOWER(mt.nameMovementType) like LOWER(concat('%',:nameMovementType,'%')) order by mt.idMovementType ASC")
	List<MovementType> findByName(@Param("nameMovementType") String nameMovementType);
}
