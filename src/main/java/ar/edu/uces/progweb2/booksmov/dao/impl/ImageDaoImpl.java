package ar.edu.uces.progweb2.booksmov.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.uces.progweb2.booksmov.dao.ImageDao;
import ar.edu.uces.progweb2.booksmov.model.Book;
import ar.edu.uces.progweb2.booksmov.model.Movie;

@Repository
public class ImageDaoImpl implements ImageDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public byte[] getBookImage(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (byte[]) session.createCriteria(Book.class)
	        .setProjection(Projections.projectionList().add(Projections.property("image")))
	        .add(Restrictions.eq("id", id)).uniqueResult();
	}

	@Override
	public byte[] getMovieImage(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (byte[]) session.createCriteria(Movie.class)
	        .setProjection(Projections.projectionList().add(Projections.property("image")))
	        .add(Restrictions.eq("id", id)).uniqueResult();
	}

}
