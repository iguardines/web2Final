package ar.edu.uces.progweb2.booksmov.model;

import java.util.Date;

public class LoanRequest {
	
	private Long id;
	private Product product;
	private String requestDescription;
	private LoanStateEnum state;
	private User requester;
	private User consignee;
	private Date requestDate;
	private Date responseDate;
	private Date deliveryDate;
	
	public LoanRequest(Product product, String requestDescription,
			LoanStateEnum state, User requester, User consignee,
			Date requestDate, Date deliveryDate) {
		this.product = product;
		this.requestDescription = requestDescription;
		this.state = state;
		this.requester = requester;
		this.consignee = consignee;
		this.requestDate = requestDate;
		this.deliveryDate = deliveryDate;
	}
	
	public LoanRequest(){}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getRequestDescription() {
		return requestDescription;
	}
	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}
	public User getRequester() {
		return requester;
	}
	public void setRequester(User requester) {
		this.requester = requester;
	}
	public User getConsignee() {
		return consignee;
	}
	public void setConsignee(User consignee) {
		this.consignee = consignee;
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
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public LoanStateEnum getState() {
		return state;
	}
	public void setState(LoanStateEnum state) {
		this.state = state;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((consignee == null) ? 0 : consignee.hashCode());
		result = prime * result
				+ ((responseDate == null) ? 0 : responseDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result
				+ ((requestDate == null) ? 0 : requestDate.hashCode());
		result = prime
				* result
				+ ((requestDescription == null) ? 0 : requestDescription
						.hashCode());
		result = prime * result
				+ ((requester == null) ? 0 : requester.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanRequest other = (LoanRequest) obj;
		if (consignee == null) {
			if (other.consignee != null)
				return false;
		} else if (!consignee.equals(other.consignee))
			return false;
		if (responseDate == null) {
			if (other.responseDate != null)
				return false;
		} else if (!responseDate.equals(other.responseDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (requestDate == null) {
			if (other.requestDate != null)
				return false;
		} else if (!requestDate.equals(other.requestDate))
			return false;
		if (requestDescription == null) {
			if (other.requestDescription != null)
				return false;
		} else if (!requestDescription.equals(other.requestDescription))
			return false;
		if (requester == null) {
			if (other.requester != null)
				return false;
		} else if (!requester.equals(other.requester))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	
	
}
