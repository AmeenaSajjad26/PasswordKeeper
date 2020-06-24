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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.net.MalformedURLException;

/**
 * Sets the stage for the program's Main Menu window.
 * User can select from three options. Depending on their selection, the scene of window switches.
 * @author ShuWen Zhang
 */
public class MainMenuStage extends Stage {

    User user;
    
    /**
     * Constructor for MainMenuStage. Its member field User will hold information about that user.
     * @param user
     * @throws FileNotFoundException 
     */
    public MainMenuStage(User user) throws FileNotFoundException {
        this.user = user;
        
        // Open file containing the user's site information, if it already exists
        // Read in, and save to user's member field ArrayList<Site> websites
            if (new File(user.getUsername() + ".txt").exists())  {
                try (
                        Scanner readIn = new Scanner(new File(user.getUsername() + ".txt")).useDelimiter("\t");
                )    {
                    while (readIn.hasNext()) {
                        String url = readIn.next();
                        String siteUsername = readIn.next();
                        String sitePW = readIn.next();
                        String notes = readIn.nextLine();
                        
                        Site tempSite = new Site(url, siteUsername, sitePW, notes);
                        user.addWebsite(tempSite);
                    }
                    System.out.println("userList: " + user.getWebsites());
                }
        }
        
        
        this.setTitle("Password Keeper Main Menu");
        this.setScene(menuScene());
        this.show();
    }
    
    /**
     * Initial scene displaying the main menu
     * @return menuScene
     */
    Scene menuScene() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);   // set horizontal gaps b/t columns
        grid.setVgap(10);   // set vertical gaps b/t rows
        grid.setPadding(new Insets(35, 35, 35, 35));    // set margins around entire grid

        Text scenetitle = new Text("Welcome, " + user.getUsername() + "!\n"
                + "Make your selection");
        scenetitle.setStyle("-fx-font-size: 16px;");
        
        // Radio buttons for menu options
        RadioButton addSite = new RadioButton("Add a site");
        RadioButton listSites = new RadioButton("List all sites");
        RadioButton searchSites = new RadioButton("Search sites");

        // Make buttons a toggle group (can only select one at a time)
        ToggleGroup groupRadioButtons = new ToggleGroup();
        addSite.setToggleGroup(groupRadioButtons);
        listSites.setToggleGroup(groupRadioButtons);
        searchSites.setToggleGroup(groupRadioButtons);
        
        // Add to grid
        grid.add(scenetitle, 0, 0, 2, 1);
        grid.add(addSite, 0, 2);
        grid.add(listSites, 0, 3);
        grid.add(searchSites, 0, 4);
                
        // Add buttons
        Button next = new Button("Next");
        HBox hboxNext = new HBox(10);
        hboxNext.setAlignment(Pos.CENTER);
        hboxNext.getChildren().add(next);
        grid.add(hboxNext, 0, 6, 1, 2);
        
        Button close = new Button("Close");
        HBox hboxClose = new HBox(10);
        hboxClose.setAlignment(Pos.CENTER);
        hboxClose.getChildren().add(close);
        grid.add(hboxClose, 0, 8, 1, 2);

        // Depending on radio button selected, program switches to different scene
        // Action event for next button
        next.setOnAction(e -> {
            if (addSite.isSelected()) {    
                this.setScene(addSiteScene());
                this.setTitle("Password Keeper: Add Site");
            }

            if (listSites.isSelected()) {
                this.setScene(listSiteScene());
                this.setTitle("Password Keeper: List Sites");

            }
            if (searchSites.isSelected()) {
                this.setScene(searchSiteScene());
                this.setTitle("Password Keeper: Search Sites");
            }
        });
        
        // Actions event for close button
        close.setOnAction(e-> {
            close();
        });
        
        return new Scene(grid, 450, 300);
    }

    /**
     * Scene displays interface for adding a site
     * @return addSiteScene
     */
    Scene addSiteScene() {
        System.out.println("Size of websites ArrayList: " + user.getWebsites().size());

        GridPane addSiteGrid = new GridPane();
        addSiteGrid.getColumnConstraints().add(new ColumnConstraints(100)); // column 0 is 100 wide
        addSiteGrid.setAlignment(Pos.CENTER);
        addSiteGrid.setHgap(10);   // set horizontal gaps b/t columns
        addSiteGrid.setVgap(10);   // set vertical gaps b/t rows
        addSiteGrid.setPadding(new Insets(35, 35, 35, 35));    // set margins around entire grid
        
        // Add text, labels, textfields
        Text scenetitle = new Text("Add a new site");
        scenetitle.setStyle("-fx-font-size: 16px;");
        Label url = new Label("Website URL:");
        TextField urlField = new TextField();
        Label username = new Label("User Name:");
        TextField usernameField = new TextField();
        Label pw = new Label("Password:");
        TextField pwField = new TextField();  
        Label notes = new Label("Notes (optional):");
        TextArea notesField = new TextArea();   
        notesField.setWrapText(true);
        
        // Add to grid
        addSiteGrid.add(scenetitle, 0, 0, 2, 1);   
        addSiteGrid.setHalignment(scenetitle, HPos.CENTER);
        addSiteGrid.add(url, 0, 2);
        addSiteGrid.add(urlField, 1, 2);
        addSiteGrid.add(username, 0, 3);
        addSiteGrid.add(usernameField, 1, 3);
        addSiteGrid.add(pw, 0, 4);
        addSiteGrid.add(pwField, 1, 4);
        addSiteGrid.add(notes, 0, 5);
        addSiteGrid.add(notesField, 1, 5);        
                
        // Button and a text to put a message
        final Text message = new Text();
        addSiteGrid.add(message, 0, 6, 2, 1);
        addSiteGrid.setHalignment(message, HPos.CENTER);
        
        Button addSite = new Button("Add Site");
        HBox hboxAdd = new HBox(10);
        hboxAdd.setAlignment(Pos.CENTER);
        hboxAdd.getChildren().add(addSite);
        addSiteGrid.add(hboxAdd, 0, 7, 2, 1);
        
        Button back = new Button("Back");
        HBox hboxBack = new HBox(10);
        hboxBack.setAlignment(Pos.CENTER);
        hboxBack.getChildren().add(back);
        addSiteGrid.add(hboxBack, 0, 9, 2, 1);

        // Action event for add button
        addSite.setOnAction((ActionEvent e) -> {
            
            message.setFont(Font.font("Arial", FontPosture.ITALIC, 12));
            message.setFill(Color.FIREBRICK);
            
            if (urlField.getText().isEmpty() || usernameField.getText().isEmpty()
                    || pwField.getText().isEmpty())
                message.setText("The first three fields must be filled.");
            
            // Validate url
            else if (!validURL(urlField.getText())) {
                message.setText("Invalid URL. Make sure to include http://");
            }

            // Validate url exists or not
            else if(urlExists(urlField.getText())) {
                message.setText("URL already exists. Try another URL.");
            }
            
            else {
                
                
                
                // Call suitable constructor, depending on if notes field is empty or not
                Site siteTemp = new Site(urlField.getText(), usernameField.getText(),
                           pwField.getText(), notesField.getText());
               
//                if (!notesField.getText().isEmpty()) {
//                    siteTemp.setNotes(notesField.getText());
//                }
                    
                // save to User's ArrayList<Site> websites field
                user.addWebsite(siteTemp);
                System.out.println("Size of websites Array:" + user.getWebsites().size());
                        
                // Write to file
                try (
                    // Create a file
                        PrintWriter output = new PrintWriter(new File(user.getUsername() + ".txt"));) {
                    // Write formatted output to the file
                    for (Site site : user.getWebsites()) {
                        output.println(site.toString());
                        System.out.println(site.toString());
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainMenuStage.class.getName()).log(Level.SEVERE, null, ex);
                }

                message.setText("Website log-in info successfully saved.");
                urlField.clear();
                usernameField.clear();
                pwField.clear();
                notesField.clear();

                addSite.setText("Add another site");
            }     
            
        });
        
        // Action event for back button: return to main menu
        back.setOnAction(e -> {
            this.setScene(menuScene());
            this.setTitle("Password Keeper: Main Menu");

        });
        
        return new Scene(addSiteGrid, 450, 450);
    }
    
    /**
     * Validate url by using try-catch for MalformedURLException
     * @param url String
     * @return true if valid, false if invalid url
     */
    public static boolean validURL(String url) {
        try {
           new URL(url);
           return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
    
    /**
     * Validate if url already exists by checking against User's ArrayList of Sites
     * @param url String
     * @return true if url exists, otherwise false
     */
    public boolean urlExists(String url) {
        for (Site site: user.getWebsites()) {
            if (site.getURL().equals(url)) {
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * Scene lists all the sites that have been saved to user's account
     * @return listSiteScene
     */
    Scene listSiteScene() {
        
        //Text text = new Text();
       // final double height = text.getLayoutBounds().getHeight();
        
        TextArea text = new TextArea();        
         
        if (user.getWebsites().isEmpty()) {
            text.setFont(Font.font("Arial", FontPosture.ITALIC, 12));
            text.setStyle("-fx-text-fill: #b22222;");
            //text.setFill(Color.FIREBRICK);
            text.setText("No sites saved.");
        }
        else {
            text.setText(user.allWebsitesToString());
        }
        
//        text.setX(20);
//        text.setY(30);

        // Glitchy: how to make sure it scrolls correctly?
        BorderPane pane = new BorderPane();
        pane.setCenter(text);
        
//Pane paneText = new Pane();
//        paneText.getChildren().add(text);
//        paneText.setPadding(new Insets(35, 35, 35, 35));
//        
//        ScrollBar sbVertical = new ScrollBar();
//        sbVertical.setOrientation(Orientation.VERTICAL);
//        
//        BorderPane pane = new BorderPane();
//        pane.setRight(sbVertical);
//        pane.setCenter(paneText);
         
//        // Listener for vertical scroll bar value change
//        sbVertical.valueProperty().addListener(ov ->
//                text.setY(sbVertical.getValue() / (sbVertical.getMax() + height) * 
//                        paneText.getHeight() ));
        
        Button back = new Button("Back");
        HBox hboxBack = new HBox(10);
//        hboxBack.setStyle("-fx-padding: 10;" +
//                "-fx-border-style: solid inside;" +
//                "-fx-border-width: 1;" + 
//                "-fx-border-color: black;");
        hboxBack.setAlignment(Pos.CENTER);
        hboxBack.getChildren().add(back);
        hboxBack.setPrefHeight(40);
        pane.setBottom(hboxBack);

        // Action event for back button
        back.setOnAction(e -> {
            this.setScene(menuScene());
            this.setTitle("Password Keeper: Main Menu");

        });
        
        return new Scene(pane, 450, 450);

    }
    
     /**
     * Scene displays interface for user to search their site information. 
     * @return searchSiteScene
     */
    Scene searchSiteScene() {
        
        BorderPane borderPane = new BorderPane();
        
        GridPane searchSiteGrid = new GridPane();
        searchSiteGrid.getColumnConstraints().add(new ColumnConstraints(200)); // column 0 is 100 wide
        searchSiteGrid.setAlignment(Pos.CENTER);
        searchSiteGrid.setHgap(10);   // set horizontal gaps b/t columns
        searchSiteGrid.setVgap(10);   // set vertical gaps b/t rows
        searchSiteGrid.setPadding(new Insets(35, 35, 35, 35));    // set margins around entire grid
        
        // Add text, labels, textfields
        Text scenetitle = new Text("Search for a site");
        scenetitle.setStyle("-fx-font-size: 16px;");
        Label searchTerm = new Label("Enter search term for website url:");
        TextField searchBox = new TextField();
        
        // Adding to grid
        searchSiteGrid.add(scenetitle, 0, 0);   
        searchSiteGrid.setHalignment(scenetitle, HPos.CENTER);
        searchSiteGrid.add(searchTerm, 0, 1);
        searchSiteGrid.setHalignment(searchTerm, HPos.CENTER);
        searchSiteGrid.add(searchBox, 0, 2);
        
        // Button and a text to put a message
        final Text message = new Text();
        searchSiteGrid.add(message, 0, 3);
        searchSiteGrid.setHalignment(message, HPos.CENTER);
        message.setFill(Color.FIREBRICK);
        message.setFont(Font.font("Arial", FontPosture.ITALIC, 12));

        if (user.getWebsites().isEmpty()) {
                message.setText("No site info available for user.");
            }
        
        Button searchSite = new Button("Search for Site");
        HBox hboxSearch = new HBox(10);
        hboxSearch.setAlignment(Pos.CENTER);
        hboxSearch.getChildren().add(searchSite);
        searchSiteGrid.add(hboxSearch, 0, 5);
        
        Button back = new Button("Back");
        HBox hboxBack = new HBox(10);
        hboxBack.setAlignment(Pos.CENTER);
        hboxBack.getChildren().add(back);
        searchSiteGrid.add(hboxBack, 0, 6);
       
        // For displaying results
        TextArea searchResult = new TextArea();
        
        // Add grid and searchResult text area to borderpane
        borderPane.setLeft(searchSiteGrid);
        borderPane.setRight(searchResult);
        
        searchSite.setOnAction(e -> {
            
            searchResult.clear();

            boolean found = false;
            
            if (searchBox.getText().isEmpty()) {
                message.setText("Enter search term.");
            }
            else if (user.getWebsites().isEmpty()) {
                message.setText("No site info available for user.");
            }
            else {
                
                for (Site site: user.getWebsites()) {
                    if (site.getURL().contains(searchBox.getText())) {
                        found = true;                        
                        System.out.println("website found");
                        searchResult.setFont(Font.font("Arial", FontPosture.REGULAR, 14));
                        searchResult.setStyle("-fx-text-fill: #000000");
                        searchResult.appendText(site.pringSiteString());
                        System.out.println(site.pringSiteString());
                    }  

                }
                
                if (!found) {
                    searchResult.setFont(Font.font("Arial", FontPosture.ITALIC, 12));
                    searchResult.setStyle("-fx-text-fill: #b22222;");
                    searchResult.setText("No results.\nTry another search term (one word)."); 
                }
            }   
        });

        // Action event for back button
        back.setOnAction(e -> {
            this.setScene(menuScene());
            this.setTitle("Password Keeper: Main Menu");

        });
        
        return new Scene(borderPane, 450, 450);
    }

}
