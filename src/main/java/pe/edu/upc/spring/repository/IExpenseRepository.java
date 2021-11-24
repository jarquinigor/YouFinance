package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Expense;

@Repository
public interface IExpenseRepository extends JpaRepository<Expense, Integer> {
	@Query("from Expense e where e.movement.idMovement = :idMovement and e.expenseType.momentExpenseType = :momentExpenseType order by e.idExpense ASC")
	List<Expense> findByMovementId(@Param("idMovement") int idMovement, @Param("momentExpenseType") int momentExpenseType);
	
	@Query("from Expense e where e.movement.idMovement = :idMovement")
	List<Expense> findAllByMovementId(@Param("idMovement") int idMovement);
}