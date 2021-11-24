package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.RateTerm;

@Repository
public interface IRateTermRepository extends JpaRepository<RateTerm, Integer> {
	@Query("from RateTerm rt where LOWER(rt.nameRateTerm) like LOWER(concat('%',:nameRateTerm,'%')) order by rt.idRateTerm ASC")
	List<RateTerm> findByName(@Param("nameRateTerm") String nameRateTerm);
}