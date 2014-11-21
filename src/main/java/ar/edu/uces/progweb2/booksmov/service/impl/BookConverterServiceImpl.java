package ar.edu.uces.progweb2.booksmov.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.uces.progweb2.booksmov.dto.BookDto;
import ar.edu.uces.progweb2.booksmov.model.Author;
import ar.edu.uces.progweb2.booksmov.model.Book;
import ar.edu.uces.progweb2.booksmov.service.BookConverterService;

@Service
public class BookConverterServiceImpl implements BookConverterService{

	@Override
	public BookDto transform(Book book) {
		
		BookDto bookDto = new BookDto(book.getTitle(), book.getRating(), book.isAlreadyUsed(),
									book.isBorrowable(), book.getImage(), book.getDescription(), book.getUser().getId());
		List<Author> authors = new ArrayList<Author>();
		bookDto.setId(book.getId());
		authors.addAll(book.getAuthors());
		bookDto.setAuthorsList(authors);
		return bookDto;
	}

}
