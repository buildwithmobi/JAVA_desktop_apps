package sample.todoapp.Controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.todoapp.Database.DatabaseHandler;
import sample.todoapp.Model.User;
import sample.todoapp.animations.Shaker;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginController {
    private int userId;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MFXPasswordField loginPassword;

    @FXML
    private MFXTextField loginUsername;

    @FXML
    private MFXButton loginLoginbutton;

    @FXML
    private MFXButton loginSignupButton;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {


        databaseHandler = new DatabaseHandler();



        loginLoginbutton.setOnAction(event -> {

            String loginText = loginUsername.getText().trim();
            String loginPwd = loginPassword.getText().trim();

            User user = new User();
            user.setUserName(loginText);
            user.setPassword(loginPwd);

            ResultSet userRow = databaseHandler.getUser(user);
            int count=0;
            try {
                // Check if the result set is not null and if it has at least one row
                while(userRow.next()) {
                    count++;
                    // Retrieve data from the result set
                    String name = userRow.getString("firstname");
                    userId = userRow.getInt("userid");
                    System.out.println("Welcome! " + name);
                    System.out.println(user.getPassword());

                } if(count==1){
                    showAddItemScreen();
                } else {

                    Shaker shaker = new Shaker(loginUsername);
                    shaker.shake();
                    System.out.println("no");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });


        loginSignupButton.setOnAction(actionEvent -> {
            //Take users to  signUp screen
            loginSignupButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/todoapp/View/signup.fxml"));

            try{
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

        });


    }
    private void showAddItemScreen(){
        loginSignupButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/todoapp/View/additem.fxml"));

        try{
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        AddItemController addItemController = loader.getController();
        addItemController.setUserId(userId);
        stage.showAndWait();

    }


    private  void loginUser(String userName, String password){
        //Check in the database if the user exists, if true
        //we take them to AddItem Screen

        System.out.println("hi");
    }
}

