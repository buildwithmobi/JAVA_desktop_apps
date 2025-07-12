package sample.todoapp;

import com.mysql.cj.protocol.Resultset;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.todoapp.Database.DatabaseHandler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(login.class.getResource("/sample/todoapp/View/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("2D0");
        stage.setScene(scene);
        stage.show();

        //System.out.println(databaseHandler.getAllTasks(1));
    }

    public static void main(String[] args) {
        launch();
    }
}