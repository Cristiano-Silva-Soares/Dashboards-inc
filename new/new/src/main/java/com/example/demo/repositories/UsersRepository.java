package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;


@Repository
public interface UsersRepository extends JpaRepository <User,Long>
{
	public List<User>findAllByUserNameContainingIgnoreCase(String userName);
	
	/**
	 * Método utilizado para pesquisar coluna Email
	 * 
	 * @param email do tipo String
	 * @return Optional com Usuario
	 * @author Cristiano
	 * @since 1.0
	 * 
	 */
	Optional<User> findByEmail(String email);
}
