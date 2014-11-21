package ar.edu.uces.progweb2.booksmov.service;

import ar.edu.uces.progweb2.booksmov.dto.ProductDto;
import ar.edu.uces.progweb2.booksmov.model.Product;

public interface ProductConverterService {
	
	ProductDto toDto(Product product);
}
