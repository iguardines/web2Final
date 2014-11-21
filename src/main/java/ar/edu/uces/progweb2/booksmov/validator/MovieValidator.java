package ar.edu.uces.progweb2.booksmov.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.uces.progweb2.booksmov.dto.MovieDto;

@Component
public class MovieValidator implements Validator{
	
	private static final String IMAGE_PATTERN = "(jpg|jpeg|png|gif|bmp)";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return MovieDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "errors.movie.title.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rating", "errors.movie.rating.empty");
		
		MovieDto movieDto = (MovieDto) object;
		if(!errors.hasFieldErrors("title")){
			if(movieDto.getTitle().length() > 200){
				errors.rejectValue("title", "errors.200.maxlength");
			}
		}
		if(!errors.hasFieldErrors("actors")){
			if(movieDto.getActors().length() > 100){
				errors.rejectValue("actors", "errors.100.maxlength");
			}
		}
		if(!errors.hasFieldErrors("director.fullName")){
			if(movieDto.getDirector().getFullName().length() > 100){
				errors.rejectValue("director.fullName", "errors.100.maxlength");
			}
		}
		if(movieDto.getSelectedFormat() == null){
			errors.rejectValue("selectedFormat", "errors.movie.format");
		}
		MultipartFile multiPartFile = (MultipartFile) movieDto.getImage();
		if(multiPartFile.getSize() > 0){
			Pattern pattern = Pattern.compile(IMAGE_PATTERN);
			Matcher matcher = pattern.matcher(multiPartFile.getContentType().split("/")[1].trim());
			if(!matcher.matches()){
				errors.rejectValue("image", "errors.invalid.image.format");
			}
		}
		
	}

}
