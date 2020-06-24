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
public class ComplexPassword extends Password {

    /**
     * Default constructor, calls super constructor
     */
    public ComplexPassword() {
        super();
    }

    /**
     * Constructor sets member field password, calls super constructor
     * @param password String
     */
    public ComplexPassword(String password){
        super(password);
    }

    /**
     * Overrides superclass's setPassword function, calls validate() to check that 
     *  argument is a valid password before setting it as the object's password member field
     * @param password String
     * @throws InvalidException 
     */
    @Override
    public void setPassword(String password) throws InvalidException {
        try {
            validate(password);
            super.setPassword(password);  
        }
        catch (InvalidException ex) {
            throw ex;
        }
        
    }

    /**
     * Validates password. Password must be 1) 8-10 alphanumeric characters, 
     * 2) at least 1 capital letter, 3) at least 1 digit, 
     * 4) at least one non-space special character
     * @param password
     * @throws InvalidException 
     */
    @Override
    public void validate(String password) throws InvalidException {
         int numCap = 0, numSC = 0, numDig = 0, numWS = 0;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            // Count number of capital letters
            if (Character.isUpperCase(ch))
                numCap++;

            // Count number of digits
            if (Character.isDigit(ch)) 
                numDig++;

            // Count number of special characters
            if (!(Character.isLetterOrDigit(ch) || Character.isWhitespace(ch)))
                numSC++;

            // Count number of white spaces
            if (Character.isWhitespace(ch)) 
                numWS++;
        }

        // Create new InvalidExceptions with appropriate messages
        if (password.length() < 8 || password.length() > 10)
            throw new InvalidException("Password must be have 8 to 10 characters.");

        if (numCap < 1) 
            throw new InvalidException("Password must have at least 1 capital letter.");

        if (numDig < 1) 
            throw new InvalidException("Password must have at least 1 number.");

        if (numSC < 1)
            throw new InvalidException("Password must have at least 1 special chararacter.");

        if (numWS > 0) 
            throw new InvalidException("Password must not contain any white space.");
    }
    }
    
//    public void validatePassword(String password) throws InvalidException {
//        int numCap = 0, numSC = 0, numDig = 0, numWS = 0;
//
//        // 8-10 alphanumeric
//        // at least one capital letter
//        // at least one digit
//        // at least one special character 
//        // Count number of capital letters
//        for (int i = 0; i < password.length(); i++) {
//            char ch = password.charAt(i);
//
//            // Count number of capital letters
//            if (Character.isUpperCase(ch))
//                numCap++;
//
//            // Count number of digits
//            if (Character.isDigit(ch)) 
//                numDig++;
//
//            // Count number of special characters
//            if (!(Character.isLetterOrDigit(ch) || Character.isWhitespace(ch)))
//                numSC++;
//
//            // Count number of white spaces
//            if (Character.isWhitespace(ch)) 
//                numWS++;
//        }
//
//        if (password.length() < 8 || password.length() > 10)
//            throw new InvalidException("Password must be have 8 to 10 characters.");
//
//        if (numCap < 1) 
//            throw new InvalidException("Password must have at least 1 capital letter.");
//
//        if (numDig < 1) 
//            throw new InvalidException("Password must have at least 1 number.");
//
//        if (numSC < 1)
//            throw new InvalidException("Password must have at least 1 special chararacter.");
//
//        if (numWS > 0) 
//            throw new InvalidException("Password must not contain any white space.");
//    }

