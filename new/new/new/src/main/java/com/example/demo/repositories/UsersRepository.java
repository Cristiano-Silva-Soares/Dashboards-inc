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
	
	Optional<User> findByEmail(String email);
}
