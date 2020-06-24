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

//    public class InvalidPasswordException extends Exception {
//
//        public InvalidPasswordException(String message) {
//            super(message);
//        }
//    }

/**
 * InvalidException, to be thrown when an object is invalid, depending on its validate() function
 * @author ShuWen Zhang
 */
    public class InvalidException extends Exception {

        public InvalidException(String message) {
            super(message);
        }
    }