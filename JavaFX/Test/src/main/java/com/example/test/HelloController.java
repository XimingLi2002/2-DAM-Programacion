package com.example.test;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class HelloController implements Runnable{
    @FXML
    private Button button;
    @FXML
    private Rectangle buttonBotRectangle;
    @FXML
    private Rectangle buttonTopRectangle;

    private TranslateTransition buttonTopBorderHover1;
    private TranslateTransition buttonTopBorderHover2;

    private FadeTransition fadeTransition1;
    private FadeTransition fadeTransition2;
    @Override
    public void run() {
        buttonTopBorderHover1 = new TranslateTransition(Duration.millis(1000), buttonTopRectangle);
        buttonTopBorderHover1.setFromX(buttonTopRectangle.getX());
        buttonTopBorderHover1.setToX(-1 * (button.getWidth() -20 - buttonTopRectangle.getWidth()));
        buttonTopBorderHover1.setCycleCount(1);
        buttonTopBorderHover1.setOnFinished(event -> startTransition());

        buttonTopBorderHover2 = new TranslateTransition(Duration.millis(1000), buttonTopRectangle);
        buttonTopBorderHover2.setFromX(-1 * (button.getWidth() -20 - buttonTopRectangle.getWidth()));
        buttonTopBorderHover2.setToX(0);
        buttonTopBorderHover2.setCycleCount(1);

        fadeTransition1 = new FadeTransition(Duration.millis(1000), buttonTopRectangle);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(1);
        fadeTransition1.setCycleCount(1);


        fadeTransition2 = new FadeTransition(Duration.millis(1000), buttonTopRectangle);
        fadeTransition2.setFromValue(1);
        fadeTransition2.setToValue(0);
        fadeTransition2.setCycleCount(1);
    }
    private void startTransition(){
        fadeTransition2.play(); buttonTopBorderHover2.play();
    }
    @FXML
    private void test(){
        run();
        fadeTransition1.play();
        buttonTopBorderHover1.play();

    }
}