package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Currency;

@Repository
public interface ICurrencyRepository extends JpaRepository<Currency, Integer> {
	@Query("from Currency c where LOWER(c.nameCurrency) like LOWER(concat('%',:nameCurrency,'%')) order by c.idCurrency ASC")
	List<Currency> findByName(@Param("nameCurrency") String nameCurrency);
}
