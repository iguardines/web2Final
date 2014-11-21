package ar.edu.uces.progweb2.booksmov.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.uces.progweb2.booksmov.dto.MovieDto;
import ar.edu.uces.progweb2.booksmov.model.Actor;
import ar.edu.uces.progweb2.booksmov.model.Movie;
import ar.edu.uces.progweb2.booksmov.service.MovieConverterService;

@Service
public class MovieConverterServiceImpl implements MovieConverterService {

	@Override
	public MovieDto transform(Movie movie) {
		
		MovieDto movieDto = new MovieDto(movie.getTitle(), movie.getRating(), movie.isAlreadyUsed(), movie.isBorrowable(),
										movie.getImage(), null, movie.getDirector(), movie.getFormat(), movie.getUser().getId());
		List<Actor> actors = new ArrayList<Actor>();
		movieDto.setId(movie.getId());
		actors.addAll(movie.getActors());
		movieDto.setActorList(actors);
		return movieDto;
	}

}
