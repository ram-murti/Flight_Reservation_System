package com.example.demo.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Flight;
import com.example.demo.model.User;
import com.example.demo.service.UserInterface;

@Service
public class UserDaoImpl implements UserDaointerface{
    @Autowired
	public UserInterface service;
	@Override
	public String saveUser(User user) {
		// TODO Auto-generated method stub
		System.out.println("daoimpl");
		String message=service.saveUser(user);
		return message;
	}

	@Override
	public User findByUsernamePassword(String username, String password) {
		
		// TODO Auto-generated method stub
		System.out.println("finddaoimpl");
		try{User user=service.findByUsernamePasswords(username, password);
		
			return user;
		}
		catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public String updateUser(User user) {
		// TODO Auto-generated method stub
		String message=service.saveUser(user);
		return message;
		
	}

	@Override
	public String deleteUser(long id) {
		// TODO Auto-generated method stub
		String message=service.deleteUser(id);
		return message;
	}

	
	public Optional<User> findById(long id) {
		// TODO Auto-generated method stub
		try {
			Optional<User> user=service.findById(id);
             return user; 
		} catch (Exception e) {
			// TODO: handle exception
			return null;

		}
	}
}
