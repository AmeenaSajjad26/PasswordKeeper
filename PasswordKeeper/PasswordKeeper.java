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

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author ShuWen Zhang
 */
public class PasswordKeeper extends Application {
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, InvalidException {
           new LoginStage();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
