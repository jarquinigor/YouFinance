package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.EstablishmentType;
import pe.edu.upc.spring.repository.IEstablishmentTypeRepository;
import pe.edu.upc.spring.service.IEstablishmentTypeService;

@Service
public class EstablishmentTypeServiceImpl implements IEstablishmentTypeService {

	@Autowired
	private IEstablishmentTypeRepository dEstablishmentType;

	@Override
	@Transactional
	public boolean save(EstablishmentType EstablishmentType) {
		EstablishmentType objEstablishmentType = dEstablishmentType.save(EstablishmentType);
		if (objEstablishmentType == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void delete(int i) {
		dEstablishmentType.deleteById(i);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<EstablishmentType> findAllSortNameAsc() {
		return dEstablishmentType.findAll(Sort.by(Sort.Direction.ASC,"nameEstablishmentType"));//AGREGADO
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<EstablishmentType> findAllSortIdAsc() {
		return dEstablishmentType.findAll(Sort.by(Sort.Direction.ASC,"idEstablishmentType"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<EstablishmentType> findById(int idEstablishmentType) {
		return dEstablishmentType.findById(idEstablishmentType);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<EstablishmentType> findByName(String nameEstablishmentType) {
		return dEstablishmentType.findByName(nameEstablishmentType);
	}
}
