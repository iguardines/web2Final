package ar.edu.uces.progweb2.booksmov.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getNamesByInput(String input) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.setProjection(Projections.property("name"));
		criteria.add(Restrictions.ilike("name", input, MatchMode.START));
		criteria.setMaxResults(5);
		return (List<String>) criteria.list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User getUserByName(String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("name", name));
		return (User) criteria.uniqueResult();
	}
	
}
