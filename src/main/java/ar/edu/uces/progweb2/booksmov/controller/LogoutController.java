package ar.edu.uces.progweb2.booksmov.controller;

import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/app/logout")
public class LogoutController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		session.invalidate();
		@SuppressWarnings("unchecked")
		Enumeration<Locale> locales = request.getLocales();
        if (locales.hasMoreElements()) {
            Locale locale = (Locale) locales.nextElement();
            LocaleResolver slr = RequestContextUtils.getLocaleResolver(request);
			slr.setLocale(request, response, locale);
        }
		return "index";
	}
}
