package ar.edu.uces.progweb2.booksmov.dto;


public class FilterDto {
	
	
	private String userName;
	private String rating;
	private String title;
	private String type = "all";
	private Boolean borrowable;
	
	public FilterDto(String userName, String rating, String title, String type,
			Boolean borrowable) {
		this.userName = userName;
		this.rating = rating;
		this.title = title;
		this.type = type;
		this.borrowable = borrowable;
	}
	public FilterDto(){
		
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public Boolean getBorrowable() {
		return borrowable;
	}
	public void setBorrowable(Boolean borrowable) {
		this.borrowable = borrowable;
	}
	

}
