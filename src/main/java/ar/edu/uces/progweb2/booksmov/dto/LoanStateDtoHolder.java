package ar.edu.uces.progweb2.booksmov.dto;

import ar.edu.uces.progweb2.booksmov.model.LoanStateEnum;

public class LoanStateDtoHolder {
	
	private LoanStateEnum name;
	private String color;
	private String message;
	private String responseDate;
	private String deliveryDate;
	
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
	public String getResponseDate() {
		return responseDate;
	}
	public void setResponseDate(String responseDate) {
		this.responseDate = responseDate;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
}
