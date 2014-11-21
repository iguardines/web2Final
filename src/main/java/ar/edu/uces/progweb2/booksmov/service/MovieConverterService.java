package ar.edu.uces.progweb2.booksmov.service;

import ar.edu.uces.progweb2.booksmov.dto.MovieDto;
import ar.edu.uces.progweb2.booksmov.model.Movie;

public interface MovieConverterService {
	
	MovieDto transform(Movie movie);
}
