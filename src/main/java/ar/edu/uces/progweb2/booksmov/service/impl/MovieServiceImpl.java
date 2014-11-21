package ar.edu.uces.progweb2.booksmov.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.uces.progweb2.booksmov.dao.MovieDao;
import ar.edu.uces.progweb2.booksmov.dto.MovieDto;
import ar.edu.uces.progweb2.booksmov.model.Movie;
import ar.edu.uces.progweb2.booksmov.service.MovieService;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieDao movieDao;
	@Autowired
	private MovieConverterServiceImpl converter;
	
	@Override
	public void save(Movie movie) {
		movieDao.save(movie);
		
	}

	@Override
	public List<MovieDto> getMoviesByUserId(Long id) {
		List<Movie> movies = movieDao.getMovies(id);
		return transform(movies);
	}

	private List<MovieDto> transform(Collection<Movie> movies) {
		List<MovieDto> movieDtos = new ArrayList<MovieDto>();
		
		for (Movie movie : movies) {
			movieDtos.add(converter.transform(movie));
		}
		return movieDtos;
	}

	@Override
	public void update(Movie movie) {
		movieDao.update(movie);
		
	}

	@Override
	public MovieDto getMovieById(Long id) {
		Movie movie = movieDao.getMovieById(id);
		return converter.transform(movie);
	}

}
