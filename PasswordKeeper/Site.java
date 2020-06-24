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
public class Site {
    private String url;
    private String siteUsername;
    private SimplePassword sitePW = new SimplePassword();
    private String notes = "";
    
    public Site() {};   // default constructor needed?// default constructor needed?// default constructor needed?// default constructor needed?// default constructor needed?// default constructor needed?// default constructor needed?// default constructor needed?
    
    public Site(String url, String siteUsername, String pw) {
        setURL(url);
        setSiteUsername(siteUsername);
        setSitePW(pw);
    }
    
    public Site(String url, String siteUsername, String pw, String notes) {
        setURL(url);
        setSiteUsername(siteUsername);
        setSitePW(pw);
        if (this.notes == "" || this.notes == null)
            setNotes("N/A");
        else
            setNotes(notes);
    }
    
    // throws exceptions if 
    // 1) format is wrong
    // 2) url doesn't exist
    public void setURL(String url) {
        this.url = url;
    }
    
    public void setSiteUsername(String siteUsername) {
        this.siteUsername = siteUsername;
    }
    
    // Construct new SimplePassword object for member field using string
    public void setSitePW(String pw) {
            try {
                this.sitePW.setPassword(pw);
            }
            catch (InvalidException ex) {}
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public String getURL() {
        return url;
    }
    
    public String getSiteUsername() {
        return siteUsername;
    }
    
    public String getSitePW() {
        return sitePW.getPassword();    // return password object, or the string?
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void displaySite() {
        System.out.println("Website:\t" + url);
        System.out.println("Username:\t" + siteUsername);
        System.out.println("Password:\t" + getSitePW());      
        System.out.println("Notes:\t\t" + (notes.equals("N/A") ? "": notes));
    }
    
    public String pringSiteString() {
        return "Website:\t\t" + url + 
                "\nUsername:\t" + siteUsername + 
                "\nPassword:\t" + getSitePW() +  
                "\nNotes:\t" + (notes.equals("N/A") ? "": notes) + "\n\n";
    }
    
    // For saving to file; use tab as delimiter?
    public String toString() {
        return url + "\t" + siteUsername + "\t" + getSitePW() + "\t" + notes;          
    }
    
}
