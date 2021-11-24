package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Capitalization;
import pe.edu.upc.spring.repository.ICapitalizationRepository;
import pe.edu.upc.spring.service.ICapitalizationService;

@Service
public class CapitalizationServiceImpl implements ICapitalizationService {

	@Autowired
	private ICapitalizationRepository dCapitalization;

	@Override
	@Transactional
	public boolean save(Capitalization capitalization) {
		Capitalization objCapitalization = dCapitalization.save(capitalization);
		if (objCapitalization == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void delete(int i) {
		dCapitalization.deleteById(i);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Capitalization> findAllSortNameAsc() {
		return dCapitalization.findAll(Sort.by(Sort.Direction.ASC,"nameCapitalization"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Capitalization> findAllSortIdAsc() {
		return dCapitalization.findAll(Sort.by(Sort.Direction.ASC,"idCapitalization"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Capitalization> findById(int idCapitalization) {
		return dCapitalization.findById(idCapitalization);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Capitalization> findByName(String nameCapitalization) {
		return dCapitalization.findByName(nameCapitalization);
	}
}
