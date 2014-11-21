package ar.edu.uces.progweb2.booksmov.service;

import java.util.List;

import ar.edu.uces.progweb2.booksmov.dto.BookDto;
import ar.edu.uces.progweb2.booksmov.model.Book;

public interface BookService {

	void save(Book book);
	List<BookDto> getBooksByUserId(Long id);
	BookDto getBookById(Long id);
	void update(Book book);
	List<BookDto> getBooksByUserName(String userName);
}
