package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Flight;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserInterface{

	@Autowired
	public UserRepository userRepository;
	@Override
	public String saveUser(User user) {
		// TODO Auto-generated method stub
		try {
			System.out.println("userservice->save");
		userRepository.save(user);
		System.out.println("return");
		return "sucessfully created";
		}
		catch (Exception e){
			System.out.println(e);
			return "something went wrong";
		}
		
	}

	@Override
	public User findByUsernamePasswords(String username, String password) {
		// TODO Auto-generated method stub
		try{
			System.out.println("here");
			System.out.println(username+"hi"+password);
			User user=userRepository.findByUsernameAndPassword(username, password);
		System.out.println("userservicefing");
		System.out.println(user.email);
	
			return user;
		}
		catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public String updateUser(User user) {
		// TODO Auto-generated method stub
		try {
			userRepository.save(user);
			return "sucessfully created";
			}
			catch (Exception e){
				return "something went wrong";
			}
		
	}

	@Override
	public String deleteUser(long id) {
		// TODO Auto-generated method stub
		try {
		userRepository.deleteById(id);
		return "successfully deleted";
		}
		catch(Exception e) {
			return "something went wrong";
		}
		
	}
	@Override
	public Optional<User> findById(long id) {
		// TODO Auto-generated method stub
		try {
		Optional<User> user=userRepository.findById(id);
		System.out.println("service");
		User us=user.get();
		System.out.println(us.getEmail());
		return user;

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	
	}
	
	
}
