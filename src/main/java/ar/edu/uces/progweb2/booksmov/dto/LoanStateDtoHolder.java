package ar.edu.uces.progweb2.booksmov.dto;

import ar.edu.uces.progweb2.booksmov.model.LoanStateEnum;

public class LoanStateDtoHolder {
	
	private LoanStateEnum name;
	private String color;
	private String message;
	
	public LoanStateDtoHolder(LoanStateEnum name, String color, String message) {
		this.name = name;
		this.color = color;
		this.message = message;
	}
	public LoanStateEnum getName() {
		return name;
	}
	public void setName(LoanStateEnum name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
