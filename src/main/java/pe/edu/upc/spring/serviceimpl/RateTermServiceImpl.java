package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.RateTerm;
import pe.edu.upc.spring.repository.IRateTermRepository;
import pe.edu.upc.spring.service.IRateTermService;

@Service
public class RateTermServiceImpl implements IRateTermService {

	@Autowired
	private IRateTermRepository dRateTerm;

	@Override
	@Transactional
	public boolean save(RateTerm rateTerm) {
		RateTerm objRateTerm = dRateTerm.save(rateTerm);
		if (objRateTerm == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void delete(int i) {
		dRateTerm.deleteById(i);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<RateTerm> findAllSortNameAsc() {
		return dRateTerm.findAll(Sort.by(Sort.Direction.ASC,"nameRateTerm"));//AGREGADO
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<RateTerm> findAllSortIdAsc() {
		return dRateTerm.findAll(Sort.by(Sort.Direction.ASC,"idRateTerm"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<RateTerm> findById(int idRateTerm) {
		return dRateTerm.findById(idRateTerm);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<RateTerm> findByName(String nameRateTerm) {
		return dRateTerm.findByName(nameRateTerm);
	}
}
