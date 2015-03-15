package ar.edu.uces.progweb2.booksmov.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.uces.progweb2.booksmov.dao.impl.LoginDaoImpl;
import ar.edu.uces.progweb2.booksmov.model.RoleEnum;
import ar.edu.uces.progweb2.booksmov.model.User;
import ar.edu.uces.progweb2.booksmov.service.LoginService;

public class LoginServiceImpl implements LoginService, UserDetailsService{
	
	private LoginDaoImpl loginDao;
	
	@Override
	public User getUserByCredentials(String name, String password) {
		return loginDao.get(name, password);
	}

	public LoginDaoImpl getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDaoImpl loginDao) {
		this.loginDao = loginDao;
	}
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = loginDao.get(username);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
		return buildUserForAuthentication(user, authorities);
	}
	
	private List<GrantedAuthority> buildUserAuthority(RoleEnum userRole) {
		 
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		setAuths.add(new SimpleGrantedAuthority(userRole.name()));
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		return Result;
	}
	
	private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getName(),
				user.getPassword(), true, true, true, true, authorities);
	}
	
}
