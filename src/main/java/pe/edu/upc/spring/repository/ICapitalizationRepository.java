package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Capitalization;

@Repository
public interface ICapitalizationRepository extends JpaRepository<Capitalization, Integer> {
	@Query("from Capitalization c where LOWER(c.nameCapitalization) like LOWER(concat('%',:nameCapitalization,'%')) order by c.idCapitalization ASC")
	List<Capitalization> findByName(@Param("nameCapitalization") String nameCapitalization);
}