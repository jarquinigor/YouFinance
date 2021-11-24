package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.ExpenseType;

@Repository
public interface IExpenseTypeRepository extends JpaRepository<ExpenseType, Integer> {
	@Query("from ExpenseType et where LOWER(et.nameExpenseType) like LOWER(concat('%',:nameExpenseType,'%')) order by et.idExpenseType ASC")
	List<ExpenseType> findByName(@Param("nameExpenseType") String nameExpenseType);
	
	@Query("from ExpenseType et where et.momentExpenseType = :momentExpenseType order by et.idExpenseType ASC")
	List<ExpenseType> findByMoment(@Param("momentExpenseType") int momentExpenseType);
}