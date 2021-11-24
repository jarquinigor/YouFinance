package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Establishment;
import pe.edu.upc.spring.repository.IEstablishmentRepository;
import pe.edu.upc.spring.service.IEstablishmentService;

@Service
public class EstablishmentServiceImpl implements IEstablishmentService {

	@Autowired
	private IEstablishmentRepository dEstablishment;

	@Override
	@Transactional
	public boolean save(Establishment establishment) {
		Establishment objEstablishment = dEstablishment.save(establishment);
		if (objEstablishment == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void delete(int i) {
		dEstablishment.deleteById(i);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Establishment> findAllSortNameAsc() {
		return dEstablishment.findAll(Sort.by(Sort.Direction.ASC,"nameEstablishment"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Establishment> findAllSortIdAsc() {
		return dEstablishment.findAll(Sort.by(Sort.Direction.ASC,"idEstablishment"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Establishment> findById(int idEstablishment) {
		return dEstablishment.findById(idEstablishment);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Establishment> findByName(String nameEstablishment, int idUser) {
		return dEstablishment.findByName(nameEstablishment, idUser);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Establishment> findByUserId(int idUser){
		return dEstablishment.findByUserId(idUser);
	}
}
