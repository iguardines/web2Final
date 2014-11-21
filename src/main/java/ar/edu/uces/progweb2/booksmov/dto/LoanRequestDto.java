package ar.edu.uces.progweb2.booksmov.dto;

import java.util.Date;

import ar.edu.uces.progweb2.booksmov.model.LoanStateEnum;

public class LoanRequestDto {
	
	private Long id;
	private Long productId;
	private String requestDescription;
	private LoanStateEnum state;
	private Long requesterId;
	private Long consigneeId;
	private Date requestDate;
	private Date responseDate;
	
	public LoanRequestDto(Long productId, String requestDescription,
			LoanStateEnum state, Long requesterId, Long consigneeId,
			Date requestDate, Date responseDate) {
		this.productId = productId;
		this.requestDescription = requestDescription;
		this.state = state;
		this.requesterId = requesterId;
		this.consigneeId = consigneeId;
		this.requestDate = requestDate;
		this.responseDate = responseDate;
	}
	
	public LoanRequestDto(){}
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getRequestDescription() {
		return requestDescription;
	}

	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}

	public LoanStateEnum getState() {
		return state;
	}

	public void setState(LoanStateEnum state) {
		this.state = state;
	}

	public Long getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(Long requesterId) {
		this.requesterId = requesterId;
	}

	public Long getConsigneeId() {
		return consigneeId;
	}

	public void setConsigneeId(Long consigneeId) {
		this.consigneeId = consigneeId;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
