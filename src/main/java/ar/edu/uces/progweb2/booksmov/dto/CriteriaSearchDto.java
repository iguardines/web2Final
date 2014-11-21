package ar.edu.uces.progweb2.booksmov.dto;

public class CriteriaSearchDto {
	
	private Integer page;
	private String order;
	private boolean rating;
	
	public CriteriaSearchDto(Integer page, String order, boolean rating) {
		this.page = page;
		this.order = order;
		this.rating = rating;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public boolean isRating() {
		return rating;
	}
	public void setRating(boolean rating) {
		this.rating = rating;
	}
	
	public String getPropertyForOrder(){
		return rating ? "rating" : "title";
	}
}
