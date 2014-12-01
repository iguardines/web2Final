package ar.edu.uces.progweb2.booksmov.dto;

public enum LoanStateDto {
	
	PENDING("rgb(255,255,125)", "pending"), ACCEPTED("springgreen", "accepted"), REJECTED("tomato", "rejected"), DELIVERED("lightgray", "delivered");
	
	private LoanStateDto(String color, String name){
		this.color = color;
		this.name = name;
	}
	
	private String name;
	private String color;
	
	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}
	
}
