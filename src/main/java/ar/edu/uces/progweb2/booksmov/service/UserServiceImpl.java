package ar.edu.uces.progweb2.booksmov.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.uces.progweb2.booksmov.dao.UserDao;
import ar.edu.uces.progweb2.booksmov.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public User getUserById(Long id) {
		return userDao.getUserById(id);
	}

	@Override
	public List<String> getNamesByInput(String input) {
		return userDao.getNamesByInput(input);
	}

}
