package ar.edu.uces.progweb2.booksmov.dao;

import ar.edu.uces.progweb2.booksmov.model.User;

public interface LoginDao {

	User get(String name, String password);
	User get(String name); // for Spring Security
}
