package com.example.timer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TimerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TimerApplication.class.getResource("Timer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 200);
        stage.setTitle("会议计时器");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}