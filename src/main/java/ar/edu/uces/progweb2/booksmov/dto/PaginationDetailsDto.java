package ar.edu.uces.progweb2.booksmov.dto;

public class PaginationDetailsDto {
	
	private Integer currentPage;
	private Integer maxPage;
	private Integer totalResults;
	private Integer itemsPerPage;
	private Integer begin;
	private Integer end;
	
	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}

	public Integer getTotalResults() {
		return totalResults;
	}

	public void setItemsPerPage(Integer itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public Integer getItemsPerPage() {
		return itemsPerPage;
	}

	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
	}

	public Integer getMaxPage() {
		return maxPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public Integer getBegin() {
		return begin;
	}

	public void setBegin(Integer begin) {
		this.begin = begin;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

}
