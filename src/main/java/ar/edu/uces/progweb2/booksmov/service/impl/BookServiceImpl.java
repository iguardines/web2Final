package ar.edu.uces.progweb2.booksmov.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.uces.progweb2.booksmov.dao.BookDao;
import ar.edu.uces.progweb2.booksmov.dto.BookDto;
import ar.edu.uces.progweb2.booksmov.model.Book;
import ar.edu.uces.progweb2.booksmov.model.Product;
import ar.edu.uces.progweb2.booksmov.service.BookConverterService;
import ar.edu.uces.progweb2.booksmov.service.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookDao bookDao;
	@Autowired
	private BookConverterService converter;
	
	@Override
	public void save(Book book) {
		bookDao.save(book);
	}

	@Override
	public List<BookDto> getBooksByUserId(Long id) {
		List<Book> books = bookDao.getBooks(id);
		return transform(books);
	}

	private List<BookDto> transform(Collection<Book> books) {
		List<BookDto> bookDtos = new ArrayList<BookDto>();
		for (Book book : books) {
			bookDtos.add(converter.transform(book));
		}
		return bookDtos;
	}

	@Override
	@Transactional(readOnly=false)
	public void update(Book book) {
		bookDao.update(book);
		
	}

	@Override
	public List<BookDto> getBooksByUserName(String userName) {
		List<Book> books = bookDao.getBooksByUserName(userName);
		return transform(books);
	}

	@Override
	public BookDto getBookById(Long id) {
		Book book = bookDao.getBookById(id);
		return converter.transform(book);
	}

	public List<Book> getBooksWithNoDuplicates(Collection<? extends Product> products){
		
		List<Book> books = new ArrayList<Book>();
		
		for (Product product : products) {
			Book book = (Book) product;
			if(!books.contains(book)){
				books.add(book);
			}
		}
		
		return books;
	}

}
