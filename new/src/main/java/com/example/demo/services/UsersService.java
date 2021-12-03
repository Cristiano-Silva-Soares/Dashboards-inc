package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.MirroredUser;
import com.example.demo.models.User;
import com.example.demo.repositories.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository repositoryU;

	public Optional<Object> saveUser(User user) {
		return repositoryU.findByEmail(user.getEmail()).map(existentUser -> {
			return Optional.empty();
		}).orElseGet(() -> {
			return Optional.ofNullable(repositoryU.save(user));
		});
	}

	public Optional<?> pickCredentials(MirroredUser authenticatedUser) {
		return repositoryU.findByEmail(authenticatedUser.getEmail()).map(existentUser -> {

				authenticatedUser.setIdUser(existentUser.getIdUser());
				authenticatedUser.setUserName(existentUser.getUserName());
				authenticatedUser.setPassword(existentUser.getPassword());
				authenticatedUser.setUserPhoto(existentUser.getUserPhoto());
				return Optional.ofNullable(authenticatedUser);

		});
	}

	public Optional<?> updateUser(MirroredUser userUpdate) {

		return repositoryU.findById(userUpdate.getIdUser()).map(existentUser -> {
			

			existentUser.setUserName(userUpdate.getUserName());
			existentUser.setEmail(userUpdate.getEmail());
			existentUser.setPassword(userUpdate.getPassword());
			existentUser.setUserPhoto(userUpdate.getUserPhoto());
			return Optional.ofNullable(repositoryU.save(existentUser));
			
		}).orElseGet(() -> {

			return Optional.empty();

		});
	}

}
