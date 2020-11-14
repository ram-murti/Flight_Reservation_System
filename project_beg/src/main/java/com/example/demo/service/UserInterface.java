package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Flight;
import com.example.demo.model.User;

@Service
public interface UserInterface {

	public String saveUser(User user);
	public User findByUsernamePasswords(String username,String password );
	public String updateUser(User user);
	public String deleteUser(long id);
	public Optional<User> findById(long id);

}
