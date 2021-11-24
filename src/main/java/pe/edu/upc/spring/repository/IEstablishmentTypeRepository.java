package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.EstablishmentType;

@Repository
public interface IEstablishmentTypeRepository extends JpaRepository<EstablishmentType, Integer> {
	@Query("from EstablishmentType e where LOWER(e.nameEstablishmentType) like LOWER(concat('%',:nameEstablishmentType,'%')) order by e.idEstablishmentType ASC")
	List<EstablishmentType> findByName(@Param("nameEstablishmentType") String nameEstablishmentType);
}
