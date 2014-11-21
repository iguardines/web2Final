package ar.edu.uces.progweb2.booksmov.dto;

import java.util.List;

public class SearchResultDto<T extends ProductDto> {
	
	private List<T> products;
	private PaginationDetailsDto paginationDetails;
	
	public List<T> getProducts() {
		return products;
	}
	public void setProducts(List<T> products) {
		this.products = products;
	}
	public PaginationDetailsDto getPaginationDetails() {
		return paginationDetails;
	}
	public void setPaginationDetails(PaginationDetailsDto paginationDetails) {
		this.paginationDetails = paginationDetails;
	}
	
	
}
