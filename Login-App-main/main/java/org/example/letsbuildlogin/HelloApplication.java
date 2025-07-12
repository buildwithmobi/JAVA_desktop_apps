
package org.example.letsbuildlogin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Login Page");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // Handle the exception (e.g., log it or display an error message)
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
