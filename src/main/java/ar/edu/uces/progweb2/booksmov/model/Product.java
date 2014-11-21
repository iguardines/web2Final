package ar.edu.uces.progweb2.booksmov.model;


public abstract class Product {
	
	protected Long id;
	protected String title;
	protected String rating;
	protected boolean alreadyUsed;
	protected boolean borrowable;
	protected byte[] image;
	protected User user;
	
	public Product(){}
	
	public Product(String title, String rating, boolean alreadyUsed, boolean borrowable, byte[] image, User user) {
		this.title = title;
		this.rating = rating;
		this.alreadyUsed = alreadyUsed;
		this.borrowable = borrowable;
		this.image = image;
		this.user = user;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public boolean isAlreadyUsed() {
		return alreadyUsed;
	}
	public void setAlreadyUsed(boolean alreadyUsed) {
		this.alreadyUsed = alreadyUsed;
	}
	public boolean isBorrowable() {
		return borrowable;
	}
	public void setBorrowable(boolean borrowable) {
		this.borrowable = borrowable;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public abstract String getType();
}
