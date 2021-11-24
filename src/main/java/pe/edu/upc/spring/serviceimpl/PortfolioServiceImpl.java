package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Portfolio;
import pe.edu.upc.spring.repository.IPortfolioRepository;
import pe.edu.upc.spring.service.IPortfolioService;

@Service
public class PortfolioServiceImpl implements IPortfolioService {

	@Autowired
	private IPortfolioRepository dPortfolio;

	@Override
	@Transactional
	public boolean save(Portfolio portfolio) {
		Portfolio objPortfolio = dPortfolio.save(portfolio);
		if (objPortfolio == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void delete(int i) {
		dPortfolio.deleteById(i);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Portfolio> findAllSortIdAsc() {
		return dPortfolio.findAll(Sort.by(Sort.Direction.ASC,"idPortfolio"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Portfolio> findById(int idPortfolio) {
		return dPortfolio.findById(idPortfolio);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Portfolio> findByUserAndMovementTypeId(int idUser,int idPortfolioType){
		return dPortfolio.findByUserAndMovementTypeId(idUser, idPortfolioType);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Portfolio> findByPortfolioId(int idPortfolio){
		return dPortfolio.findByPortfolioId(idPortfolio);
	}
}
