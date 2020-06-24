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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Sets the stage for the program's Log-in window.
 * User can register for new account.
 * @author ShuWen Zhang
 */
public class LoginStage extends Stage {
    
    ArrayList<User> userList = new ArrayList<>();

    /**
     * Constructor for LoginStage
     * @throws FileNotFoundException 
     */
    public LoginStage() throws FileNotFoundException, InvalidException {

        System.out.println("Initial size of userlist: " + userList.size());

        /**
         * If user_list.txt (database of account for the program) exists, 
         * read data into array list of users. Used to check if user account exists,
         * and if username/password is correct.
         */ 
        if (new File("user_list.txt").exists())  {
            try (
                    Scanner readIn = new Scanner(new File("user_list.txt"));
            )    {
                while (readIn.hasNext()) {
                    String tempUsername = readIn.next();
                    ComplexPassword tempPW = new ComplexPassword(readIn.next());
                    // Add to ArrayList<User> userList
                    userList.add(new User(tempUsername, tempPW));
                }
                
                System.out.println("userList: " + userList);
                
            }
        }
        
        System.out.println("Size of userList after reading in file:" + userList.size());

        /**
         * Set up scene and nodes
         */
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);   // set horizontal gaps b/t columns
        grid.setVgap(10);   // set vertical gaps b/t rows
        grid.setPadding(new Insets(35, 35, 35, 35));    // set margins around entire grid
        Scene scene = new Scene(grid, 450, 300);

        // Add text, labels, textfields
        Text scenetitle = new Text("Welcome to the Password Keeper");
        scenetitle.setStyle("-fx-font-size: 18px;");
        Label userName = new Label("Username:");
        TextField userTextField = new TextField();
        Label pw = new Label("Password:");
        PasswordField pwBox = new PasswordField();  // masks text
        final Text message = new Text();            // display update, error messages, etc.
        
        // Add to grid
        grid.add(scenetitle, 0, 0, 2, 1);   // Add to col 0, row 0, span 2 columns, span 1 row
        grid.setHalignment(scenetitle, HPos.CENTER);
        grid.add(userName, 0, 2);
        grid.add(userTextField, 1, 2);
        grid.add(pw, 0, 3);
        grid.add(pwBox, 1, 3);
        grid.add(message, 0, 4, 2, 1);
        grid.setHalignment(message, HPos.CENTER);
       

        // Add sign in button to horizontal box and add to grid
        Button signIn = new Button("Sign In");
        HBox hboxSignIn = new HBox(10);
        hboxSignIn.setAlignment(Pos.CENTER);
        hboxSignIn.getChildren().add(signIn);
        grid.add(hboxSignIn, 0, 5, 2, 1);
        
        // Button for registering new user
        // Create a new horizontal box to place in bottom right hand corner, then add to grid -- easier to format
        Text noLogin = new Text("New user? Register for a log-in.");
        noLogin.setFont(Font.font("Arial", FontPosture.REGULAR, 12));
        noLogin.setFill(Color.BLUE);
        grid.add(noLogin, 0, 7, 2, 1);
        grid.setHalignment(noLogin, HPos.CENTER);

        Button register = new Button("Register");    
                Button close = new Button("Close");

        HBox hboxRegisterClose = new HBox(10);
        hboxRegisterClose.setAlignment(Pos.CENTER);
        hboxRegisterClose.getChildren().addAll(register, close);
        grid.add(hboxRegisterClose, 0, 8, 2, 1);
        
//        HBox hboxClose = new HBox(10);
//        hboxClose.setAlignment(Pos.CENTER);
//        hboxClose.getChildren().add(close);
//        grid.add(hboxClose, 0, 9, 2, 1);

        grid.setGridLinesVisible(false);

        // Add scene to stage
        this.setTitle("Password Keeper");
        this.setScene(scene);
        this.show();

        //Add action event for signIn button control
        signIn.setOnAction(e -> {
            message.setFont(Font.font("Arial", FontPosture.ITALIC, 12));
            message.setFill(Color.FIREBRICK);
            
            // If user left any field empty, display error message
            if (userTextField.getText().isEmpty() || pwBox.getText().isEmpty()) {
                message.setText("All fields must be filled.");
            }
            
            // Call usernameExists. Display error message is false
            else if (!usernameExists(userTextField.getText())) {
                message.setText("Username doesn't exist. ");
            }
            
            // Call passwordMatchesIndex to see if username/password exists.
            // Function returns -1 if doesn't exist
            else if (passwordMatchesIndex(userTextField.getText(), pwBox.getText()) == -1) {
                message.setText("Incorrect username or password.");
            }
            
            else {
                int index = passwordMatchesIndex(userTextField.getText(), pwBox.getText());
                System.out.println("Index of user in userList:" + index);
                
                // If user account exists, launch the MainMenuStage
                // Pass the user's account information to it
                try {
                    MainMenuStage keeperStage = new MainMenuStage(userList.get(index));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(LoginStage.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                message.setText("Sign-in successful.");
                userTextField.clear();
                pwBox.clear();
            }
             
            System.out.println("Sign in button pressed on stage"); // prints in console too
        });
        
        // Add action event for register button control
        register.setOnAction(e -> {
            // Launch RegisterStage to register new user
            // Passes userList array to the stage (so it can see which accounts are already existing)
            RegisterStage registerStage = new RegisterStage(userList);
            userTextField.clear();
            pwBox.clear();
            System.out.println("Register button pressed on Login Stage.");
        });
        
        close.setOnAction(e-> {
           close(); 
        });

    }
    
    /**
     * Checks if username exists by traversing through userList (ArrayList of Users)
     * @param username String, username entered by user
     * @return true if exists, false if doesn't exist
     */
    public boolean usernameExists(String username) {
        for (User user: userList) {
            if (user.getUsername().equals(username))
                return true;
        }
        return false;
    }
    
    /**
     * Checks if username and password matches. 
     * @param username String
     * @param password String
     * @return index of User in the ArrayList if matches. Else, returns -1
     */
    public int passwordMatchesIndex(String username, String password) {
        for (User user: userList) {
            if (user.getUsername().equals(username) 
                    && user.getPW().equals(password))
                return userList.indexOf(user);
        }
        return -1;
    }

}
