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


import java.util.ArrayList;

/**
 *
 * @author ShuWen Zhang
 */

public class User implements Validate {
    private String username;
    private ComplexPassword userPW;
    private ArrayList<Site> websites = new ArrayList<>();
    
    public User() {};

    // validate that username not already taken
    public User(String username, ComplexPassword userPW) throws InvalidException {
        setUsername(username);
        setPW(userPW);
    }
    
//    public User(String username, ComplexPassword userPW, ArrayList<Site> websites) {
//        
//    }
    
    public void setUsername(String username) throws InvalidException {
        try {
            validate(username);
            this.username = username;
        }
        catch (InvalidException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }
    
    /** 
     * Can't have whitespace in username
     * @param username 
     */
    @Override
    public void validate(String username) throws InvalidException {
        int numWS = 0;

        for (int i = 0; i < username.length(); i++) {
            char ch = username.charAt(i);

            if (Character.isWhitespace(ch)) 
                numWS++;
        }

        if (numWS > 0) 
            throw new InvalidException("Username must not contain any white space.");
    }
    
    public void setPW(ComplexPassword userPW) {
        this.userPW = userPW;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPW() {
        return userPW.getPassword();
    }
    
    public ArrayList<Site> getWebsites() {
        return websites;
    }
    
    public void addWebsite(Site website) {
        websites.add(website);
    }
    
    public void deleteWebsite(Site website) {
        websites.remove(website);
    }
    
    public void printAllWebsites() {
        for (Site site : websites) {
            System.out.println(site.toString());
        }
    }
    
    public String allWebsitesToString() {
        String temp = "";
        for (Site site: websites) {
            temp += site.pringSiteString() + "\n";
        }
        return temp;
    }
        
    
}

