module org.example.fruitjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.fruitjavafx to javafx.fxml;
    exports org.example.fruitjavafx;
}