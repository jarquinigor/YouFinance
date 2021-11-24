package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.RateType;

@Repository
public interface IRateTypeRepository extends JpaRepository<RateType, Integer> {
	@Query("from RateType rt where LOWER(rt.nameRateType) like LOWER(concat('%',:nameRateType,'%')) order by rt.idRateType ASC")
	List<RateType> findByName(@Param("nameRateType") String nameRateType);
}