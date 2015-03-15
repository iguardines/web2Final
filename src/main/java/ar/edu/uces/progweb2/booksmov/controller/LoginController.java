package ar.edu.uces.progweb2.booksmov.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import ar.edu.uces.progweb2.booksmov.dto.UserDto;
import ar.edu.uces.progweb2.booksmov.model.Preference;
import ar.edu.uces.progweb2.booksmov.model.User;
import ar.edu.uces.progweb2.booksmov.service.LoginService;
import ar.edu.uces.progweb2.booksmov.validator.UserValidator;

@Controller
@SessionAttributes("user")
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserValidator userValidator;
	
	//@Autowired
	private LoginService loginService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String processLogin(ModelMap model, Locale locale, HttpServletRequest request, HttpServletResponse response){
		User user = (User) model.get("user");
		if(user == null){
			LocaleResolver slr = RequestContextUtils.getLocaleResolver(request);
			slr.setLocale(request, response, locale);
			model.addAttribute("userDto", new UserDto());
			return "login";
		}
		return "index";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String processLogin(@ModelAttribute("userDto") UserDto userDto, BindingResult result, HttpServletRequest request, HttpServletResponse response, Locale locale){
		userValidator.validate(userDto, result);
        LocaleResolver slr = RequestContextUtils.getLocaleResolver(request);
		slr.setLocale(request, response, locale);
       
		if(!result.hasErrors()){
			User user = loginService.getUserByCredentials(userDto.getJ_username(), userDto.getJ_password());
			if(user != null){
				resolveLocale(request, response, user);
				request.getSession().setAttribute("user", user);
				return "redirect:/app/search";
			}else{
				result.rejectValue("name", "errors.login.bad.credentials");
			}
		}
		return "login";
	}

	private void resolveLocale(HttpServletRequest request,	HttpServletResponse response, User user) {
		Preference preference = user.getPreferences();
		Locale locale = null;
						
		if(preference.hasLanguage()){
			if(!preference.hasCountry()){
				locale = new Locale(preference.getLanguage());
			}else{
				locale = new Locale(preference.getLanguage(), preference.getCountry());
			}
			
			LocaleResolver slr = RequestContextUtils.getLocaleResolver(request);
			slr.setLocale(request, response, locale);
		}
	}
}
