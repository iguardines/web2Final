package ar.edu.uces.progweb2.booksmov.service;

import java.util.List;

import ar.edu.uces.progweb2.booksmov.dto.MovieDto;
import ar.edu.uces.progweb2.booksmov.model.Movie;

public interface MovieService {
	
	void save(Movie movie);
	void update(Movie movie);
	List<MovieDto> getMoviesByUserId(Long id);
	MovieDto getMovieById(Long id);
}
