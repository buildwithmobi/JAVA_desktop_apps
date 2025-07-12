package sample.todoapp.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.todoapp.animations.Shaker;

public class AddItemController {
    static int userId;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label notTaskLabel;
    @FXML
    private ImageView addButton;

    @FXML
    void initialize() {
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Shaker buttonShaker = new Shaker(addButton);
            buttonShaker.shake();
            System.out.println("Added Clicked");


            addButton.relocate(300,200);
            notTaskLabel.relocate(230,100);


            addButton.setOpacity(0);
            notTaskLabel.setOpacity(0);

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), addButton);
            FadeTransition labelTransition = new FadeTransition(Duration.millis(2000), notTaskLabel);

            fadeTransition.setFromValue(1f);
            fadeTransition.setToValue(0f);
            fadeTransition.setCycleCount(1);
            fadeTransition.setAutoReverse(false);
            fadeTransition.play();

            labelTransition.setFromValue(1f);
            labelTransition.setToValue(0f);
            labelTransition.setCycleCount(1);
            labelTransition.setAutoReverse(false);
            labelTransition.play();

            try {
                AnchorPane formPane = FXMLLoader.load(getClass().
                        getResource("/sample/todoapp/View/additemForm.fxml"));



                AddItemController.userId = getUserId();
                //AdditemFormController additemFormController = new AdditemFormController();
                //additemFormController.setUserId(getUserId());

                rootPane.getChildren().setAll(formPane);

                FadeTransition rootTransition = new FadeTransition(Duration.millis(2000),formPane);
                rootTransition.setFromValue(0f);
                rootTransition.setToValue(1f);
                rootTransition.setCycleCount(1);
                rootTransition.setAutoReverse(false);
                rootTransition.play();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void setUserId(int userId) {
        this.userId =userId;
        System.out.println("User ID is "+ userId);
    }
    public int getUserId(){
        return this.userId;
    }
}
