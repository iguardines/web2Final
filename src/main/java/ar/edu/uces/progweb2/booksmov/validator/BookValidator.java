package ar.edu.uces.progweb2.booksmov.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.uces.progweb2.booksmov.dto.BookDto;

@Component
public class BookValidator implements Validator{
	
	private static final String IMAGE_PATTERN = "(jpg|jpeg|png|gif|bmp)";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return BookDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "errors.book.title.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rating", "errors.book.rating.empty");
		
		BookDto bookDto = (BookDto) object;
		if(!errors.hasFieldErrors("title")){
			if(bookDto.getTitle().length() > 200){
				errors.rejectValue("title", "errors.200.maxlength");
			}
		}
		if(!errors.hasFieldErrors("authors")){
			if(bookDto.getAuthors().length() > 100){
				errors.rejectValue("authors", "errors.100.maxlength");
			}
		}
		MultipartFile multiPartFile = (MultipartFile) bookDto.getImage();
		if(multiPartFile.getSize() > 0){
			Pattern pattern = Pattern.compile(IMAGE_PATTERN);
			Matcher matcher = pattern.matcher(multiPartFile.getContentType().split("/")[1].trim());
			if(!matcher.matches()){
				errors.rejectValue("image", "errors.invalid.image.format");
			}
		}
	}

}
