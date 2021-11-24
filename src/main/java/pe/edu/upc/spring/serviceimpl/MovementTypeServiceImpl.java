package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.MovementType;
import pe.edu.upc.spring.repository.IMovementTypeRepository;
import pe.edu.upc.spring.service.IMovementTypeService;

@Service
public class MovementTypeServiceImpl implements IMovementTypeService {

	@Autowired
	private IMovementTypeRepository dMovementType;

	@Override
	@Transactional
	public boolean save(MovementType movementType) {
		MovementType objMovementType = dMovementType.save(movementType);
		if (objMovementType == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void delete(int i) {
		dMovementType.deleteById(i);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MovementType> findAllSortNameAsc() {
		return dMovementType.findAll(Sort.by(Sort.Direction.ASC,"nameMovementType"));//AGREGADO
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MovementType> findAllSortIdAsc() {
		return dMovementType.findAll(Sort.by(Sort.Direction.ASC,"idMovementType"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<MovementType> findById(int idMovementType) {
		return dMovementType.findById(idMovementType);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MovementType> findByName(String nameMovementType) {
		return dMovementType.findByName(nameMovementType);
	}
}
