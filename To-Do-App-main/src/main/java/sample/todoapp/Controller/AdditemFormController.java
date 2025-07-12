package sample.todoapp.Controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.todoapp.Database.DatabaseHandler;
import sample.todoapp.Model.Task;

public class AdditemFormController {
    private int userId;

    private DatabaseHandler databaseHandler;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private MFXTextField taskField;

    @FXML
    private MFXTextField descriptionField;

    @FXML
    private MFXButton saveTaskButton;

    @FXML
    private Label successLabel;

    @FXML
    private MFXButton todoButton;

    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();
        Task task = new Task();
        saveTaskButton.setOnAction(actionEvent -> {

            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());

            String taskText = taskField.getText().trim();
            String taskDescription = descriptionField.getText().trim();

            if (!taskText.equals("") && !taskDescription.equals("")) {

                task.setUserId(AddItemController.userId);
                //task.setUserId(getUserId());
                //System.out.println(userId);
                task.setDatecreated(timestamp);
                task.setDescription(taskDescription);
                task.setTask(taskText);
                databaseHandler.insertTask(task);
                System.out.println("Successful entry ");

                successLabel.setVisible(true);
                todoButton.setVisible(true);
                int taskNumber = databaseHandler.getAllTasks(AddItemController.userId);

                todoButton.setText("My 2DO's: "+ taskNumber);
                taskField.setText("");
                descriptionField.setText("");
                todoButton.setOnAction(actionEvent1 -> {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/todoapp/View/lists.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Parent root  = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                });


            }
            else{
                System.out.println("Unable to enter data");
            }


            //task.setDatecreated(timestamp); // Set the datetime value
            //task.setTask("Testing the connection");
            //task.setDescription("Passing the test case One");


        });
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
