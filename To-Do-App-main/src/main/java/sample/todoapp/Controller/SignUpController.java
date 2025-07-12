package sample.todoapp.Controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.todoapp.Database.DatabaseHandler;
import sample.todoapp.Model.User;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MFXTextField SignUpFirstName;

    @FXML
    private MFXTextField SignUpLastName;

    @FXML
    private MFXTextField SignUpUserName;

    @FXML
    private MFXTextField SignUpLocation;

    @FXML
    private MFXCheckbox SignUpCheckBoxMale;

    @FXML
    private MFXCheckbox SignUpCheckBoxFemale;

    @FXML
    private MFXPasswordField SignUpPassword;

    @FXML
    private MFXButton SignUpButton;


    @FXML
    void initialize() {


        SignUpButton.setOnAction(event ->{
            createUser();

            SignUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/todoapp/View/login.fxml"));


            try {
                loader.load();


            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.show();
        });

    }

    private void createUser(){

        String name = SignUpFirstName.getText();
        String lastName= SignUpLastName.getText();
        String userName = SignUpUserName.getText();
        String password = SignUpPassword.getText();
        String location = SignUpLocation.getText();

        String gender = "";
        if(SignUpCheckBoxFemale.isSelected()){
            gender = "Female";

        }else gender = "Male";

        User user  = new User(name,lastName,userName, password,location,gender);
        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.signUpUser(user);
    }
}
