package ar.edu.uces.progweb2.booksmov.service;

import ar.edu.uces.progweb2.booksmov.dto.BookDto;
import ar.edu.uces.progweb2.booksmov.model.Book;

public interface BookConverterService {
	
	BookDto transform(Book book);
}
