package ar.edu.uces.progweb2.booksmov.model;

import org.apache.commons.lang.StringUtils;

public class Preference {
	
	private Long id;
	private String language;
	private String country;
	
	public boolean hasLanguage(){
		return validate(this.language);
	}

	public boolean hasCountry(){
		return validate(this.country);
	}

	private boolean validate(String attribute) {
		return attribute != null && !attribute.equals(StringUtils.EMPTY);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
