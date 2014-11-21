package ar.edu.uces.progweb2.booksmov.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.uces.progweb2.booksmov.dao.MovieDao;
import ar.edu.uces.progweb2.booksmov.model.Movie;

@Repository
public class MovieDaoImpl implements MovieDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(Movie movie) {
		sessionFactory.getCurrentSession().save(movie);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getMovies(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Movie m WHERE m.user.id = :userId");
		query.setLong("userId", id);
		return query.list();
	}

	@Override
	public void update(Movie movie) {
		Session session = sessionFactory.getCurrentSession();
		Movie original = (Movie) session.get(Movie.class, movie.getId());
		original.setAlreadyUsed(movie.isAlreadyUsed());
		original.setBorrowable(movie.isBorrowable());
		original.setDirector(movie.getDirector());
		original.setFormat(movie.getFormat());
		original.setId(movie.getId());
		original.setImage(movie.getImage());
		original.setRating(movie.getRating());
		original.setTitle(movie.getTitle());
		original.setUser(movie.getUser());
		original.getActors().clear();
		original.getActors().addAll(movie.getActors());
		session.update(original);

	}

	@Override
	public Movie getMovieById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Movie m WHERE m.id = :id");
		query.setLong("id", id);
		return (Movie) query.uniqueResult();
	}

}
