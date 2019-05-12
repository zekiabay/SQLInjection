package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class User {
    @Id
    private int id;
    private  String name;
    private  String surname;
    private  String email;
    private  String password;

}
