package ar.edu.uces.progweb2.booksmov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.uces.progweb2.booksmov.dao.impl.LoginDaoImpl;
import ar.edu.uces.progweb2.booksmov.model.User;
import ar.edu.uces.progweb2.booksmov.service.LoginService;

@Service
@Transactional(readOnly = true)
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginDaoImpl loginDao;
	
	@Override
	public User getUserByCredentials(String name, String password) {
		return loginDao.get(name, password);
	}

}
