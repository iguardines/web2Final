package ar.edu.uces.progweb2.booksmov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class RootController {
	
	@RequestMapping(value="/")
	public String goToIndex(){
		return "index";
	}
}
