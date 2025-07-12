package org.example.letsbuildlogin;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private Button LoginButton; // Change AnchorPane to Button

    @FXML
    private TextField LoginUserName;

    @FXML
    private PasswordField LoginPassword;

    @FXML
    void initialize() {
        if (LoginUserName != null) {
            LoginUserName.setStyle("-fx-text-inner-color: #a0a2ab");
        }

        if(LoginPassword != null){
            LoginPassword.setStyle("-fx-text-inner-color: #a0a2ab");}
        LoginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Login is clicked");
                loginUser();
                // You can add login functionality here
            }
        });


        }
    private void loginUser(){
        LoginButton.getScene().getWindow().hide();


        if (!LoginUserName.getText().toString().trim().equals("") && !LoginPassword.getText().toString().trim().equals("")) {
            // Your code here
            Stage detailStage = new Stage();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/letsbuildlogin/details.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                DetailsController detailsController = loader.getController();
                detailsController.initialize();
                detailsController.setName(LoginUserName.getText().toString().trim(),"Java & App Developer", "20");
                detailStage.setScene(scene);
                detailStage.show();
                detailStage.setResizable(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

