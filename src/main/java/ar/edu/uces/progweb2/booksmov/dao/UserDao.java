package ar.edu.uces.progweb2.booksmov.dao;

import java.util.List;

import ar.edu.uces.progweb2.booksmov.model.User;

public interface UserDao {

	User getUserById(Long id);
	List<String> getNamesByInput(String input);
	User getUserByName(String name);
}
