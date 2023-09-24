package com.products.service.impl;


import com.products.entity.Role;
import com.products.entity.User;
import com.products.exceptions.ResourceNotFoundException;
import com.products.repository.RoleRepository;
import com.products.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Autowired
	RoleRepository roleRepository ;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUserName(username).orElseThrow(
				() -> new ResourceNotFoundException("User not found with username:" + username));

		return new org.springframework.security.core.userdetails.User(user.getUserName()+"", user.getPassword(),
				getAuthority(user));
	}
	   
	   public  Set<SimpleGrantedAuthority> getAuthority(User user) {
	        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		  Role role = roleRepository.findByRoleId(user.getRoleId()).orElseThrow(
				   () -> new ResourceNotFoundException("Role not found with roleId:" + user.getRoleId()));

		   if (role!=null)
		     authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
	        return authorities;
	    }

	
	
}
