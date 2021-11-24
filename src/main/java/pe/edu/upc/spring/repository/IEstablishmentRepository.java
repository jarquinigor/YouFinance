package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Establishment;

@Repository
public interface IEstablishmentRepository extends JpaRepository<Establishment, Integer> {
	@Query("from Establishment e where e.user.idUser = :idUser and LOWER(e.nameEstablishment) like LOWER(concat('%',:nameEstablishment,'%')) order by e.idEstablishment ASC")
	List<Establishment> findByName(@Param("nameEstablishment") String nameEstablishment, @Param("idUser") int idUser);
	
	@Query("from Establishment e where e.user.idUser = :idUser and LOWER(e.establishmentType.nameEstablishmentType) like LOWER(concat('%',:nameEstablishmentType,'%')) order by e.idEstablishment ASC")
	List<Establishment> findByType(@Param("nameEstablishmentType") String nameEstablishmentType, @Param("idUser") int idUser);
	
	@Query("from Establishment e where e.user.idUser = :idUser order by e.idEstablishment ASC")
	List<Establishment> findByUserId(@Param("idUser") int idUser);
}
