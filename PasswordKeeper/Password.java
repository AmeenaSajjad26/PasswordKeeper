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
 * Abstract class, implement Valid interface and throws InvalidException
 * @author ShuWen Zhang
 */
public abstract class Password implements Validate {

    private String password;

    /**
     * Default constructor, sets password to be empty string
     */
    protected Password() {
        try {
            setPassword("");    //
        } catch (InvalidException ex) {

        }
    }

    /**
     * Constructs password with specified string
     * @param password String
     */
    protected Password(String password) {
        try {
            setPassword(password);
        } catch (InvalidException ex) {

        }
    }

    public String getPassword() {
        return this.password;
    }

    /**
     * Set password String. Throws InvalidException. May be overridden in subclasses.
     * @param password String
     * @throws InvalidException 
     */
    public void setPassword(String password) throws InvalidException {
        this.password = password;
    }

    public void printPassword() {
        System.out.print(password);
    }

}