package ar.edu.uces.progweb2.booksmov.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.uces.progweb2.booksmov.dao.ProductDao;
import ar.edu.uces.progweb2.booksmov.dto.CriteriaSearchDto;
import ar.edu.uces.progweb2.booksmov.dto.FilterDto;
import ar.edu.uces.progweb2.booksmov.dto.ProductDto;
import ar.edu.uces.progweb2.booksmov.dto.SearchResultDto;
import ar.edu.uces.progweb2.booksmov.model.Product;
import ar.edu.uces.progweb2.booksmov.model.SearchResult;
import ar.edu.uces.progweb2.booksmov.service.ProductConverterService;
import ar.edu.uces.progweb2.booksmov.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductConverterService converter;
	
	
	@Override
	public SearchResultDto<ProductDto> getProductsByUserId(Long id, FilterDto filterDto, CriteriaSearchDto criteriaSearch) {
		SearchResult searchResult = productDao.getProductsByUserId(id, filterDto, criteriaSearch);
		SearchResultDto<ProductDto> results = new SearchResultDto<ProductDto>();
		results.setPaginationDetails(searchResult.getPaginationDetails());
		results.setProducts(transform(searchResult.getProducts()));
		return results;
	}

	private List<ProductDto> transform(Collection<? extends Product> products) {
		List<ProductDto> productsDto = new ArrayList<ProductDto>();
		for (Product product : products) {
			productsDto.add(converter.toDto(product));
		}
		
		return productsDto;
	}

	@Override
	public Product getProductById(Long id) {
		return productDao.getProductById(id);
	}

	@Override
	public SearchResultDto<ProductDto> getProductsByCriteria(FilterDto filterDto, CriteriaSearchDto criteriaSearchDto) {
		SearchResult searchResult = productDao.getProductsByCriteria(filterDto, criteriaSearchDto);
		SearchResultDto<ProductDto> results = new SearchResultDto<ProductDto>();
		results.setPaginationDetails(searchResult.getPaginationDetails());
		Set<Product> productsSet = new LinkedHashSet<>(searchResult.getProducts());
		results.setProducts(transform(productsSet));
		return results;
	}

	@Override
	public List<String> getProductsBy(FilterDto filterDto) {
		return productDao.getProductsBy(filterDto);
	}

}
