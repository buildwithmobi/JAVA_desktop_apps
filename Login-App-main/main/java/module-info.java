module org.example.letsbuildlogin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens org.example.letsbuildlogin to javafx.fxml;
    exports org.example.letsbuildlogin;
}