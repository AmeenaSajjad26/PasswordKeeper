// Course:  CIT-239 Group Project 3
// Program: Password Keeper
// Creator: Team Bluebird: Ameena Sajjad, Uttam Bavarva, ShuWen Zhang
// Created: 4/12/2017
// Modified: 4/19/2017

package PasswordKeeper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ShuWen Zhang
 */
public class SimplePassword extends Password {
    
    /**
     * Default constructor, simply calls super constructor
     */
    public SimplePassword() {
        super();
    }
    
    /**
     * Constructor with argument, simply calls super constructor
     * @param password String
     */
    public SimplePassword(String password) {
        super(password);
    }
    
    /**
     * Simple password doesn't require validation--doesn't do anything
     */
    @Override 
    public void validate(String password) {
    
    };
}
