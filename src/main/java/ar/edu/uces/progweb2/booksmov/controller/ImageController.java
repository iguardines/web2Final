package ar.edu.uces.progweb2.booksmov.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.edu.uces.progweb2.booksmov.service.ImageService;

@Controller
@RequestMapping("/app/image")
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value="/book/{id}", method=RequestMethod.GET)
	@ResponseBody
	public byte[] getBookImage(@PathVariable("id") Long id) throws IOException{
		return imageService.getBookImageFromDb(id);
	}
	
	@RequestMapping(value="/movie/{id}", method=RequestMethod.GET)
	@ResponseBody
	public byte[] getMovieImage(@PathVariable("id") Long id) throws IOException{
		return imageService.getMovieImageFromDb(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public byte[] getProductImage(@PathVariable("id") Long id) throws IOException{
		return imageService.getProductImageFromDb(id);
	}
}
