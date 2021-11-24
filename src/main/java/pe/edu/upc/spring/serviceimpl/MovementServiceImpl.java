package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Movement;
import pe.edu.upc.spring.repository.IMovementRepository;
import pe.edu.upc.spring.service.IMovementService;

@Service
public class MovementServiceImpl implements IMovementService {

	@Autowired
	private IMovementRepository dMovement;

	@Override
	@Transactional
	public boolean save(Movement movement) {
		Movement objMovement = dMovement.save(movement);
		if (objMovement == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void delete(int i) {
		dMovement.deleteById(i);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Movement> findAllSortIdAsc() {
		return dMovement.findAll(Sort.by(Sort.Direction.ASC,"idMovement"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Movement> findById(int idMovement) {
		return dMovement.findById(idMovement);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Movement> findByMovementId(int idMovement){
		return dMovement.findByMovementId(idMovement);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Movement> findByPortfolioId(int idPortfolio){
		return dMovement.findByPortfolioId(idPortfolio);
	}
}
