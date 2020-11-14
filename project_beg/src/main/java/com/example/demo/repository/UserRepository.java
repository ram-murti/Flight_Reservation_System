package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
@Repository
public interface UserRepository extends CrudRepository<User, Long>{

public User findByUsernameAndPassword(String username,String password);

}
