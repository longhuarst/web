package com.clxs.web.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//@Table(name = "user")
public class User {

    @Id
    private  String id;

    private String username;

    private String password;
}
