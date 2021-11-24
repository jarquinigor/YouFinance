package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Users;

public interface IUsersService {
	public boolean save(Users user);
	public void delete(int idUser);
	public List<Users> findAll();
	public List<Users> findAllSortAsc();
	public Optional<Users>findById(int idUser);
	public List<Users> findByName(String nameUser);
	public List<Users> findByEmail(String emailUser);
}
