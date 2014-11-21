package ar.edu.uces.progweb2.booksmov.model;

import java.util.ArrayList;
import java.util.Collection;

import ar.edu.uces.progweb2.booksmov.dto.PaginationDetailsDto;

public class SearchResult {
	
	private Collection<? extends Product> products = new ArrayList<Product>();
	private PaginationDetailsDto paginationDetails;
	
	public Collection<? extends Product> getProducts() {
		return products;
	}
	public void setProducts(Collection<? extends Product> products) {
		this.products = products;
	}
	public PaginationDetailsDto getPaginationDetails() {
		return paginationDetails;
	}
	public void setPaginationDetails(PaginationDetailsDto paginationDetails) {
		this.paginationDetails = paginationDetails;
	}
}
