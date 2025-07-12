package sample.todoapp.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import sample.todoapp.Database.DatabaseHandler;
import sample.todoapp.Model.Task;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXListView<Task> listTask;

    @FXML
    private JFXTextField listTaskField;

    @FXML
    private JFXTextField listDescriptionField;

    @FXML
    private JFXButton listSaveTaskButton;

    @FXML
    private ImageView listRefreshButton;

    private ObservableList<Task> tasks;
    private ObservableList<Task> refreshedTasks;
    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() throws SQLException {
        tasks = FXCollections.observableArrayList();

        databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getTasksByUser(AddItemController.userId);
        while (resultSet.next()) {
            Task task = new Task();
            task.setTaskId(resultSet.getInt("taskid"));
            task.setTask(resultSet.getString("task"));
            task.setDatecreated(resultSet.getTimestamp("datecreated"));
            task.setDescription(resultSet.getString("description"));
            tasks.add(task);
        }

        listTask.setItems(tasks);
        listTask.setCellFactory(listView -> new CellController());
        listSaveTaskButton.setOnAction(event -> addNewTask());

        listRefreshButton.setOnMouseClicked(event -> {
            try {
                refreshList();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        listSaveTaskButton.setOnAction(event -> {
            addNewTask();

        });
    }

    public void addNewTask() {
        if (!listTaskField.getText().isEmpty() && !listDescriptionField.getText().isEmpty()) {
            Task newTask = new Task();
            newTask.setUserId(AddItemController.userId);
            newTask.setTask(listTaskField.getText().trim());
            newTask.setDescription(listDescriptionField.getText().trim());
            newTask.setDatecreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));

            databaseHandler.insertTask(newTask);
            try {
                initialize();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public  void refreshList() throws SQLException {
        System.out.println("refreshList in ListCont called");

        refreshedTasks = FXCollections.observableArrayList();


        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getTasksByUser(AddItemController.userId);

        while (resultSet.next()) {
            Task task = new Task();
            task.setTaskId(resultSet.getInt("taskid"));
            task.setTask(resultSet.getString("task"));
            task.setDatecreated(resultSet.getTimestamp("datecreated"));
            task.setDescription(resultSet.getString("description"));

            refreshedTasks.addAll(task);

        }


        listTask.setItems(refreshedTasks);
        listTask.setCellFactory(CellController -> new CellController());

    }
}
