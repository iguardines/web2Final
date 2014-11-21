package ar.edu.uces.progweb2.booksmov.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.uces.progweb2.booksmov.dao.UserDao;
import ar.edu.uces.progweb2.booksmov.model.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public User getUserById(Long id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

}
