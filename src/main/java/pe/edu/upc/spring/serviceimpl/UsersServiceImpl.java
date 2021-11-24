package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Users;
import pe.edu.upc.spring.repository.IUsersRepository;
import pe.edu.upc.spring.service.IUsersService;

@Service
public class UsersServiceImpl implements IUsersService {

	@Autowired
	private IUsersRepository dUser;

	@Override
	@Transactional
	public boolean save(Users user) {
		Users objUser = dUser.save(user);
		if (objUser == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void delete(int idUser) {
		dUser.deleteById(idUser);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Users> findAll() {
		return dUser.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Users> findAllSortAsc() {
		return dUser.findAll(Sort.by(Sort.Direction.ASC,"idUser"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Users> findById(int idUser) {
		return dUser.findById(idUser);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Users> findByName(String nameUser) {
		return dUser.findByName(nameUser);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Users> findByEmail(String emailUser) {
		return dUser.findByEmail(emailUser);
	}
}
