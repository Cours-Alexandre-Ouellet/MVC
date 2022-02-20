package edu.uqtr.mvc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CalendrierApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalendrierApplication.class.getResource("vue-calendrier.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 850);
        stage.setTitle("Calendrier");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}