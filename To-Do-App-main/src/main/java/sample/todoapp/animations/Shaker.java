package sample.todoapp.animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;


public class Shaker {
    private TranslateTransition translateTransition;
    //anything on screen is a node
    public  Shaker(Node node){
        translateTransition = new TranslateTransition(Duration.millis(50), node);

        translateTransition.setFromX(0f);
        translateTransition.setByX(10f);
        translateTransition.setByX(10f);
        // within  50 seconds it happens 2 time
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);
    }
    public  void shake(){
        translateTransition.playFromStart();

    }
}
