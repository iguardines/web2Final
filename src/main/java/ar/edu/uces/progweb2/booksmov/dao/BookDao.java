package ar.edu.uces.progweb2.booksmov.dao;

import java.util.List;

import ar.edu.uces.progweb2.booksmov.model.Book;

public interface BookDao {
	
	void save(Book book);
	List<Book> getBooks(Long id);
	void update(Book book);
	List<Book> getBooksByUserName(String userName);
	Book getBookById(Long id);
}
