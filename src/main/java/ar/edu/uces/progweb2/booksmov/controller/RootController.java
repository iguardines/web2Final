package ar.edu.uces.progweb2.booksmov.controller;

import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import ar.edu.uces.progweb2.booksmov.model.User;

@Controller
@RequestMapping(value="/")
public class RootController {
	
	@RequestMapping(value="/")
	public String goToIndex(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession(); 
		User user = (User) session.getAttribute("user");
		if(user == null){
			Enumeration<Locale> locales = request.getLocales();
	        if (locales.hasMoreElements()) {
	            Locale locale = (Locale) locales.nextElement();
	            LocaleResolver slr = RequestContextUtils.getLocaleResolver(request);
				slr.setLocale(request, response, locale);
	        }
		}

		return "index";
	}
}
