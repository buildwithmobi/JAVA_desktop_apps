package org.example.letsbuildlogin;

import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;

import java.util.ResourceBundle;

import java.awt.Desktop;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;


public class DetailsController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label detailsWelcomeLabel;

    @FXML
    private Label detailName;

    @FXML
    private Label detailAge;

    @FXML
    private Label detailProfession;

    @FXML
    private Hyperlink deatailsWeb;

    @FXML
    private Hyperlink detailTweet;


    @FXML
    void initialize() {
         deatailsWeb.setOnAction(new EventHandler<ActionEvent>() {
             @java.lang.Override
             public void handle(ActionEvent actionEvent) {
                 try {
                     URI uri = new URI("https://codemy.com/");
                     Desktop.getDesktop().browse(uri);
                 } catch (URISyntaxException | IOException e) {
                     e.printStackTrace();
                 }

             }
             //https://twitter.com/i/flow/login?redirect_after_login=%2Fcodemynet
         });

        detailTweet.setOnAction(new EventHandler<ActionEvent>() {
            @java.lang.Override
            public void handle(ActionEvent actionEvent) {
                try {
                    URI uri = new URI("https://twitter.com/i/flow/login?redirect_after_login=%2Fcodemynet");
                    Desktop.getDesktop().browse(uri);
                } catch (URISyntaxException | IOException e) {
                    e.printStackTrace();
                }

            }

        });

    }

    void  setName(String name,String proffession, String age){
        detailsWelcomeLabel.setText("Welcome "+ name);
        detailName.setText(name);
        detailProfession.setText("Profession: " + proffession);
        detailAge.setText("Age: " +age);
    }
}
