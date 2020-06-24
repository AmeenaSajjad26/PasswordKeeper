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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Sets the stage for the program's Register window.
 * User can register for new account.
 * @author ShuWen Zhang
 */
public class RegisterStage extends Stage {

    ArrayList<User> userList;

    /**
     * Constructor for RegisterStage. 
     * Its member field userList will hold information regarding existing users.
     * @param userList  ArrayList of Users
     */
    public RegisterStage(ArrayList<User> userList) {
        
        this.userList = userList;

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);   // set horizontal gaps b/t columns
        grid.setVgap(10);   // set vertical gaps b/t rows
        grid.setPadding(new Insets(35, 35, 35, 35));    // set margins around entire grid
        Scene scene = new Scene(grid, 450, 300);

        // Add text, labels, textfields
        Text scenetitle = new Text("Create your log-in");
        scenetitle.setStyle("-fx-font-size: 16px;");
        Label userName = new Label("User Name:");
        TextField userTextField = new TextField();
        Label pw = new Label("Password:");
        PasswordField pwBox = new PasswordField();
        Label pw2 = new Label("Enter password again:");
        PasswordField pwBox2 = new PasswordField();
        Text message = new Text();
        
        // Add to the grid
        grid.add(scenetitle, 0, 0, 2, 1);
        grid.setHalignment(scenetitle, HPos.CENTER);
        grid.add(userName, 0, 2);
        grid.add(userTextField, 1, 2);
        grid.add(pw, 0, 3);
        grid.add(pwBox, 1, 3);
        grid.add(pw2, 0, 4);
        grid.add(pwBox2, 1, 4);
        grid.add(message, 0, 5, 2, 1);
        grid.setHalignment(message, HPos.CENTER);
        
        // Create button for registering
        Button register = new Button("Register");
        HBox hboxRegister = new HBox(10);
        hboxRegister.setAlignment(Pos.CENTER);
        hboxRegister.getChildren().add(register);
        grid.add(hboxRegister, 0, 6, 2, 1);

        // Create button for closing registration window
        Button close = new Button("Close");
        HBox hboxClose = new HBox(10);
        hboxClose.setAlignment(Pos.CENTER);
        hboxClose.getChildren().add(close);
        grid.add(hboxClose, 0, 7, 2, 1);

        // Add scene to stage
        this.setTitle("Password Keeper");
        this.setScene(scene);
        this.show();

        // Add action event for button control
        register.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                message.setFont(Font.font("Arial", FontPosture.ITALIC, 12));
                message.setFill(Color.FIREBRICK);

                System.out.println("pwBox: " + pwBox.getText()); // prints in console too
                System.out.println("pwBox2: " + pwBox2.getText()); // prints in console too
                
                // Construct ComplexPassword object
                ComplexPassword password = new ComplexPassword();

                // Check if username field is empty
                if (userTextField.getText().isEmpty()) {
                    message.setText("Enter a username.");
                }
                
                // Call usernameTaken() to check if username is available or not
                else if (usernameTaken(userTextField.getText())) {
                    message.setText("Username already taken. Try another one.");
                }
                
                else {
                    // First check if text in the two password fields are equivalent
                    if (!(pwBox.getText().equals(pwBox2.getText()))) {
                        message.setText("Passwords don't match.");
                    } else {
                        
                        // Construct a new User object, and add to ArrayList
                        try {
                            password.setPassword(pwBox.getText());
                            User usertemp = new User(userTextField.getText(), password);
                            userList.add(usertemp);
                            message.setText("Successfully registered.\n");
                            System.out.println("Username: " + usertemp.getUsername()
                                    + "\nPassword: " + usertemp.getPW());
                            
                            // Once registered, disable the register button
                            register.setDisable(true);

                            // Update User database by writing ArrayList to file
                            try (
                                // Create a file
                                PrintWriter output = new PrintWriter(new File("user_list.txt"));
                             ) {
                                // Write formatted output to the file
                                for (User user : userList) {
                                    // for (int i = 0; i < userList.size()-1; i++) {
                                    output.println(user.getUsername() + "\t" + user.getPW());
                                }
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(RegisterStage.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        } catch (InvalidException ex) {
                            message.setText(ex.getMessage());
                        }
                    }
                }

                System.out.println("Register button pressed on Register stage"); // prints in console too
                System.out.println("Size of userList: " + userList.size() + 
                        "\nPrinting userList contents:");
                for (User user: userList) {
                    System.out.println(user.getUsername() + " " + user.getPW());

                }
            }
        });

        // Add action event for button control
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Close button pressed on stage."); // prints in console too
                close();
            }
        });

    }

    /**
     * Check if username is taken by comparing against ArrayList of Users
     * @param PKusername String, username entered by user
     * @return true if taken, false if available
     */
    public boolean usernameTaken(String PKusername) {
        boolean nameTaken = false;

        for (User user : userList) {
            if (user.getUsername().equals(PKusername)) {
                return true;
            }
        }
        return nameTaken;
    }

    /**
     * @return ArrayList<User> list of Users who have accounts with the program
     */
    public ArrayList<User> getUserList() {
        return userList;
    }

}
