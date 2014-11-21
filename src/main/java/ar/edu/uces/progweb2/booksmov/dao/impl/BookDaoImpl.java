package ar.edu.uces.progweb2.booksmov.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.uces.progweb2.booksmov.dao.BookDao;
import ar.edu.uces.progweb2.booksmov.model.Book;

@Repository
public class BookDaoImpl implements BookDao{
	 
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(Book book) {
		sessionFactory.getCurrentSession().save(book);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getBooks(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Book b WHERE b.user.id = :userId");
		query.setLong("userId", id);
		return query.list();
	}

	@Override
	public void update(Book book) {
		Session session = sessionFactory.getCurrentSession();
		Book original = (Book) session.get(Book.class, book.getId());
		original.setAlreadyUsed(book.isAlreadyUsed());
		original.setBorrowable(book.isBorrowable());
		original.setImage(book.getImage());
		original.setId(book.getId());
		original.setRating(book.getRating());
		original.setTitle(book.getTitle());
		original.setUser(book.getUser());
		original.setDescription(book.getDescription());
		original.getAuthors().clear();
		original.getAuthors().addAll(book.getAuthors());
		session.update(original);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getBooksByUserName(String userName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Book b WHERE b.user.name = :name");
		query.setString("name", userName);
		return (List<Book>) query.list();
	}

	@Override
	public Book getBookById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Book b WHERE b.id = :id");
		query.setLong("id", id);
		return (Book) query.uniqueResult();
	}
}
