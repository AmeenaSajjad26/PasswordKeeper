// Course:  CIT-239 Group Project 3
// Program: Password Keeper
// Creator: Team Bluebird: Ameena Sajjad, Uttam Bavarva, ShuWen Zhang
// Created: 4/12/2017
// Modified: 4/19/2017

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PasswordKeeper;

/**
 * Validates string object and throws InvalidException
 * @author ShuWen Zhang
 */
public interface Validate {
    public abstract void validate(String s) throws InvalidException;
}
