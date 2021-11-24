package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.RateType;
import pe.edu.upc.spring.repository.IRateTypeRepository;
import pe.edu.upc.spring.service.IRateTypeService;

@Service
public class RateTypeServiceImpl implements IRateTypeService {

	@Autowired
	private IRateTypeRepository dRateType;

	@Override
	@Transactional
	public boolean save(RateType rateType) {
		RateType objRateType = dRateType.save(rateType);
		if (objRateType == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void delete(int i) {
		dRateType.deleteById(i);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<RateType> findAllSortNameAsc() {
		return dRateType.findAll(Sort.by(Sort.Direction.ASC,"nameRateType"));//AGREGADO
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<RateType> findAllSortIdAsc() {
		return dRateType.findAll(Sort.by(Sort.Direction.ASC,"idRateType"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<RateType> findById(int idRateType) {
		return dRateType.findById(idRateType);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<RateType> findByName(String nameRateType) {
		return dRateType.findByName(nameRateType);
	}
}
