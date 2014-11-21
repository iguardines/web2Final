package ar.edu.uces.progweb2.booksmov.dao;

import ar.edu.uces.progweb2.booksmov.dto.CriteriaSearchDto;
import ar.edu.uces.progweb2.booksmov.dto.FilterDto;
import ar.edu.uces.progweb2.booksmov.model.Product;
import ar.edu.uces.progweb2.booksmov.model.SearchResult;

public interface ProductDao {
	
	SearchResult getProductsByUserId(Long id, FilterDto filterDto, CriteriaSearchDto criteriaSearch);
	SearchResult getProductsByCriteria(FilterDto filterDto, CriteriaSearchDto criteriaSearchDto);
	Product getProductById(Long id);
}
