package ar.edu.uces.progweb2.booksmov.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ar.edu.uces.progweb2.booksmov.dao.LoginDao;
import ar.edu.uces.progweb2.booksmov.model.User;

public class LoginDaoImpl implements LoginDao{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public User get(String name, String password) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User u where u.name = :name and password = :pass");
		query.setString("name", name);
		query.setString("pass", password);
		return (User) query.uniqueResult();

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public User get(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User u where u.name = :name");
		query.setString("name", username);
		return (User) query.uniqueResult();
	}


}
