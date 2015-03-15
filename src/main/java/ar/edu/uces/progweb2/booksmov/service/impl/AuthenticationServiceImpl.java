package ar.edu.uces.progweb2.booksmov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import ar.edu.uces.progweb2.booksmov.model.User;
import ar.edu.uces.progweb2.booksmov.service.AuthenticationService;
import ar.edu.uces.progweb2.booksmov.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	
	@Autowired
	private UserService userService;
	
	@Override
	public User getLoggedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		return userService.getUserByName(userDetails.getUsername());
	}

}
