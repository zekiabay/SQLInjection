package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class userListwithExecuteException {
    ArrayList<User> userList;
    String exception;
    String query;
}
