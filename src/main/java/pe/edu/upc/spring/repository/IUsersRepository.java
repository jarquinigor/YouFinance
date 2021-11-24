package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Users;

@Repository
public interface IUsersRepository extends JpaRepository<Users, Integer> {
	@Query("from Users u where u.nameUser like %:nameUser% order by u.idUser ASC")
	List<Users> findByName(@Param("nameUser") String nameUser);
	
	@Query("from Users u where u.emailUser = :emailUser")
	List<Users> findByEmail(@Param("emailUser") String emailUser); //Devuelve un usuario (OJO)
}
