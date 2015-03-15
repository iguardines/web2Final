package ar.edu.uces.progweb2.booksmov.service;

import java.util.List;

import ar.edu.uces.progweb2.booksmov.model.User;

public interface UserService {
	
	User getUserById(Long id);
	User getUserByName(String name);
	List<String> getNamesByInput(String input);
}
