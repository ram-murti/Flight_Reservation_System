package com.example.demo.dao;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public interface UserDaointerface {
	public String saveUser(User user);
	public User findByUsernamePassword(String username,String password );
	public String updateUser(User user);
	public String deleteUser(long id);
	public Optional<User> findById(long id);

}
