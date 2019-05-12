package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UserRepository extends CrudRepository<User, Integer> {

    ArrayList<User> findByEmailAndPassword(String email, String password);
}
