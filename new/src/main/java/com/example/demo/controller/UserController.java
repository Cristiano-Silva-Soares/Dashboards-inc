package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.MirroredUser;
import com.example.demo.models.User;
import com.example.demo.repositories.UsersRepository;
import com.example.demo.services.UsersService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UsersRepository repositoryU;

	@Autowired
	private UsersService servicesU;

	@GetMapping("/allusers")
	public ResponseEntity<List<User>> GetAll() {

		List<User> usersObject = repositoryU.findAll();

		if (usersObject.isEmpty()) {

			return ResponseEntity.status(204).build();

		} else {

			return ResponseEntity.status(200).body(usersObject);

		}
	}

	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody User newUser) {

		Optional<Object> optionalObject = servicesU.saveUser(newUser);

		if (optionalObject.isEmpty()) {

			return ResponseEntity.status(400).build();

		} else {
			
			return ResponseEntity.status(201).body(optionalObject.get());

		}
	}

	@PutMapping("/login")
	public ResponseEntity<Object> login(@Valid @RequestBody MirroredUser pickCredentials) {

		Optional<?> optionalObject = servicesU.pickCredentials(pickCredentials);

		if (optionalObject.isPresent()) {

			return ResponseEntity.status(200).body(optionalObject.get());

		} else {

			return ResponseEntity.status(204).build();

		}

	}

	@GetMapping("/id/{id}")
	public ResponseEntity<User> searchId(@PathVariable(value = "id") Long idUser) {

		Optional<User> userObject = repositoryU.findById(idUser);

		if (userObject.isPresent()) {

			return ResponseEntity.status(200).body(userObject.get());

		} else {

			return ResponseEntity.status(204).build();

		}

	}

	@GetMapping("/searchusername/{name}")
	public ResponseEntity<List<User>> searchUsername(@PathVariable(value = "name") String nomeUsuario) {

		List<User> userObject = repositoryU.findAllByUserNameContainingIgnoreCase(nomeUsuario);

		if (userObject.isEmpty()) {

			return ResponseEntity.status(204).build();

		} else {

			return ResponseEntity.status(200).build();

		}

	}

	@PutMapping("/update")
	public ResponseEntity<Object> updateUser(@Valid @RequestBody MirroredUser updateUserData) {

		Optional<?> optionalObject = servicesU.updateUser(updateUserData);

		if (optionalObject.isPresent()) {

			return ResponseEntity.status(201).body(optionalObject.get());

		} else {

			return ResponseEntity.status(204).build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public void userDelete(@PathVariable(value = "id") Long idUser) {

		repositoryU.deleteById(idUser);

	}

}
