package org.example.fruitjavafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application implements EventHandler<ActionEvent> {

    private Label title;
    private Label response;
    private Label selected;
    private CheckBox bananaCheckBox;
    private CheckBox mangoCheckBox;
    private CheckBox papayaCheckBox;
    private CheckBox grapefruitCheckBox;

    private Rotate rotate;

    private String fruits;
    private Image fruitImage;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Favorite Fruits");

        title = new Label("What fruits do you like ?");
        response = new Label("");
        selected = new Label("");

        bananaCheckBox = new CheckBox("Banana");
        mangoCheckBox = new CheckBox("Mango");
        papayaCheckBox = new CheckBox("Papaya");
        grapefruitCheckBox = new CheckBox("Grapefruit");



        // Set up the stage and the scene
        FlowPane root = new FlowPane(Orientation.VERTICAL, 5, 5);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(title, bananaCheckBox, papayaCheckBox, mangoCheckBox, grapefruitCheckBox, response, selected);

        // Attach event listener to checkboxes
        bananaCheckBox.setOnAction(this);
        mangoCheckBox.setOnAction(this);
        papayaCheckBox.setOnAction(this);
        grapefruitCheckBox.setOnAction(this);

        Scene scene = new Scene(root, 400, 500);
        stage.setScene(scene);
        stage.show();
        showAll();
    }

    void showAll() {
        fruits = "";
        if (bananaCheckBox.isSelected()) fruits += " Banana";
        if (mangoCheckBox.isSelected()) fruits += " Mango";
        if (papayaCheckBox.isSelected()) fruits += " Papaya";
        if (grapefruitCheckBox.isSelected()) fruits += " Grapefruit";
        selected.setText("Fruits Selected: " + fruits);
    }

    @Override
    public void handle(ActionEvent event) {
        CheckBox fruitChecked = (CheckBox) event.getSource();
        displaySymbol(fruitChecked);
        showAll();
    }

    private void displaySymbol(CheckBox fruitCheckBox) {
        String imagePath = "";
        String fruitName = "";
        if (fruitCheckBox.equals(bananaCheckBox)) {
            imagePath = "C:\\Users\\ADMIN\\Downloads\\banana.png";
            fruitName = "Banana";
        } else if (fruitCheckBox.equals(mangoCheckBox)) {
            imagePath = "C:\\Users\\ADMIN\\Downloads\\mango.png";
            fruitName = "Mango";
        } else if (fruitCheckBox.equals(papayaCheckBox)) {
            imagePath = "C:\\Users\\ADMIN\\Downloads\\papaya.png";
            fruitName = "Papaya";
        } else if (fruitCheckBox.equals(grapefruitCheckBox)) {
            imagePath = "C:\\Users\\ADMIN\\Downloads\\grape.png";
            fruitName = "Grapefruit";
        }

        try {
            fruitImage = new Image(new FileInputStream(imagePath));
            response.setText(fruitName + " is " + (fruitCheckBox.isSelected() ? "Selected" : "Cleared"));
        } catch (FileNotFoundException e) {
            response.setText("Image not found for " + fruitName);
        }

        ImageView imageView = new ImageView(fruitImage);
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        response.setGraphic(imageView);
    }

    public static void main(String[] args) {
        launch();
    }
}
