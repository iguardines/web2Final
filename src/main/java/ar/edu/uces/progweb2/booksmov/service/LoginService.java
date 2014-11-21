package ar.edu.uces.progweb2.booksmov.service;

import ar.edu.uces.progweb2.booksmov.model.User;


public interface LoginService {

	User getUserByCredentials(String name, String password);
}
