package ar.edu.uces.progweb2.booksmov.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import ar.edu.uces.progweb2.booksmov.dto.MovieDto;
import ar.edu.uces.progweb2.booksmov.model.Actor;
import ar.edu.uces.progweb2.booksmov.model.Format;
import ar.edu.uces.progweb2.booksmov.model.Movie;
import ar.edu.uces.progweb2.booksmov.model.User;
import ar.edu.uces.progweb2.booksmov.service.ImageService;
import ar.edu.uces.progweb2.booksmov.service.MovieService;
import ar.edu.uces.progweb2.booksmov.validator.MovieValidator;

@Controller
@SessionAttributes("user")
@RequestMapping("/app/movies")
public class MovieController {
	
	@Autowired
	private MovieValidator movieValidator;
	@Autowired
	private MovieService movieService;
	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String addBook(ModelMap model){
		
		MovieDto movieDto = new MovieDto();
		movieDto.setFormats(Arrays.asList(Format.values()));
		model.addAttribute("movieDto", movieDto);
		return "addMovie";
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String addMovie(@ModelAttribute("movieDto") MovieDto movieDto, BindingResult result, ModelMap model) throws IOException{
		
		movieValidator.validate(movieDto, result);
		if(!result.hasErrors()){
			Movie movie = prepareEntity(movieDto, model);
			movieService.save(movie);
			movieDto.setSuccess(true);
			movieDto.clearFields();
		}
		
		return "addMovie";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String editMovie(@PathVariable("id") Long id, ModelMap model){
		MovieDto movieDto = movieService.getMovieById(id);
		parseActorsToString(movieDto);
		model.addAttribute("movieDto", movieDto);
		return "editMovie";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editMovie(@ModelAttribute("movieDto") MovieDto movieDto, BindingResult result, ModelMap model) throws IOException{
		movieValidator.validate(movieDto, result);
		if(!result.hasErrors()){
			Movie movie = prepareEntity(movieDto, model);
			movie.setId(movieDto.getId());
			movieService.update(movie);
			movieDto.setSuccess(true);
		}
		
		return "editMovie";
	}
	
	private void parseActorsToList(String actors, Movie movie) {
		
		String[] result = actors.split(",");
		
		for (int i = 0; i < result.length; i++) {
			Actor actor = new Actor(result[i].trim());
			movie.getActors().add(actor);
		}
	}
	
	private void parseActorsToString(MovieDto movieDto) {
		StringBuilder sb = new StringBuilder();
		List<Actor> actors = movieDto.getActorList();
		for (int i = 0; i < actors.size(); i++) {
			sb.append(actors.get(i).getFullName());
			if(i < actors.size() - 1){
				sb.append(", ");
			}
		}
		movieDto.setActors(sb.toString());
		
	}
	
	private Movie prepareEntity(MovieDto movieDto, ModelMap model) throws IOException {
		User user = (User) model.get("user");
		byte[] image = imageService.manageImage(movieDto);
		
		Movie movie = new Movie(movieDto.getTitle(), movieDto.getRating(),
				movieDto.isAlreadyUsed(), movieDto.isBorrowable(), image, movieDto.getSelectedFormat(), movieDto.getDirector(), user);
		
		String actors = movieDto.getActors();
		if(!StringUtils.isBlank(actors)){
			parseActorsToList(actors, movie);
		}
		return movie;
	}

	
}
