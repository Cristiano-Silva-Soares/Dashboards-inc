package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.tomcat.util.codec.binary.Base64;
import java.nio.charset.Charset;

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
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String result = encoder.encode(user.getPassword());
			user.setPassword(result);
			return Optional.ofNullable(repositoryU.save(user));
		});
	}

	public Optional<?> pickCredentials(MirroredUser authenticatedUser) {
		return repositoryU.findByEmail(authenticatedUser.getEmail()).map(existentUser -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			if (encoder.matches(authenticatedUser.getPassword(), existentUser.getPassword())) {

				String basicEstructure = authenticatedUser.getEmail() + ":" + authenticatedUser.getPassword();
				byte[] authorizationBase64 = Base64.encodeBase64(basicEstructure.getBytes(Charset.forName("US-ASCII")));
				String authorizationHeader = "Basic " + new String(authorizationBase64);

				authenticatedUser.setToken(authorizationHeader);
				authenticatedUser.setIdUser(existentUser.getIdUser());
				authenticatedUser.setUserName(existentUser.getUserName());
				authenticatedUser.setPassword(existentUser.getPassword());
				authenticatedUser.setUserPhoto(existentUser.getUserPhoto());
				return Optional.ofNullable(authenticatedUser);

			} else {

				return Optional.empty();
			}

		});
	}

	public Optional<?> updateUser(MirroredUser userUpdate) {

		return repositoryU.findById(userUpdate.getIdUser()).map(existentUser -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String cryptoPassword = encoder.encode(userUpdate.getPassword());

			existentUser.setUserName(userUpdate.getUserName());
			existentUser.setEmail(userUpdate.getEmail());
			existentUser.setPassword(cryptoPassword);
			existentUser.setUserPhoto(userUpdate.getUserPhoto());
			return Optional.ofNullable(repositoryU.save(existentUser));

		}).orElseGet(() -> {

			return Optional.empty();

		});
	}

}
