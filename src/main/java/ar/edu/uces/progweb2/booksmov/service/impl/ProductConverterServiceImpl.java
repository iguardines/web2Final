package ar.edu.uces.progweb2.booksmov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.uces.progweb2.booksmov.dto.ProductDto;
import ar.edu.uces.progweb2.booksmov.model.Book;
import ar.edu.uces.progweb2.booksmov.model.Movie;
import ar.edu.uces.progweb2.booksmov.model.Product;
import ar.edu.uces.progweb2.booksmov.service.BookConverterService;
import ar.edu.uces.progweb2.booksmov.service.MovieConverterService;
import ar.edu.uces.progweb2.booksmov.service.ProductConverterService;

@Service
public class ProductConverterServiceImpl implements ProductConverterService{

	@Autowired
	private BookConverterService bookConverter;
	@Autowired
	private MovieConverterService movieConverter;
	
	@Override
	public ProductDto toDto(Product product) {
		
		if(product instanceof Book){
			Book book = (Book) product;
			return bookConverter.transform(book);
		}else{
			Movie movie = (Movie) product;
			return movieConverter.transform(movie);
		}
	}

}
