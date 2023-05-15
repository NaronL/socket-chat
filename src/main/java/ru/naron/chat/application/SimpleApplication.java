package ru.naron.chat.application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class SimpleApplication extends Application {
    private final String resource;
    private final String title;
    private final int width;
    private final int height;

    public SimpleApplication(String resource, String title, int width, int height){
        this.resource = resource;
        this.title = title;
        this.width = width;
        this.height = height;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/".concat(resource).concat(".fxml")));

        primaryStage.setScene(new Scene(parent, width, height));
        primaryStage.setTitle(title);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.show();
    }
}
