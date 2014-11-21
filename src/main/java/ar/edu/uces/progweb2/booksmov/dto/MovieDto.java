package ar.edu.uces.progweb2.booksmov.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ar.edu.uces.progweb2.booksmov.model.Actor;
import ar.edu.uces.progweb2.booksmov.model.Director;
import ar.edu.uces.progweb2.booksmov.model.Format;

public class MovieDto extends ProductDto{
	
	private String actors;
	private List<Actor> actorList = new ArrayList<Actor>();
	private Director director;
	private	List<Format> formats = new ArrayList<Format>();
	private Format selectedFormat;
	
	public MovieDto(){}

	public MovieDto(String title, String rating, boolean alreadyUsed,
			boolean borrowable, Serializable image,
			String actors, Director director, Format format, Long userId) {
		super(title, rating, alreadyUsed, borrowable, image, userId);
		this.actors = actors;
		this.director = director;
		this.selectedFormat = format;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public List<Format> getFormats() {
		return formats;
	}

	public void setFormats(List<Format> formats) {
		this.formats = formats;
	}

	public Format getSelectedFormat() {
		return selectedFormat;
	}

	public void setSelectedFormat(Format selectedFormat) {
		this.selectedFormat = selectedFormat;
	}

	public List<Actor> getActorList() {
		return actorList;
	}

	public void setActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}

	public void clearFields(){
		this.title = null;
		this.director = null;
		this.rating = null;
		this.borrowable = false;
		this.image = null;
		this.alreadyUsed = false;
		this.actors = null;
		this.selectedFormat = null;
	}
	
	public String getType(){
		return "movie";
	}
	
}
