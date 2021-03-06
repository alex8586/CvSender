package com.alex;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

@Lazy
@SpringBootApplication
public class MainApp extends AbstractJavaFxApplicationSupport {

    @Qualifier("mainView")
    @Autowired
    private ConfigurationControllers.View view;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Email Sender");
        stage.setScene(new Scene(view.getView()));
        stage.setResizable(true);
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launchApp(MainApp.class, args);
    }

}