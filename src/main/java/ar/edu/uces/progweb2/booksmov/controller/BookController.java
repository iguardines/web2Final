package ar.edu.uces.progweb2.booksmov.controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileUploadException;
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

import ar.edu.uces.progweb2.booksmov.dto.BookDto;
import ar.edu.uces.progweb2.booksmov.model.Author;
import ar.edu.uces.progweb2.booksmov.model.Book;
import ar.edu.uces.progweb2.booksmov.model.User;
import ar.edu.uces.progweb2.booksmov.service.BookService;
import ar.edu.uces.progweb2.booksmov.service.ImageService;
import ar.edu.uces.progweb2.booksmov.validator.BookValidator;

@Controller
@SessionAttributes("user")
@RequestMapping("/app/books")
public class BookController {
	
	@Autowired
	private BookValidator bookValidator;
	@Autowired
	private BookService bookService;
	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String addBook(ModelMap model){
		
		model.addAttribute("bookDto", new BookDto());
		return "addBook";
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String addBook(@ModelAttribute("bookDto") BookDto bookDto, BindingResult result, ModelMap model) throws FileUploadException, IOException{
		
		bookValidator.validate(bookDto, result);
		if(!result.hasErrors()){
			Book book = prepareEntity(bookDto, model);
			bookService.save(book);
			bookDto.setSuccess(true);
			bookDto.clearFields();
		}
		
		return "addBook";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String editBook(@PathVariable("id") Long id, ModelMap model){
		BookDto bookDto = bookService.getBookById(id);
		parseAuthorsToString(bookDto);
		model.addAttribute("bookDto", bookDto);
		return "editBook";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editBook(@ModelAttribute("bookDto") BookDto bookDto, BindingResult result, ModelMap model) throws IOException{
		bookValidator.validate(bookDto, result);
		if(!result.hasErrors()){
			Book book = prepareEntity(bookDto, model);
			book.setId(bookDto.getId());
			bookService.update(book);
			bookDto.setSuccess(true);
		}
		
		return "editBook";
	}

	private Book prepareEntity(BookDto bookDto, ModelMap model) throws IOException {
		User user = (User) model.get("user");
		byte[] image = imageService.manageImage(bookDto);
		Book book = new Book(bookDto.getTitle(), bookDto.getRating(),
				bookDto.isAlreadyUsed(), bookDto.isBorrowable(), image, bookDto.getDescription(), user);
		
		String authors = bookDto.getAuthors();
		if(!StringUtils.isBlank(authors)){
			parseAuthorsToList(authors, book);
		}
		return book;
	}
	
	private void parseAuthorsToString(BookDto bookDto) {
		StringBuilder sb = new StringBuilder();
		List<Author> authors = bookDto.getAuthorsList();
		for (int i = 0; i < authors.size(); i++) {
			sb.append(authors.get(i).getFullName());
			if(i < authors.size() - 1){
				sb.append(", ");
			}
		}
		bookDto.setAuthors(sb.toString());
		
	}

	private void parseAuthorsToList(String authors, Book book) {
		
		String[] result = authors.split(",");
		
		for (int i = 0; i < result.length; i++) {
			Author author = new Author(result[i].trim());
			book.getAuthors().add(author);
		}
	}
	
}
