module sample.todoapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires java.sql;
    requires mysql.connector.j;
    requires com.jfoenix;


    opens sample.todoapp to javafx.fxml;
    exports sample.todoapp;
    exports sample.todoapp.Controller;
    opens sample.todoapp.Controller to javafx.fxml;
}