package com.example.demo.security;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.repositories.UsersRepository;

@Service
public class UserDetailsServiceImplements implements UserDetailsService {

	@Autowired
	private UsersRepository repositoryU;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> objectOptional = repositoryU.findByEmail(userName);

		if (objectOptional.isPresent()) {
			return new UserDetailsImplements(objectOptional.get());

		} else {

			throw new UsernameNotFoundException(userName + "Não encontrado!");
		}
	}

}